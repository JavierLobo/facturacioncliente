import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Usuario } from './usuario';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private _usuario: Usuario;
  private _token: string;

  constructor(private http: HttpClient) { }

  public get usuario(): Usuario {
    if(this._usuario != null) {
      return this._usuario;

    } else if(this._usuario == null && sessionStorage.getItem('usuario') != null) {
      this._usuario = JSON.parse(sessionStorage.getItem('usuario')) as Usuario;
      return this._usuario;
    }
    return new Usuario();
  }

  public get token(): string {
    console.log('getToken: ' + this._token);

    if (this._token != null) {
      return this._token;

    } else if(this._token == null && sessionStorage.getItem('token') != null) {
      this._token = sessionStorage.getItem('token');
      return this._token;
    }
    
    return null;
  }

  login(usuario: Usuario): Observable<any> {
    const urlEndPoint = 'http://localhost:8080/oauth/token';
    const credenciales = btoa('angularapp' + ':' + '12345'); // Esta línea no deberia ser así
    const httpHeaders = new HttpHeaders({'Content-Type': 'application/x-www-form-urlencoded', 'Authorization': 'Basic ' + credenciales});

    let params = new URLSearchParams();
    params.set('grant_type', 'password');
    params.set('username', usuario.username);
    params.set('password', usuario.password);

    return this.http.post<any>(urlEndPoint, params.toString(), {headers: httpHeaders});
  }
  
  logout():void {
    this._token = null;
    this._usuario = null;
    sessionStorage.clear();
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('usuario');
  }

  guardarUsuario(accessToken: string): void {
    let payLoad = this.obtenerDatosToken(accessToken);
    
    this._usuario = new Usuario();
    this._usuario.nombre = payLoad.nombre;
    this._usuario.apellido = payLoad.apellido;
    this._usuario.email = payLoad.email;
    this._usuario.username = payLoad.user_name;
    this._usuario.roles = payLoad.authorities;
  
    sessionStorage.setItem('usuario', JSON.stringify(this._usuario));
  }

  guardarToken(accessToken: string): void {
    this._token = accessToken;
    sessionStorage.setItem('token', accessToken);
  }

  obtenerDatosToken(accessToken: string): any {
    // let playLoad = '{"info_adicional": "Hola que tal!: admin","user_name": "admin","scope": ["read","write"],"apellido": "Butifarrez Moñigez","exp": 1602845756,"nombre": "Mentanez","authorities": ["ROLE_ADMIN", "ROLE_USER"], "jti": "c2b6dcb5-4d36-4b5d-8666-42432e1c1e3d", "email": "mentanez.butifarrezmoñigez@hotmail.com", "client_id": "angularapp"}';
    
    if (accessToken != null) {
      // ToDo: la funcion 'atob' falla porque le llega un caracter no valido, sospecho 
      //       que tiene que ver con el formato de codificación UTF-8
      return JSON.parse(atob(accessToken.split(".")[1]));
      // return JSON.parse(playLoad);
    } 
    return null;
  }

  isAuthenticated(): boolean {
    let payLoad = this.obtenerDatosToken(this.token);

    if (payLoad != null && payLoad.user_name && payLoad.user_name.length > 0) {
      return true;
    }
    return false;
  }

  hasRole(role: string): boolean {
    if (this.usuario.roles.includes(role)) {
      return true;
    }
    return false;
  }

  // b64DecodeUnicode(str): string {
  //   console.log(str);
  //   // Going backwards: from bytestream, to percent-encoding, to original string.
  //   return decodeURIComponent(atob(str).split('').map(function(c) {
  //       return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
  //   }).join(''));
  // }

  // base64encode(str): string {
  //   let encode = encodeURIComponent(str).replace(/%([a-f0-9]{2})/gi, (m, $1) => String.fromCharCode(parseInt($1, 16)));
  //   return btoa(encode);
  // }

  // base64decode(str): string {
  //   let decode = atob(str).replace(/[\x80-\uffff]/g, (m) => `%${m.charCodeAt(0).toString(16).padStart(2, '0')}`);
  //   return decodeURIComponent(decode);
  // }

}

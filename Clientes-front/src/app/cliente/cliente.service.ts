import { Injectable } from '@angular/core';
import { Cliente } from './Cliente';
import { CLIENTES } from './clientes.json';
import { TitleBar } from './TitleBar';
import { TITLEBAR } from './TitleBar.json';

// En angular 6 se cambian las dos líneas de abajo por la comentada
import { Observable, of } from 'rxjs';
//import { Observable } from 'rxjs/Observable';
//import { of } from 'rxjs/Observable/of';

import { HttpClient } from '@angular/common/http'

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  constructor(private http: HttpClient) { }

  getClientes(): Observable<Cliente[]> {
    return of(CLIENTES);
  }

  getTitleBar(): Observable<TitleBar[]> {
    return of(TITLEBAR);
  }
}

import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../usuarios/auth.service';

import swal from 'sweetalert2';

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html'
})
export class HeaderComponent {
    title: string = 'App Angular';

    constructor(public authService: AuthService, private router: Router) {}
 
    logout():void {
        let nombre = this.authService.usuario.nombre;
        this.authService.logout();
        swal.fire('Logout', `Hola ${nombre}, has cerrado sesión con éxito!`, 'success');
        this.router.navigate(['/login']);
    }
}
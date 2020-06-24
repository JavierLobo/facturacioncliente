import { Component, OnInit } from '@angular/core';
import { ClienteService } from './cliente.service';
import { Cliente } from './Cliente';
import { TitleBar } from './TitleBar';
import { TITLEBAR } from './TitleBar.json';
import { CLIENTES } from './clientes.json';
import { tap } from 'rxjs/operators';
import Swal from 'sweetalert2';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-cliente',
  templateUrl: './cliente.component.html'
})
export class ClienteComponent implements OnInit {
  title: string = 'Clientes';
  subTitle: string = 'Listado de clientes';

  titleBar: TitleBar[];
  clientes: Cliente[];
  
  paginador: any;

  constructor(private clienteService: ClienteService,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe( params => {
      let page: number = +params.get('page');
      if (!page) { page = 0; }

      this.clienteService.getClientes(page)
        .pipe(
          tap( response => {
            console.log('ClientesComponet: tap 3');
            (response.content as Cliente[]).forEach( cliente => {
              console.log(cliente.nombre);
            });
          })
        ).subscribe( response => {
          this.clientes = response.content as Cliente[];
          this.paginador = response;
        });

    });

//    this.clienteService.getTitleBar().subscribe(
//      titlebar => this.titleBar = TITLEBAR
//    );
  }

  delete(cliente: Cliente): void {
    const swalWithBootstrapButtons = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger'
      },
      buttonsStyling: false
    })
    
    swalWithBootstrapButtons.fire({
      title: '¿Está seguro?',
      text: `¿Seguro que desea eliminar al cliente ${cliente.nombre} ${cliente.apellido}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si, eliminar!',
      cancelButtonText: 'No, cancelar!',
      reverseButtons: true
    }).then((result) => {
      if (result.value) {

        this.clienteService.delete(cliente.id).subscribe(
          Response => {
            this.clientes = this.clientes.filter(cli => cli !== cliente)
            swalWithBootstrapButtons.fire(
              'Cliente Eliminado!',
              `Cliente ${cliente.nombre} eliminado con éxito.`,
              'success'
            )
          }
        )
      }
    })
  }
}

import { Component, OnInit } from '@angular/core';
import { ClienteService } from './cliente.service';
import { Cliente } from './Cliente';
import { TitleBar } from './TitleBar';
import { TITLEBAR } from './TitleBar.json';
import { CLIENTES } from './clientes.json';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-cliente',
  templateUrl: './cliente.component.html'
})
export class ClienteComponent implements OnInit {
  title: string = 'Clientes';
  subTitle: string = 'Listado de clientes';

  titleBar: TitleBar[];
  clientes: Cliente[];
  
  constructor(private clienteService: ClienteService) { }

  ngOnInit(): void {
    this.clienteService.getClientes().subscribe(
      clientes => this.clientes = clientes
    );
    
    this.clienteService.getTitleBar().subscribe(
      titlebar => this.titleBar = TITLEBAR
    );
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

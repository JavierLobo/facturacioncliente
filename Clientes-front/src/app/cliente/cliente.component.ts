import { Component, OnInit } from '@angular/core';
import { ClienteService } from './cliente.service';
import { Cliente } from './Cliente';
import { TitleBar } from './TitleBar';
import { TITLEBAR } from './TitleBar.json';
import { CLIENTES } from './clientes.json';

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
      clientes => this.clientes = CLIENTES
    );
    this.clienteService.getTitleBar().subscribe(
      titlebar => this.titleBar = TITLEBAR
    );
  }

}

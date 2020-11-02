import { Cliente } from '../../clientes/Cliente';
import { ItemFactura } from './item-factura';

export class Factura {
    id: number;
    descripcion: string;
    observacion: string;
    items: Array<ItemFactura> = []; // ItemFactura[] = [];
    cliente: Cliente;
    total: number;
    createAt: string;
}

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

    calcularGranTotal(): number {
      this.total = 0;

      this.items.forEach((item: ItemFactura) => {
        this.total += item.calcularImporte();
      });

      return this.total;
    }
}

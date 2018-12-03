import {Customer} from './customer';
import {Part} from './part';

export class ComputerSet {
  constructor() {}
  id: number;
  customer: Customer = new Customer();
  parts: Part[] = [];
}

import {Customer} from './customer';
import {Part} from './part';

export class ComputerSet {
  constructor() {}
  id: number;
  user: Customer = new Customer();
  parts: Part[] = [];
}

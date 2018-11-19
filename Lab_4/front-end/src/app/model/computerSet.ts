import {User} from './user';
import {Part} from './part';

export class ComputerSet {
  constructor() {}
  id: number;
  user: User;
  parts: Part[];
}

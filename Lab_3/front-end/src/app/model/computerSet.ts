import {User} from './user';
import {Part} from './part';
import {Link} from './link';

export class ComputerSet {
  constructor() {}
  id: number;
  user: User = new User();
  parts: Part[] = [];
  links: Link[];
}

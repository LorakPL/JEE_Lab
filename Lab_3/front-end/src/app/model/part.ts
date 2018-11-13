import {PartType} from './partType';
import {Link} from './link';

export class Part {
  constructor() {}
  id: number;
  name: string;
  price: string;
  type: PartType;
  links: Link[];
}

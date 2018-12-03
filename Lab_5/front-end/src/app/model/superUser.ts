import {Role} from './role';

export class SuperUser {
  constructor() {}
  id: number;
  name: string;
  password: string;
  role: Role;
}

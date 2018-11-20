import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {User} from '../../../model/user';
import {UsersService} from '../services/users.service';

@Component({
  selector: 'app-list-user',
  templateUrl: './list-users.component.html',
  styleUrls: ['./list-users.component.css']
})
export class ListUsersComponent implements OnInit {

  users: Observable<User[]>;
  nameToFind = '';

  constructor(private usersService: UsersService) {
  }

  ngOnInit() {
    this.users = this.usersService.findAllUsers();
  }

  remove(user: User) {
    this.usersService.removeUser(user)
      .subscribe(() => this.ngOnInit());
  }

  find() {
    if (this.nameToFind) {
      this.users = this.usersService.findAllUsersByName(this.nameToFind);
    } else {
      alert("Imię nie może być puste");
    }
  }

  all() {
    this.users = this.usersService.findAllUsers();
  }

}
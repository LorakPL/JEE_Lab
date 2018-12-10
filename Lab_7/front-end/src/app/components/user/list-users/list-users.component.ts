import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {User} from '../../../model/user';
import {UsersService} from '../services/users.service';
import {SharedService} from '../../../shared/services/shared.service';

@Component({
  selector: 'app-list-user',
  templateUrl: './list-users.component.html',
  styleUrls: ['./list-users.component.css']
})
export class ListUsersComponent implements OnInit {

  users: Observable<User[]>;
  loginToFind = '';
  nameToFind = '';
  secondNameToFind = '';
  column = '';
  direction = '';

  sort = {
    column: '',
    direction: ''
  };

  constructor(private usersService: UsersService, private sharedService: SharedService) {
  }

  ngOnInit() {
    this.users = this.sharedService.findAllUsers();
  }

  remove(user: User) {
    this.usersService.removeUser(user)
      .subscribe(() => this.ngOnInit());
  }

  find() {
    var link = '';
    if (this.loginToFind) {
      link += 'login=' + this.loginToFind + '&';
    }
    if (this.nameToFind) {
      link += 'name=' + this.nameToFind + '&';
    }
    if (this.secondNameToFind) {
      link += 'secondName=' + this.secondNameToFind + '&';
    }
    if (this.sort.column) {
      link += 'column=' + this.sort.column + '&';
    }
    if (this.sort.direction) {
      link += 'direction=' + this.sort.direction + '&';
    }
    console.log(this.users);
    console.log(this.sort);
    this.users = this.usersService.filterUser(link);
    console.log(this.users);
  }

  sortTable(value: string) {
    if (this.sort.column === value) {
      if (this.sort.direction === 'asc') {
        this.sort.direction = 'desc';
      } else {
        this.sort.direction = 'asc';
      }
    } else {
      this.sort.column = value;
      this.sort.direction = 'asc';
    }
    this.find();
  }

  all() {
    this.loginToFind = '';
    this.nameToFind = '';
    this.secondNameToFind = '';
    this.sort.column = '';
    this.sort.direction = '';
    this.users = this.sharedService.findAllUsers();
  }

}

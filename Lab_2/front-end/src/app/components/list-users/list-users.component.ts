import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {User} from '../../model/user';
import {ViewService} from '../../services/view.service';

@Component({
  selector: 'app-list-users',
  templateUrl: './list-users.component.html',
  styleUrls: ['./list-users.component.css']
})
export class ListUsersComponent implements OnInit {

  users: Observable<User[]>;

  constructor(private viewService: ViewService) {
  }

  ngOnInit() {
    this.users = this.viewService.findAllUsers();
  }

  remove(user: User) {
    this.viewService.removeUser(user)
      .subscribe(() => this.ngOnInit());
  }

}

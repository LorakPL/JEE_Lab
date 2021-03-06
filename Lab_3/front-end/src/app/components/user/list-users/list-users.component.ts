import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {User} from '../../../model/user';
import {UserService} from '../services/user.service';
import {SharedService} from '../../../shared/services/shared.service';

@Component({
  selector: 'app-list-users',
  templateUrl: './list-users.component.html',
  styleUrls: ['./list-users.component.css']
})
export class ListUsersComponent implements OnInit {

  users: Observable<User[]>;

  constructor(private userService: UserService, private sharedService: SharedService) {}

  ngOnInit() {
    this.users = this.sharedService.findAllUsers();
    this.users.subscribe(res => console.log(res));
  }

  remove(user: User) {
    this.userService.removeUser(user.links[0].link).subscribe(() => this.ngOnInit());
  }

}

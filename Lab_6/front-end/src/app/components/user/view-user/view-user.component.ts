import { Component, OnInit } from '@angular/core';
import {User} from '../../../model/user';
import {UsersService} from '../services/users.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-view-user',
  templateUrl: './view-user.component.html',
  styleUrls: ['./view-user.component.css']
})
export class ViewUserComponent implements OnInit {

  user: User;

  constructor(private usersService: UsersService, private route: ActivatedRoute) { }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    this.usersService.findUser(Number(id)).subscribe(user => this.user = user);
  }

}

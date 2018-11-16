import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {User} from '../../../model/user';
import {UserService} from '../services/user.service';

@Component({
  selector: 'app-view-user',
  templateUrl: './view-user.component.html',
  styleUrls: ['./view-user.component.css']
})
export class ViewUserComponent implements OnInit {

  user: User;

  constructor(private userService: UserService,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    const link = this.route.snapshot.paramMap.get('link');
    this.userService.findUser(String(link)).subscribe(user => this.user = user);
  }

}

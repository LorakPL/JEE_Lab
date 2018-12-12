import { Component, OnInit } from '@angular/core';
import {UsersService} from '../user/services/users.service';
import {AuthenticationService} from '../authentication/services/authentication.service';
import {first} from 'rxjs/operators';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-new-password',
  templateUrl: './new-password.component.html',
  styleUrls: ['./new-password.component.css']
})
export class NewPasswordComponent implements OnInit {

  password: string;
  password2: string;
  username: string;
  hide = true;
  returnUrl: string;

  constructor(private customerService: UsersService,
              private authenticationService: AuthenticationService,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  changePassword() {
    if (this.password !== this.password2 || this.password === undefined) {
      this.hide = false;
    } else {
      this.hide = true;
      this.username = localStorage.getItem('currentUserName').replace(/"/g, '');
      this.authenticationService.changePassword(this.username, this.password)
        .pipe(first())
        .subscribe(
          data => {
            this.authenticationService.logout();
            location.reload(true);
          },
          error => {
            console.log(error);
          });
    }
  }

}

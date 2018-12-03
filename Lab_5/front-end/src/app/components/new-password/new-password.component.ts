import { Component, OnInit } from '@angular/core';
import {CustomersService} from '../user/services/customers.service';
import {AuthenticationService} from '../authentication/services/authentication.service';

@Component({
  selector: 'app-new-password',
  templateUrl: './new-password.component.html',
  styleUrls: ['./new-password.component.css']
})
export class NewPasswordComponent implements OnInit {

  username: string;
  password: string;
  hide = true;

  constructor(private customerService: CustomersService, private authenticationService: AuthenticationService) { }

  ngOnInit() {
  }

  changePassword() {
    this.authenticationService.new(this.username, this.password);
    this.authenticationService.logout();
    location.reload(true);
  }

}

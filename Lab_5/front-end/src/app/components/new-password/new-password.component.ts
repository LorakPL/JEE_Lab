import { Component, OnInit } from '@angular/core';
import {CustomersService} from '../user/services/customers.service';

@Component({
  selector: 'app-new-password',
  templateUrl: './new-password.component.html',
  styleUrls: ['./new-password.component.css']
})
export class NewPasswordComponent implements OnInit {

  password: string;
  password2: string;
  hide = true;

  constructor(private customerService: CustomersService) { }

  ngOnInit() {
  }

  changePassword() {

    this.customerService.changePassword(this.password, this.password2);


    /*
    if(!this.password || !this.password2 || this.password !== this.password2) {
      console.log("Błędne dane");
      this.hide = false;
    } else {
      this.customerService.changePassword(this.password, this.password2);
      console.log("OK");
      this.hide = true;
    }
    */
  }

}

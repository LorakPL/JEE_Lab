import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {Customer} from '../../../model/customer';
import {CustomersService} from '../services/customers.service';
import {SharedService} from '../../../shared/services/shared.service';

@Component({
  selector: 'app-list-user',
  templateUrl: './list-users.component.html',
  styleUrls: ['./list-users.component.css']
})
export class ListUsersComponent implements OnInit {

  users: Observable<Customer[]>;
  nameToFind = '';

  constructor(private usersService: CustomersService, private sharedService: SharedService) {
  }

  ngOnInit() {
    this.users = this.sharedService.findAllCustomers();
  }

  remove(user: Customer) {
    this.usersService.removeCustomer(user)
      .subscribe(() => this.ngOnInit());
  }

  find() {
    if (this.nameToFind) {
      this.users = this.usersService.findAllCustomersByName(this.nameToFind);
    } else {
      alert("Imię nie może być puste");
    }
  }

  all() {
    this.users = this.sharedService.findAllCustomers();
  }

}

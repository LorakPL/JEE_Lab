import { Component, OnInit } from '@angular/core';
import {Customer} from '../../../model/customer';
import {CustomersService} from '../services/customers.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-view-user',
  templateUrl: './view-user.component.html',
  styleUrls: ['./view-user.component.css']
})
export class ViewUserComponent implements OnInit {

  user: Customer;

  constructor(private usersService: CustomersService, private route: ActivatedRoute) { }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    this.usersService.findCustomer(Number(id)).subscribe(user => this.user = user);
  }

}

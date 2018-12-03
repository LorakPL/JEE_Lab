import { Component, OnInit } from '@angular/core';
import {Customer} from '../../../model/customer';
import {CustomersService} from '../services/customers.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  user: Customer;
  usernameError = '';
  nameError = '';
  secondNameError = '';

  constructor(private usersService: CustomersService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');

    if (id == null) {
      this.user = new Customer();
    } else {
      this.usersService.findCustomer(Number(id)).subscribe(user => this.user = user);
    }
  }

  save() {
    this.usersService.saveCustomer(this.user).subscribe(
      () => this.router.navigateByUrl(''),
      errorResponse => {
        (errorResponse.error.parameterViolations.some(res => res.path.includes('userName'))) ?
          this.usernameError = 'Wartość w polu nazwa użytkownika musi się mieścić w przedziale 2 - 50 znaków' : this.usernameError = '';
        (errorResponse.error.parameterViolations.some(res => res.path.includes('name'))) ?
          this.nameError = 'Wartość w polu imię musi się mieścić w przedziale 2 - 50 znaków' : this.nameError = '';
        (errorResponse.error.parameterViolations.some(res => res.path.includes('secondName'))) ?
          this.secondNameError = 'Wartość w polu nazwisko musi się mieścić w przedziale 2 - 50 znaków' : this.secondNameError = '';
      });
  }

}

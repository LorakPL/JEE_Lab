import { Component, OnInit } from '@angular/core';
import {User} from '../../../model/user';
import {UsersService} from '../services/users.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  user: User;
  usernameError = '';
  nameError = '';
  secondNameError = '';

  constructor(private usersService: UsersService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');

    if (id == null) {
      this.user = new User();
    } else {
      this.usersService.findUser(Number(id)).subscribe(user => this.user = user);
    }
  }

  save() {
    this.usersService.saveUser(this.user).subscribe(
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

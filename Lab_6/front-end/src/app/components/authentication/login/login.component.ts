import { Component, OnInit } from '@angular/core';
import {LoginService} from '../services/login.service';
import {AuthenticationService} from '../services/authentication.service';
import {first} from 'rxjs/operators';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  login: string;
  password: string;
  password2: string;
  hide = true;
  returnUrl: string;
  registration = false;
  title = 'Logowanie';
  buttonText = 'Zaloguj';
  link = 'Rejestracja';

  constructor(private loginService: LoginService, private authenticationService: AuthenticationService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  signIn() {
    if (this.registration) {
      if(this.password !== this.password2) {
        alert("Niestety wpisane hasła się różnią");
      } else {
        this.authenticationService.register(this.login, this.password)
          .pipe(first())
          .subscribe(
            data => {
              this.router.navigate([this.returnUrl]);
            },
            error => {
              console.log(error);
            });
      }

    } else {
      this.authenticationService.login(this.login, this.password)
        .pipe(first())
        .subscribe(
          data => {
            this.router.navigate([this.returnUrl]);
          },
          error => {
            console.log(error);
          });
    }
  }

  change() {
    if(this.registration) {
      this.registration = false;
      this.login = '';
      this.password = '';
      this.title = 'Logowanie';
      this.buttonText = 'Zaloguj';
      this.link = 'Rejestracja';
    } else {
      this.registration = true;
      this.login = '';
      this.password = '';
      this.password2 = '';
      this.title = 'Rejestracja';
      this.buttonText = 'Zarejestruj';
      this.link = 'Logowanie';
    }
  }



}

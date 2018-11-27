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
  hide = true;
  returnUrl: string;

  constructor(private loginService: LoginService, private authenticationService: AuthenticationService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  signIn() {

    // this.loginService.loginUser(this.login, this.password).subscribe( res => console.log(res));
    /*
    this.loginService.loginUser(this.login, this.password).subscribe(
      () => console.log("Działa"),
      errorResponse => {
        console.log(errorResponse);
      });
      */

    this.authenticationService.login(this.login, this.password)
      .pipe(first())
      .subscribe(
        data => {
          this.router.navigate([this.returnUrl]);
        },
        error => {
          console.log(error);
          // this.alertService.error(error);
          // this.loading = false;
        });

    /*
    if (!this.login || !this.password) {
      this.hide = false;
    } else {
      alert(this.login + ' : ' + this.password);
      this.hide = true;
    }
    */
  }



}

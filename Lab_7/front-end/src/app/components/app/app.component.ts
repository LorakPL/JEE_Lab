import { Component } from '@angular/core';
import {Router} from '@angular/router';
import {AuthenticationService} from '../authentication/services/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(private router: Router, private authenticationService: AuthenticationService) {}

  logout() {
    this.authenticationService.logout();
    location.reload(true);
  }
}

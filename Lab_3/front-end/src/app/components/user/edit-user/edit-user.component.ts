import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {User} from '../../../model/user';
import {UserService} from '../services/user.service';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  user: User;

  constructor(private userService: UserService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit() {
    const link = this.route.snapshot.paramMap.get('link');
    if (link == null) {
      this.user = new User();
    } else {
      this.userService.findUser(String(link))
        .subscribe(user => this.user = user);
    }
  }

  save() {
    if (!this.user.username || !this.user.name || !this.user.secondName) {
      alert("Proszę wprowadzić wszystkie dane");
    } else if (this.user.name.match(/\d+/g) || this.user.secondName.match(/\d+/g)) {
      alert("Proszę wprowadzić poprawne dane");
    } else {
      console.log(this.user);
      this.userService.saveUser(this.user)
        .subscribe(() => this.router.navigateByUrl(''));
    }
  }
}

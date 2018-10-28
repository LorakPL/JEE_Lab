import { Component, OnInit } from '@angular/core';
import {Book} from '../../model/book';
import {Author} from '../../model/author';
import {BooksService} from '../../services/books.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ViewService} from '../../services/view.service';
import {User} from '../../model/user';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  user: User;

  constructor(private viewService: ViewService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');

    if (id == null) {
      this.user = {id: null, username: '', name: '', secondName: ''};
    } else {
      this.viewService.findUser(Number(id))
        .subscribe(user => this.user = user);
    }
  }

  save() {
    if (!this.user.username || !this.user.name || !this.user.secondName) {
      alert("Proszę wprowadzić wszystkie dane");
    } else if (this.user.name.match(/\d+/g) || this.user.secondName.match(/\d+/g)) {
      alert("Proszę wprowadzić poprawne dane");
    } else {
      this.viewService.saveUser(this.user)
        .subscribe(() => this.router.navigateByUrl(''));
    }
  }
}

import { Component, OnInit } from '@angular/core';
import {BooksService} from '../../services/books.service';
import {Author} from '../../model/author';
import {Observable} from 'rxjs/Observable';

@Component({
  selector: 'app-list-authors',
  templateUrl: './list-authors.component.html',
  styleUrls: ['./list-authors.component.css']
})
export class ListAuthorsComponent implements OnInit {

  authors: Observable<Author[]>;

  constructor(private booksService: BooksService) { }

  ngOnInit() {
    this.authors = this.booksService.findAllAuthors();
  }

}

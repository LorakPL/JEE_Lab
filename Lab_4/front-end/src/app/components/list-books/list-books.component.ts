import {Component, OnInit} from '@angular/core';
import {BooksService} from '../../services/books.service';
import {Observable} from 'rxjs/Observable';
import {Book} from '../../model/book';

@Component({
  selector: 'app-list-books',
  templateUrl: './list-books.component.html',
  styleUrls: ['./list-books.component.css']
})
export class ListBooksComponent implements OnInit {

  books: Observable<Book[]>;

  constructor(private booksService: BooksService) {
  }

  ngOnInit() {
    this.books = this.booksService.findAllBooks();
  }

  remove(book: Book) {
    this.booksService.removeBook(book)
      .subscribe(() => this.ngOnInit());
  }
}

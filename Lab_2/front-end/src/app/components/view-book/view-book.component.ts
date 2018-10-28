import {Component, OnInit} from '@angular/core';
import {Book} from '../../model/book';
import {BooksService} from '../../services/books.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-view-book',
  templateUrl: './view-book.component.html',
  styleUrls: ['./view-book.component.css']
})
export class ViewBookComponent implements OnInit {

  book: Book;

  constructor(private booksService: BooksService,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    this.booksService.findBook(Number(id)).subscribe(book => this.book = book);
  }

}

import {Component, OnInit} from '@angular/core';
import {Book} from '../../model/book';
import {BooksService} from '../../services/books.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Author} from '../../model/author';

@Component({
  selector: 'app-edit-book',
  templateUrl: './edit-book.component.html',
  styleUrls: ['./edit-book.component.css']
})
export class EditBookComponent implements OnInit {

  book: Book;
  availableAuthors: Author[];

  constructor(private booksService: BooksService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');

    if (id == null) {
      this.book = {id: null, title: '', publishDate: '', authors: []};
    } else {
      this.booksService.findBook(Number(id))
        .subscribe(book => this.book = book);
    }

    this.booksService.findAllAuthors()
      .subscribe(authors => this.availableAuthors = authors);
  }

  save() {
    this.booksService.saveBook(this.book).subscribe(
      () => this.router.navigateByUrl(''),
      errorResponse => {
        console.log(errorResponse);
        alert('Niepoprawne dane w formularzu');
      });
  }

  compareAuthors(author1: Author, author2: Author): boolean {
    return author1 && author2 ? author1.id === author2.id : author1 === author2;
  }
}

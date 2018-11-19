import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Book} from '../model/book';
import {Author} from '../model/author';

@Injectable()
export class BooksService {

  constructor(private http: HttpClient) {
  }

  findAllBooks(): Observable<Book[]> {
    return this.http.get<Book[]>('api/books');
  }

  findBook(id: number): Observable<Book> {
    return this.http.get<Book>(`api/books/${id}`);
  }

  removeBook(book: Book): Observable<any> {
    return this.http.delete(`api/books/${book.id}`);
  }

  saveBook(book: Book): Observable<any> {
    if (book.id) {
      return this.http.put(`api/books/${book.id}`, book);
    } else {
      return this.http.post('api/books/', book);
    }
  }

  findAllAuthors(): Observable<Author[]> {
    return this.http.get<Author[]>('api/authors');
  }

  findAuthor(id: number): Observable<Author> {
    return this.http.get<Author>(`api/authors/${id}`);
  }
}

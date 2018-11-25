import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {User} from '../../model/user';
import {Part} from '../../model/part';

@Injectable()
export class SharedService {

  constructor(private http: HttpClient) { }

  findAllUsers(): Observable<User[]> {
    return this.http.get<User[]>('api/users');
  }

  findAllParts(): Observable<Part[]> {
    return this.http.get<Part[]>('api/parts');
  }
}

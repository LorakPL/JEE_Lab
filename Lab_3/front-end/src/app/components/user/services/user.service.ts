import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {User} from '../../../model/user';

@Injectable()
export class UserService {

  constructor(private http: HttpClient) {}

  removeUser(link: string): Observable<any> {
    return this.http.delete('api/' + link);
  }

  findUser(link: string): Observable<User> {
    return this.http.get<User>('api/' + link);
  }

  saveUser(user: User): Observable<any> {
    if (user.id) {
      return this.http.put(`api/users/${user.id}`, user);
    } else {
      return this.http.post('api/users/', user);
    }
  }
}

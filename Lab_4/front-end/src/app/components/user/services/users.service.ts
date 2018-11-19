import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {User} from '../../../model/user';

@Injectable()
export class UsersService {

  constructor(private http: HttpClient) {
  }

  findAllUsers(): Observable<User[]> {
    return this.http.get<User[]>('api/users');
  }

  findAllUsersByName(name: string): Observable<User[]> {
    return this.http.get<User[]>(`api/users/findByName/${name}`);
  }

  findUser(id: number): Observable<User> {
    return this.http.get<User>(`api/users/${id}`);
  }

  removeUser(user: User): Observable<any> {
    return this.http.delete(`api/users/${user.id}`);
  }

  saveUser(user: User): Observable<any> {
    if (user.id) {
      return this.http.put(`api/users/${user.id}`, user);
    } else {
      return this.http.post('api/users/', user);
    }
  }
}

import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {User} from '../../../model/user';

@Injectable()
export class UsersService {

  constructor(private http: HttpClient) {
  }

  findAllUsersByLogin(name: string): Observable<User[]> {
    return this.http.get<User[]>(`api/users/findByLogin/${name}`);
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

  sortUser(column: string, direction: string): Observable<User[]> {
    return this.http.get<User[]>(`api/users/sortTable/${column}/${direction}`);
  }

  filterUser(link: string) {
    // return this.http.get<User[]>(`api/users/filter/${login}/${name}/${secondName}/${column}/${direction}`);
    // return this.http.get<User[]>(`api/users/filter?login=${login}&name=${name}&secondName=${secondName}&column=${column}&direction=${direction}`);
    return this.http.get<User[]>(`api/users/filter?${link}`);
    // api/users/filter?login=costam&name=costam&column=costam
  }
}

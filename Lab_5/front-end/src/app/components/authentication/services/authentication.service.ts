import { Injectable } from '@angular/core';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
import {Observable} from 'rxjs/Observable';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {UserPass} from '../../../model/userPass';

@Injectable()
export class AuthenticationService {

  // TODO change string to user when UserSerive will be extended
  private currentUserSubject: BehaviorSubject<string>;
  public currentUser: Observable<string>;
  private token: string;

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<string>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): string {
    return this.currentUserSubject.value;
  }

  login(username: string, password: string) {
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    let userPass = new UserPass(username, password);
    return this.http.post<any>(`api/token`, JSON.stringify(userPass),  {headers, observe: 'response'})
      .pipe(map(response => {
        this.token = response.headers.get('Authorization');

        // login successful if there's a jwt token in the response
        if (this.token) {
          // store user details and jwt token in local storage to keep user logged in between page refreshes
          localStorage.setItem('currentUser', JSON.stringify(this.token));
          this.currentUserSubject.next(this.token);
        }

        return response;
      }));
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }
}

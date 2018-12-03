import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class LoginService {

  constructor(private http: HttpClient) {
  }

  loginUser(login: string, password: string): Observable<any> {

    const tmp = { login: 'karol', password: 'karol'};

    /*
    return this.http.post('adres', tmp)

    let httpParams = new HttpParams()
      .append('login', login)
      .append('password', password);
      */
    return this.http.post('api/token', tmp);
  }
}

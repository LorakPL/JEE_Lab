import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Customer} from '../../model/customer';
import {Part} from '../../model/part';

@Injectable()
export class SharedService {

  constructor(private http: HttpClient) { }

  findAllCustomers(): Observable<Customer[]> {
    return this.http.get<Customer[]>('api/customers');
  }

  findAllParts(): Observable<Part[]> {
    return this.http.get<Part[]>('api/parts');
  }
}

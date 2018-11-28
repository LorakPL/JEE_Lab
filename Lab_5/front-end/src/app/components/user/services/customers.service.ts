import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Customer} from '../../../model/customer';
import {UserPass} from '../../../model/userPass';
import {map} from 'rxjs/operators';

@Injectable()
export class CustomersService {

  constructor(private http: HttpClient) {
  }

  findAllCustomersByName(name: string): Observable<Customer[]> {
    return this.http.get<Customer[]>(`api/customers/findByName/${name}`);
  }

  findCustomer(id: number): Observable<Customer> {
    return this.http.get<Customer>(`api/customers/${id}`);
  }

  removeCustomer(customer: Customer): Observable<any> {
    return this.http.delete(`api/customers/${customer.id}`);
  }

  saveCustomer(customer: Customer): Observable<any> {
    if (customer.id) {
      return this.http.put(`api/customers/${customer.id}`, customer);
    } else {
      return this.http.post('api/customers/', customer);
    }
  }

  changePassword(username: string, password: string) {
    /*
    const headers = new HttpHeaders({'Content-Type': 'application/json'});
    let userPass = new UserPass(username, password);
    return this.http.post<any>(`api/users/newPass`, JSON.stringify(userPass),  {headers, observe: 'response'})
      .pipe(map(response => { console.log(response); }));
      */
    let userPass = new UserPass(username, password);
    console.log(userPass);
    return this.http.post('api/customers/newPass/', JSON.stringify(userPass));
  }
}

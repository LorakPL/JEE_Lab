import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Customer} from '../../../model/customer';

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
}

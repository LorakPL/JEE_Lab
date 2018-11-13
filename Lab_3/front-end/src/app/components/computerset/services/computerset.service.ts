import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {ComputerSet} from '../../../model/computerSet';
import {PaginatorObject} from '../../../model/PaginatorObject';

@Injectable()
export class ComputersetService {

  constructor(private http: HttpClient) {}

  findAllComputerSets(): Observable<PaginatorObject> {
    return this.http.get<PaginatorObject>('api/computersets');
  }

  findComputerSetsPagination(): Observable<PaginatorObject> {
    return this.http.get<PaginatorObject>('api/computersets?limit=5');
  }

  findComputerSetsPaginationByLink(link: string): Observable<PaginatorObject> {
    return this.http.get<PaginatorObject>('api/' + link);
  }

  removeComputerSet(link: string): Observable<any> {
    return this.http.delete('api/' + link);
  }

  findComputerSet(link: string): Observable<ComputerSet> {
    return this.http.get<ComputerSet>('api/' + link);
  }

  saveComputerSet(computerSet: ComputerSet): Observable<any> {
    if (computerSet.id) {
      return this.http.put(`api/computersets/${computerSet.id}`, computerSet);
    } else {
      return this.http.post('api/computersets/', computerSet);
    }
  }

  checkIfEnoughParts(): Observable<boolean> {
    return this.http.get<boolean>('api/computersets/checkIfEnoughParts');
  }

  checkIfEnoughUsers(): Observable<boolean> {
    return this.http.get<boolean>('api/computersets/checkIfEnoughUsers');
  }
}

import {Injectable} from '@angular/core';
import {ComputerSet} from '../../../model/computerSet';
import {Observable} from 'rxjs/Observable';
import {HttpClient} from '@angular/common/http';

@Injectable()
export class ComputerSetService {

  constructor(private http: HttpClient) { }

  findAllComputerSets(): Observable<ComputerSet[]> {
    return this.http.get<ComputerSet[]>('api/computersets');
  }

  findComputerSet(id: number): Observable<ComputerSet> {
    return this.http.get<ComputerSet>(`api/computersets/${id}`);
  }

  removeComputerSet(computerSet: ComputerSet): Observable<any> {
    return this.http.delete(`api/computersets/${computerSet.id}`);
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

  getUpdate(): Observable<any> {
    return this.http.get<any>('api/listener');
  }
}

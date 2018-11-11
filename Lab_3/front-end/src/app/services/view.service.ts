import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {User} from '../model/user';
import {Part} from '../model/part';
import {PartType} from '../model/partType';
import {ComputerSet} from '../model/computerSet';

@Injectable()
export class ViewService {

  constructor(private http: HttpClient) {
  }

  findAllUsers(): Observable<User[]> {
    return this.http.get<User[]>('api/users');
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

  findAllParts(): Observable<Part[]> {
    return this.http.get<Part[]>('api/parts');
  }

  getAllPartType(type: PartType): Observable<PartType[]> {
    return this.http.get<PartType[]>(`api/parts/partType/${type}`);
  }

  findPart(id: number): Observable<Part> {
    return this.http.get<Part>(`api/parts/${id}`);
  }

  removePart(part: Part): Observable<any> {
    return this.http.delete(`api/parts/${part.id}`);
  }

  savePart(part: Part): Observable<any> {
    if (part.id) {
      return this.http.put(`api/parts/${part.id}`, part);
    } else {
      return this.http.post('api/parts/', part);
    }
  }

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
}

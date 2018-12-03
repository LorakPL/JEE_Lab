import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Customer} from '../../../model/customer';
import {Part} from '../../../model/part';
import {PartType} from '../../../model/partType';

@Injectable()
export class PartsService {

  constructor(private http: HttpClient) {
  }

  getAllPartType(): Observable<PartType[]> {
    return this.http.get<PartType[]>('api/parts/partType');
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
}

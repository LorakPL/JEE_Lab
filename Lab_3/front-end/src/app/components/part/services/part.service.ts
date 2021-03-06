import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Part} from '../../../model/part';
import {PartType} from '../../../model/partType';

@Injectable()
export class PartService {

  constructor(private http: HttpClient) {}

  removePart(link: string): Observable<any> {
    return this.http.delete('api/' + link);
  }

  findPart(link: string): Observable<Part> {
    return this.http.get<Part>('api/' + link);
  }

  getAllPartType(): Observable<PartType[]> {
    return this.http.get<PartType[]>('api/parts/partType');
  }

  savePart(part: Part): Observable<any> {
    if (part.id) {
      return this.http.put(`api/parts/${part.id}`, part);
    } else {
      return this.http.post('api/parts/', part);
    }
  }
}

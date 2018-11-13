import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {Part} from '../../../model/part';
import {PartType} from '../../../model/partType';
import {PartService} from '../services/part.service';

@Component({
  selector: 'app-list-parts',
  templateUrl: './list-parts.component.html',
  styleUrls: ['./list-parts.component.css']
})
export class ListPartsComponent implements OnInit {

  parts: Observable<Part[]>;

  constructor(private partService: PartService) { }

  ngOnInit() {
    this.parts = this.partService.findAllParts();
    this.parts.subscribe(res => console.log(res));
  }

  remove(part: Part) {
    this.partService.removePart(part.links[0].link).subscribe(() => this.ngOnInit());
  }

  getType(data) {
    return PartType[data];
  }

}

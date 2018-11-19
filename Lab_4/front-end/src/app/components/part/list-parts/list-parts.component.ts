import { Component, OnInit } from '@angular/core';
import {Part} from '../../../model/part';
import {Observable} from 'rxjs/Observable';
import {PartsService} from '../services/parts.services';
import {PartType} from '../../../model/partType';

@Component({
  selector: 'app-list-parts',
  templateUrl: './list-parts.component.html',
  styleUrls: ['./list-parts.component.css']
})
export class ListPartsComponent implements OnInit {

  parts: Observable<Part[]>;

  constructor(private partsService: PartsService) { }

  ngOnInit() {
    this.parts = this.partsService.findAllParts();
  }

  remove(part: Part) {
    this.partsService.removePart(part).subscribe(() => this.ngOnInit());
  }

  getType(data) {
    return PartType[data];
  }
}

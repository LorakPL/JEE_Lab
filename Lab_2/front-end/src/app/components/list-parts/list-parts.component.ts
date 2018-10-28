import { Component, OnInit } from '@angular/core';
import {ViewService} from '../../services/view.service';
import {Observable} from 'rxjs/Observable';
import {Part} from '../../model/part';
import {PartType} from '../../model/partType';

@Component({
  selector: 'app-list-parts',
  templateUrl: './list-parts.component.html',
  styleUrls: ['./list-parts.component.css']
})
export class ListPartsComponent implements OnInit {

  parts: Observable<Part[]>;

  constructor(private viewService: ViewService) { }

  ngOnInit() {
    this.parts = this.viewService.findAllParts();
  }

  remove(part: Part) {
    this.viewService.removePart(part)
      .subscribe(() => this.ngOnInit());
  }

  getType(data) {
    return PartType[data];
  }

}

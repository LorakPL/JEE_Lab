import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {Part} from '../../../model/part';
import {PartType} from '../../../model/partType';
import {PartService} from '../services/part.service';
import {SharedService} from '../../../shared/services/shared.service';

@Component({
  selector: 'app-list-parts',
  templateUrl: './list-parts.component.html',
  styleUrls: ['./list-parts.component.css']
})
export class ListPartsComponent implements OnInit {

  parts: Observable<Part[]>;

  constructor(private partService: PartService, private sharedService: SharedService) { }

  ngOnInit() {
    this.parts = this.sharedService.findAllParts();
    this.parts.subscribe(res => console.log(res));
  }

  remove(part: Part) {
    this.partService.removePart(part.links[0].link).subscribe(() => this.ngOnInit());
  }

  getType(data) {
    return PartType[data];
  }

}

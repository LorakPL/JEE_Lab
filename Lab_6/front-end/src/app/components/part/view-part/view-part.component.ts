import { Component, OnInit } from '@angular/core';
import {Part} from '../../../model/part';
import {PartsService} from '../services/parts.services';
import {ActivatedRoute} from '@angular/router';
import {PartType} from '../../../model/partType';

@Component({
  selector: 'app-view-part',
  templateUrl: './view-part.component.html',
  styleUrls: ['./view-part.component.css']
})
export class ViewPartComponent implements OnInit {

  part: Part;

  constructor(private partsService: PartsService, private route: ActivatedRoute) { }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    this.partsService.findPart(Number(id)).subscribe(part => this.part = part);
  }

  getType(data) {
    return PartType[data];
  }

}

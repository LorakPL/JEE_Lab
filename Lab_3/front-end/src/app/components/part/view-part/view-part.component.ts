import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Part} from '../../../model/part';
import {PartType} from '../../../model/partType';
import {PartService} from '../services/part.service';

@Component({
  selector: 'app-view-part',
  templateUrl: './view-part.component.html',
  styleUrls: ['./view-part.component.css']
})
export class ViewPartComponent implements OnInit {

  part: Part;

  constructor(private partService: PartService,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    const link = this.route.snapshot.paramMap.get('link');
    this.partService.findPart(String(link)).subscribe(part => this.part = part);
  }

  getType(data) {
    return PartType[data];
  }

}

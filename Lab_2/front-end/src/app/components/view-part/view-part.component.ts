import { Component, OnInit } from '@angular/core';
import {ViewService} from '../../services/view.service';
import {ActivatedRoute} from '@angular/router';
import {Part} from '../../model/part';
import {PartType} from '../../model/partType';

@Component({
  selector: 'app-view-part',
  templateUrl: './view-part.component.html',
  styleUrls: ['./view-part.component.css']
})
export class ViewPartComponent implements OnInit {

  part: Part;

  constructor(private viewService: ViewService,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    this.viewService.findPart(Number(id)).subscribe(part => this.part = part);
  }

  getType(data) {
    return PartType[data];
  }

}

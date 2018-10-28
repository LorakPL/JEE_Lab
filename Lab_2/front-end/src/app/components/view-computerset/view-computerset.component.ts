import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {ComputerSet} from '../../model/computerSet';
import {ViewService} from '../../services/view.service';
import {PartType} from '../../model/partType';

@Component({
  selector: 'app-view-computerset',
  templateUrl: './view-computerset.component.html',
  styleUrls: ['./view-computerset.component.css']
})
export class ViewComputersetComponent implements OnInit {

  computerSet: ComputerSet;

  constructor(private viewService: ViewService,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    this.viewService.findComputerSet(Number(id)).subscribe(computerSet => this.computerSet = computerSet);
  }

  getType(data) {
    return PartType[data];
  }

}

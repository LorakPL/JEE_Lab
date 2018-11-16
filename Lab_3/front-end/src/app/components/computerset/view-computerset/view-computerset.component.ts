import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {ComputerSet} from '../../../model/computerSet';
import {PartType} from '../../../model/partType';
import {ComputersetService} from '../services/computerset.service';

@Component({
  selector: 'app-view-computerset',
  templateUrl: './view-computerset.component.html',
  styleUrls: ['./view-computerset.component.css']
})
export class ViewComputersetComponent implements OnInit {

  computerSet: ComputerSet;

  constructor(private computersetService: ComputersetService,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    const link = this.route.snapshot.paramMap.get('link');
    this.computersetService.findComputerSet(String(link)).subscribe(computerSet => this.computerSet = computerSet);
  }

  getType(data) {
    return PartType[data];
  }

}

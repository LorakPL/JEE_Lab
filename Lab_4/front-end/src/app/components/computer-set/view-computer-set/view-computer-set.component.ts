import { Component, OnInit } from '@angular/core';
import {ComputerSetService} from '../services/computer-set.service';
import {ActivatedRoute} from '@angular/router';
import {ComputerSet} from '../../../model/computerSet';
import {PartType} from '../../../model/partType';

@Component({
  selector: 'app-view-computer-set',
  templateUrl: './view-computer-set.component.html',
  styleUrls: ['./view-computer-set.component.css']
})
export class ViewComputerSetComponent implements OnInit {

  computerSet: ComputerSet;

  constructor(private computerSetService: ComputerSetService, private route: ActivatedRoute) { }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    this.computerSetService.findComputerSet(Number(id)).subscribe(computerSet => this.computerSet = computerSet);
  }

  getType(data) {
    return PartType[data];
  }

}

import { Component, OnInit } from '@angular/core';
import {ComputerSet} from '../../../model/computerSet';
import {ComputerSetService} from '../services/computer-set.service';
import {ActivatedRoute, Router} from '@angular/router';
import {SharedService} from '../../../shared/services/shared.service';
import {PartType} from '../../../model/partType';

@Component({
  selector: 'app-edit-computer-set',
  templateUrl: './edit-computer-set.component.html',
  styleUrls: ['./edit-computer-set.component.css']
})
export class EditComputerSetComponent implements OnInit {

  computerSet: ComputerSet;
  availableParts: any[];
  availableUsers: any[];

  constructor(private computerSetService: ComputerSetService, private sharedService: SharedService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id == null) {
      this.computerSet = new ComputerSet();
    } else {
      this.computerSetService.findComputerSet(Number(id)).subscribe(computerSet => this.computerSet = computerSet);
    }

    this.sharedService.findAllParts().subscribe(parts => this.availableParts = parts);

    this.sharedService.findAllUsers().subscribe(users => this.availableUsers = users);
  }

  getType(data) {
    return PartType[data];
  }

  getPartsByType(type) {
    if (this.availableParts) {
      return this.availableParts.filter(res => res.type === type);
    }
  }

}

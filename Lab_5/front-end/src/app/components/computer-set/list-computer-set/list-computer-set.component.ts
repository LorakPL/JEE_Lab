import { Component, OnInit } from '@angular/core';
import {ComputerSet} from '../../../model/computerSet';
import {Observable} from 'rxjs/Observable';
import {ComputerSetService} from '../services/computer-set.service';
import {Router} from '@angular/router';
import {PartType} from '../../../model/partType';

@Component({
  selector: 'app-list-computer-set',
  templateUrl: './list-computer-set.component.html',
  styleUrls: ['./list-computer-set.component.css']
})
export class ListComputerSetComponent implements OnInit {

  computerSets: Observable<ComputerSet[]>;
  enoughParts: boolean;
  enoughUsers: boolean;

  constructor(private computerSetService: ComputerSetService, private router: Router) { }

  ngOnInit() {
    this.computerSets = this.computerSetService.findAllComputerSets();
    this.computerSetService.checkIfEnoughParts().subscribe(res => this.enoughParts = res);
    this.computerSetService.checkIfEnoughUsers().subscribe(res => this.enoughUsers = res);
  }

  remove(computerSet: ComputerSet) {
    this.computerSetService.removeComputerSet(computerSet)
      .subscribe(() => this.ngOnInit());
  }

  getType(data) {
    return PartType[data];
  }

  moveToEditSite() {
    if (this.enoughParts && this.enoughUsers) {
      this.router.navigateByUrl('/tmp2/new');
    } else {
      alert("Brak danych do stworzenia zestawu");
    }
  }

}

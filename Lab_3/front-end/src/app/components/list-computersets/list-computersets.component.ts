import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {ComputerSet} from '../../model/computerSet';
import {ViewService} from '../../services/view.service';
import {PartType} from '../../model/partType';
import {Router} from '@angular/router';

@Component({
  selector: 'app-list-computersets',
  templateUrl: './list-computersets.component.html',
  styleUrls: ['./list-computersets.component.css']
})
export class ListComputersetsComponent implements OnInit {

  computerSets: Observable<ComputerSet[]>;
  enoughParts: boolean;
  enoughUsers: boolean;

  constructor(private viewService: ViewService, private router: Router) {
  }

  ngOnInit() {
    this.computerSets = this.viewService.findAllComputerSets();
    this.viewService.checkIfEnoughParts().subscribe(res => this.enoughParts = res);
    this.viewService.checkIfEnoughUsers().subscribe(res => this.enoughUsers = res);
  }

  remove(computerSet: ComputerSet) {
    this.viewService.removeComputerSet(computerSet)
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

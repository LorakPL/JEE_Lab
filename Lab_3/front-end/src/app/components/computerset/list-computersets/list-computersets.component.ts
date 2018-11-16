import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {ComputerSet} from '../../../model/computerSet';
import {PartType} from '../../../model/partType';
import {Router} from '@angular/router';
import {ComputersetService} from '../services/computerset.service';
import {PaginatorObject} from '../../../model/PaginatorObject';
import {User} from '../../../model/user';
import {SharedService} from '../../../shared/services/shared.service';

@Component({
  selector: 'app-list-computersets',
  templateUrl: './list-computersets.component.html',
  styleUrls: ['./list-computersets.component.css']
})
export class ListComputersetsComponent implements OnInit {

  computerSets: ComputerSet[];
  paginatorObject: Observable<PaginatorObject>;
  enoughParts: boolean;
  enoughUsers: boolean;

  constructor(private computersetService: ComputersetService, private router: Router, private shardedService: SharedService) {
  }

  ngOnInit() {
    this.paginatorObject = this.computersetService.findAllComputerSets();
    this.paginatorObject.subscribe(res => {
      this.computerSets = res.computerSets;
      console.log(this.computerSets);
    });
    this.computersetService.checkIfEnoughParts().subscribe(res => this.enoughParts = res);
    this.computersetService.checkIfEnoughUsers().subscribe(res => this.enoughUsers = res);
  }

  remove(computerSet: ComputerSet) {
    this.computersetService.removeComputerSet(String(computerSet.links[0].link))
      .subscribe(() => this.ngOnInit());
  }

  getType(data) {
    return PartType[data];
  }

  getUserName(link: string) {
    var user = new User();
    this.shardedService.findUser(link).subscribe(res => {
      user = res;
      return user.name;
    });
    // return user.name;
  }

  moveToEditSite() {
    if (this.enoughParts && this.enoughUsers) {
      this.router.navigateByUrl('/tmp2/new');
    } else {
      alert("Brak danych do stworzenia zestawu");
    }
  }

}

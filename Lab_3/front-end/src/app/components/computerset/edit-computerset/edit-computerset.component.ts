import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {PartType} from '../../../model/partType';
import {ComputerSet} from '../../../model/computerSet';
import {ComputersetService} from '../services/computerset.service';
import {SharedService} from '../../../shared/services/shared.service';
import {User} from '../../../model/user';

@Component({
  selector: 'app-edit-computerset',
  templateUrl: './edit-computerset.component.html',
  styleUrls: ['./edit-computerset.component.css']
})
export class EditComputersetComponent implements OnInit {

  computerSet: ComputerSet;
  availableParts: any[];
  availableUsers: any[];

  constructor(private computersetService: ComputersetService,
              private sharedService: SharedService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {
    const link = this.route.snapshot.paramMap.get('link');
    if (link == null) {
      this.computerSet = new ComputerSet();
      // this.computerSet = {id: null, user: new User(), parts: [], links: []};
    } else {
      this.computersetService.findComputerSet(String(link))
        .subscribe(computerSet => this.computerSet = computerSet);
    }

    this.sharedService.findAllParts()
      .subscribe(parts => this.availableParts = parts);

    this.sharedService.findAllUsers()
      .subscribe(users => this.availableUsers = users);
  }

  getType(data) {
    return PartType[data];
  }

  getPartsByType(type) {
    if (this.availableParts) {
      return this.availableParts.filter(res => res.type === type);
    }
  }


  save() {
    if(!this.computerSet.user || !(this.computerSet.parts.length > 7)) {
      alert("Proszę wprowadzić wszystkie dane");
    } else {
      this.computersetService.saveComputerSet(this.computerSet)
        .subscribe(() => this.router.navigateByUrl('/computerSetsLink'));
      console.log(this.computerSet);
    }
  }

}

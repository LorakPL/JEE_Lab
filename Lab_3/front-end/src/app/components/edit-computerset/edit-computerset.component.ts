import { Component, OnInit } from '@angular/core';
import {ViewService} from '../../services/view.service';
import {ActivatedRoute, Router} from '@angular/router';
import {PartType} from '../../model/partType';
import {ComputerSet} from '../../model/computerSet';
import {User} from '../../model/user';

@Component({
  selector: 'app-edit-computerset',
  templateUrl: './edit-computerset.component.html',
  styleUrls: ['./edit-computerset.component.css']
})
export class EditComputersetComponent implements OnInit {

  computerSet: ComputerSet;
  availableParts: any[];
  availableUsers: any[];

  constructor(private viewService: ViewService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id == null) {
      this.computerSet = {id: null, user: new User(), parts: []};
    } else {
      this.viewService.findComputerSet(Number(id))
        .subscribe(computerSet => this.computerSet = computerSet);
    }

    this.viewService.findAllParts()
      .subscribe(parts => this.availableParts = parts);

    this.viewService.findAllUsers()
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
      this.viewService.saveComputerSet(this.computerSet)
        .subscribe(() => this.router.navigateByUrl('/computerSetsLink'));
    }
  }

}

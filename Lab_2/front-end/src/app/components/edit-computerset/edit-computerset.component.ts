import { Component, OnInit } from '@angular/core';
import {Part} from '../../model/part';
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
  partType: PartType;

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

  getPartType(data) {
    return data;
  }

  getPartsByType(type) {
    if (this.availableParts) {
      return this.availableParts.filter(res => res.type === type);
    }
  }


  save() {
    // console.log(this.computerSet);

    if(!this.computerSet.user || !(this.computerSet.parts.length > 7)) {
      alert("Proszę wprowadzić wszystkie dane");
    } else {
      this.viewService.saveComputerSet(this.computerSet)
        .subscribe(() => this.router.navigateByUrl('/computerSetsLink'));
    }

    /*
    if (!this.part.name || !this.part.price || !this.part.type) {
      alert("Proszę wprowadzić wszystkie dane");
    } else if (!this.part.price.match(/\d+/g)) {
      alert("Proszę wprowadzić poprawne dane");
    } else {
      this.viewService.savePart(this.part)
        .subscribe(() => this.router.navigateByUrl('/partsLink'));
    }
    */
  }

}

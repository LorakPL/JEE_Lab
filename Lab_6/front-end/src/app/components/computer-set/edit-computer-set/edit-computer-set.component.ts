import { Component, OnInit } from '@angular/core';
import {ComputerSet} from '../../../model/computerSet';
import {ComputerSetService} from '../services/computer-set.service';
import {ActivatedRoute, Router} from '@angular/router';
import {SharedService} from '../../../shared/services/shared.service';
import {PartType} from '../../../model/partType';
import {User} from '../../../model/user';

@Component({
  selector: 'app-edit-computer-set',
  templateUrl: './edit-computer-set.component.html',
  styleUrls: ['./edit-computer-set.component.css']
})
export class EditComputerSetComponent implements OnInit {

  computerSet: ComputerSet;
  availableParts: any[];
  availableUsers: any[];
  userError = '';
  motherboardError = '';
  graphicCardError = '';
  hardDriveError = '';
  ramError = '';
  caseError = '';
  coolingError = '';
  powerSupplyError = '';
  processorError = '';
  new = true;

  constructor(private computerSetService: ComputerSetService, private sharedService: SharedService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id == null) {
      this.computerSet = new ComputerSet();
    } else {
      this.new = false;
      this.computerSetService.findComputerSet(Number(id)).subscribe(computerSet => this.computerSet = computerSet);
    }

    this.sharedService.findAllParts().subscribe(parts => this.availableParts = parts);

    this.sharedService.findAllUsersForComputerSets().subscribe(users => this.availableUsers = users);
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
    this.computerSet.date = new Date();
    if (!this.computerSet.user.login) {
      this.computerSet.user = this.getUserByLogin(localStorage.getItem('currentUserName').replace(/"/g, ''));
    }
    this.computerSetService.saveComputerSet(this.computerSet).subscribe(
      () => this.router.navigateByUrl('/computerSetsLink'),
      errorResponse => {
        (errorResponse.error.parameterViolations.some(res => res.path.includes('user'))) ?
          this.userError = 'Wartość w polu użytkownik nie może być pusta' : this.userError = '';
        (errorResponse.error.parameterViolations.some(res => res.path.includes('parts'))) ? this.validateParts() : this.clearParts();
      });
  }

  validateParts() {
    !this.computerSet.parts[0] ? this.motherboardError = 'Proszę wybrać płytę główną' : this.motherboardError = '';
    !this.computerSet.parts[1] ? this.graphicCardError = 'Proszę wybrać kartę graficzną' : this.graphicCardError = '';
    !this.computerSet.parts[2] ? this.hardDriveError = 'Proszę wybrać dysk twardy' : this.hardDriveError = '';
    !this.computerSet.parts[3] ? this.ramError = 'Proszę wybrać pamięć ram' : this.ramError = '';
    !this.computerSet.parts[4] ? this.caseError = 'Proszę wybrać pamięć obudowę' : this.caseError = '';
    !this.computerSet.parts[5] ? this.coolingError = 'Proszę wybrać chłodzenie' : this.coolingError = '';
    !this.computerSet.parts[6] ? this.powerSupplyError = 'Proszę wybrać zasilacz' : this.powerSupplyError = '';
    !this.computerSet.parts[7] ? this.processorError = 'Proszę wybrać procesor' : this.processorError = '';

  }

  clearParts() {
    this.motherboardError = '';
    this.graphicCardError = '';
    this.hardDriveError = '';
    this.ramError = '';
    this.caseError = '';
    this.coolingError = '';
    this.powerSupplyError = '';
    this.processorError = '';
  }

  getUserByLogin(login) {
    var user = new User();
    this.availableUsers.forEach(function (item) {
      if (item.login === login) {
        user = item;
      }
    });
    return user;
  }

}

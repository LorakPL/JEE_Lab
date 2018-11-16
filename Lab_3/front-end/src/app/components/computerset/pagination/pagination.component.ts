import { Component, OnInit } from '@angular/core';
import {ComputerSet} from '../../../model/computerSet';
import {Observable} from 'rxjs/Observable';
import {PaginatorObject} from '../../../model/PaginatorObject';
import {ComputersetService} from '../services/computerset.service';
import {Link} from '../../../model/link';
import {PartType} from '../../../model/partType';

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.css']
})
export class PaginationComponent implements OnInit {
  computerSets: ComputerSet[];
  links: Link[];
  paginatorObject: Observable<PaginatorObject>;
  prevButton = true;
  nextButton = false;

  constructor(private computersetService: ComputersetService) { }

  ngOnInit() {
    this.paginatorObject = this.computersetService.findComputerSetsPagination();
    this.paginatorObject.subscribe(res => {
      this.computerSets = res.computerSets;
      this.links = res.links;
      console.log(this.computerSets);
      console.log(this.links);
    });
  }

  changePage() {
    if(this.links[0].rel.includes('Next')) {
      this.prevButton = false;
      this.nextButton = true;
    } else {
      this.prevButton = true;
      this.nextButton = false;
    }
    this.paginatorObject = this.computersetService.findComputerSetsPaginationByLink(this.links[0].link);
    this.paginatorObject.subscribe(res => {
      this.computerSets = res.computerSets;
      this.links = res.links;
    });
  }

  getType(data) {
    return PartType[data];
  }

}

import {Component, OnInit} from '@angular/core';
import {ViewService} from '../../services/view.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Part} from '../../model/part';
import {PartType} from '../../model/partType';

@Component({
  selector: 'app-edit-part',
  templateUrl: './edit-part.component.html',
  styleUrls: ['./edit-part.component.css']
})
export class EditPartComponent implements OnInit {

  part: Part;
  availableTypes: any[];

  constructor(private viewService: ViewService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id == null) {
      this.part = {id: null, name: '', price: '', type: null};
    } else {
      this.viewService.findPart(Number(id))
        .subscribe(part => this.part = part);
    }

    this.viewService.getAllPartType(PartType.GRAPHIC_CARD)
      .subscribe(partTypes => this.availableTypes = partTypes);
  }

  getType(data) {
    return PartType[data];
  }

  getPartType(data) {
    return data;
  }


  save() {
    if (!this.part.name || !this.part.price || !this.part.type) {
      alert("Proszę wprowadzić wszystkie dane");
    } else if (!this.part.price.match(/\d+/g)) {
      alert("Proszę wprowadzić poprawne dane");
    } else {
      this.viewService.savePart(this.part)
        .subscribe(() => this.router.navigateByUrl('/partsLink'));
    }
  }

}

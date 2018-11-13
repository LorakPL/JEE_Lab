import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Part} from '../../../model/part';
import {PartType} from '../../../model/partType';
import {PartService} from '../services/part.service';

@Component({
  selector: 'app-edit-part',
  templateUrl: './edit-part.component.html',
  styleUrls: ['./edit-part.component.css']
})
export class EditPartComponent implements OnInit {

  part: Part;
  availableTypes: any[];

  constructor(private partService: PartService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {
    const link = this.route.snapshot.paramMap.get('link');
    if (link == null) {
      this.part = new Part();
    } else {
      this.partService.findPart(String(link))
        .subscribe(part => this.part = part);
    }

    this.partService.getAllPartType(PartType.GRAPHIC_CARD)
      .subscribe(partTypes => this.availableTypes = partTypes);
  }

  getType(data) {
    return PartType[data];
  }

  save() {
    if (!this.part.name || !this.part.price || !this.part.type) {
      alert("Proszę wprowadzić wszystkie dane");
    } else if (!this.part.price.match('^\\d+$')) {
      alert("Proszę wprowadzić poprawne dane");
    } else {
      this.partService.savePart(this.part)
        .subscribe(() => this.router.navigateByUrl('/partsLink'));
    }
  }

}

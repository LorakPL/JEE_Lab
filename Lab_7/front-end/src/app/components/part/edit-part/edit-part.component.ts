import { Component, OnInit } from '@angular/core';
import {Part} from '../../../model/part';
import {PartsService} from '../services/parts.services';
import {ActivatedRoute, Router} from '@angular/router';
import {PartType} from '../../../model/partType';

@Component({
  selector: 'app-edit-part',
  templateUrl: './edit-part.component.html',
  styleUrls: ['./edit-part.component.css']
})
export class EditPartComponent implements OnInit {

  part: Part;
  availableTypes: any[];
  nameError = '';
  priceError = '';
  typeError = '';
  disabled = false;

  constructor(private partsService: PartsService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id == null) {
      this.part = new Part();
    } else {
      this.disabled = true;
      this.partsService.findPart(Number(id)).subscribe(part => this.part = part);
    }

    this.partsService.getAllPartType().subscribe(partTypes => this.availableTypes = partTypes);
  }

  getType(data) {
    return PartType[data];
  }

  save() {
    this.partsService.savePart(this.part).subscribe(
      () => this.router.navigateByUrl('/partsLink'),
      errorResponse => {
        (errorResponse.error.parameterViolations.some(res => res.path.includes('name'))) ?
          this.nameError = 'Wartość w polu nazwa musi się mieścić w przedziale 2 - 100 znaków' : this.nameError = '';
        (errorResponse.error.parameterViolations.some(res => res.path.includes('price'))) ?
          this.priceError = 'Wartość w polu cena musi składać się tylko z liczb całkowitych' : this.priceError = '';
        (errorResponse.error.parameterViolations.some(res => res.path.includes('type'))) ?
          this.typeError = 'Wartość w polu typ nie może być pusta' : this.typeError = '';
      });
  }

}

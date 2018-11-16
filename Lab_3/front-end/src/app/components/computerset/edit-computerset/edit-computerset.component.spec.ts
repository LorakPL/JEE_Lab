import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditComputersetComponent } from './edit-computerset.component';

describe('EditComputersetComponent', () => {
  let component: EditComputersetComponent;
  let fixture: ComponentFixture<EditComputersetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditComputersetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditComputersetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

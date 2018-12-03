import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditComputerSetComponent } from './edit-computer-set.component';

describe('EditComputerSetComponent', () => {
  let component: EditComputerSetComponent;
  let fixture: ComponentFixture<EditComputerSetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditComputerSetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditComputerSetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

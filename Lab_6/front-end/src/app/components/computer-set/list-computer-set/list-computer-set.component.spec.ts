import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListComputerSetComponent } from './list-computer-set.component';

describe('ListComputerSetComponent', () => {
  let component: ListComputerSetComponent;
  let fixture: ComponentFixture<ListComputerSetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListComputerSetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListComputerSetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

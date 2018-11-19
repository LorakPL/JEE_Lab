import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewComputerSetComponent } from './view-computer-set.component';

describe('ViewComputerSetComponent', () => {
  let component: ViewComputerSetComponent;
  let fixture: ComponentFixture<ViewComputerSetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewComputerSetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewComputerSetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

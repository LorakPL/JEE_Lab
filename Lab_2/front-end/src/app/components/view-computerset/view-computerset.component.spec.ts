import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewComputersetComponent } from './view-computerset.component';

describe('ViewComputersetComponent', () => {
  let component: ViewComputersetComponent;
  let fixture: ComponentFixture<ViewComputersetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewComputersetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewComputersetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListComputersetsComponent } from './list-computersets.component';

describe('ListComputersetsComponent', () => {
  let component: ListComputersetsComponent;
  let fixture: ComponentFixture<ListComputersetsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListComputersetsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListComputersetsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

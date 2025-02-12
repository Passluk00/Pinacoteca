import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListOpereComponent } from './list-opere.component';

describe('ListOpereComponent', () => {
  let component: ListOpereComponent;
  let fixture: ComponentFixture<ListOpereComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListOpereComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListOpereComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

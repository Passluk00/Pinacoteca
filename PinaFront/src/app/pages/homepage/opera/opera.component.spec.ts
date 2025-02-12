import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OperaComponent } from './opera.component';

describe('OperaComponent', () => {
  let component: OperaComponent;
  let fixture: ComponentFixture<OperaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OperaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OperaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

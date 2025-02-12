import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CuratoreComponent } from './curatore.component';

describe('CuratoreComponent', () => {
  let component: CuratoreComponent;
  let fixture: ComponentFixture<CuratoreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CuratoreComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CuratoreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListArtistiComponent } from './list-artisti.component';

describe('ListArtistiComponent', () => {
  let component: ListArtistiComponent;
  let fixture: ComponentFixture<ListArtistiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListArtistiComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListArtistiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

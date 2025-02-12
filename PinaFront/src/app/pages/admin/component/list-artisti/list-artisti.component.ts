import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {AdminControllerService} from "../../../../services/services/admin-controller.service";
import {NgForOf, NgIf} from "@angular/common";
import {ArtistaFront} from "../../../../services/models/artista-front";
import {ArtistaFrontImg} from "../../../../services/models/artista-front-img";


@Component({
  selector: 'app-list-artisti',
  standalone: true,
  imports: [
    NgForOf,
    NgIf
  ],
  templateUrl: './list-artisti.component.html',
  styleUrl: './list-artisti.component.scss'
})
export class ListArtistiComponent implements OnInit{

  constructor(
    private adminService: AdminControllerService,
    private cdr: ChangeDetectorRef
  ) {
  }

  lista:ArtistaFrontImg[] = []


  ngOnInit() {
    this.getAllArtist()
  }


  getAllArtist(){

    this.adminService.getAllArtistForDisplay().subscribe({
      next: (res) => {
        this.lista = res
        this.cdr.detectChanges()
      },
      error:(err) => {
        console.error("errore fetch Artisti: "+err);
      }
    })

  }

}

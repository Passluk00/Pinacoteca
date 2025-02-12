import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, RouterLink} from "@angular/router";
import {ArtistaFront} from "../../../services/models/artista-front";
import {FrontEndControllerService} from "../../../services/services/front-end-controller.service";
import {ArtistaFrontImg} from "../../../services/models/artista-front-img";
import {Artista} from "../../../services/models/artista";
import {NgIf} from "@angular/common";
import {ArtistaCut} from "../../../services/models/artista-cut";

@Component({
  selector: 'app-artista',
  standalone: true,
  imports: [
    RouterLink,
    NgIf
  ],
  templateUrl: './artista.component.html',
  styleUrl: './artista.component.scss'
})
export class ArtistaComponent implements OnInit{

  constructor(
    private route:ActivatedRoute,
    private frontEndservice: FrontEndControllerService
  ) {
  }


  userId: number | undefined;
  artista: ArtistaCut ={}

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.userId = +params['id']
    })
    this.getData()
  }

  getData(){
    if(this.userId != undefined){
      this.frontEndservice.getArtista({
        idArt: this.userId
      }).subscribe({
        next: (res) => {
          this.artista = res
        },
        error: (err) => {
          console.error("errore fetch dati artista "+err)
        }
      })

    }
  }

}

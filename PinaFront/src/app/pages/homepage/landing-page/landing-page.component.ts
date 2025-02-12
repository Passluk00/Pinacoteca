import {Component, OnInit} from '@angular/core';
import {NgbDropdown, NgbDropdownItem, NgbDropdownMenu, NgbDropdownToggle} from "@ng-bootstrap/ng-bootstrap";
import {NgForOf, NgIf} from "@angular/common";
import {FrontEndControllerService} from "../../../services/services/front-end-controller.service";
import {AreaFront} from "../../../services/models/area-front";


@Component({
  selector: 'app-landing-page',
  standalone: true,
  imports: [
    NgbDropdown,
    NgbDropdownToggle,
    NgbDropdownMenu,
    NgbDropdownItem,
    NgForOf,
    NgIf
  ],
  templateUrl: './landing-page.component.html',
  styleUrl: './landing-page.component.scss'
})
export class LandingPageComponent implements OnInit{

  constructor(
    private frontendService: FrontEndControllerService
  ) {
  }

  categorie : AreaFront[] = []



  ngOnInit(): void {
    this.getAllArea()
  }


  getAllArea(){

    this.frontendService.getAree().subscribe({
      next:(res) => {
        this.categorie = res;
      },
      error:(err)=> {
        console.error("errore fetch dati aree: "+err)
      }
    })

  }



}

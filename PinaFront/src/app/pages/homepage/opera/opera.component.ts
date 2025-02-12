import {Component, OnInit} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {OperaFront} from "../../../services/models/opera-front";
import {ActivatedRoute, RouterLink} from "@angular/router";
import {FrontEndControllerService} from "../../../services/services/front-end-controller.service";

@Component({
  selector: 'app-opera',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    FormsModule,
    RouterLink
  ],
  templateUrl: './opera.component.html',
  styleUrl: './opera.component.scss'
})
export class OperaComponent implements OnInit{

  constructor(
    private route: ActivatedRoute,
    private frontEdnService: FrontEndControllerService
  ) {
  }

  opera:OperaFront = {}
  userId: number | undefined;


  ngOnInit() {
    this.route.params.subscribe(params => {
      this.userId = +params['id']
    })
    this.getData()

  }

  getData(){
    if(this.userId != undefined){
      this.frontEdnService.getOpera({
        idOpera: this.userId
      }).subscribe({
        next: (res) => {
          this.opera = res
        },
        error:() => {
          console.error("errore fetch dati opera")
        }
      })
    }
  }






}

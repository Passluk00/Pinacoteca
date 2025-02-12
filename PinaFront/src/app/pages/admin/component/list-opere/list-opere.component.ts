import {Component, OnInit} from '@angular/core';
import {AdminControllerService} from "../../../../services/services/admin-controller.service";
import {NgForOf} from "@angular/common";
import {Opera} from "../../../../services/models/opera";
import {OperaFront} from "../../../../services/models/opera-front";


@Component({
  selector: 'app-list-opere',
  standalone: true,
  imports: [
    NgForOf
  ],
  templateUrl: './list-opere.component.html',
  styleUrl: './list-opere.component.scss'
})
export class ListOpereComponent implements OnInit{

  constructor(
    private adminService: AdminControllerService
  ) {
  }

  lista :OperaFront[] = []

    ngOnInit() {
      this.getAllOpere()
    }


    getAllOpere(){

      this.adminService.getAllOpereForDisplay().subscribe({
        next: (res) => {
          this.lista = res
          console.log("ok")
        },
        error:() => {
          console.error("errore fetch dati opere admin")
        }
      })

    }


}

import { Component } from '@angular/core';
import {NgForOf, NgIf} from "@angular/common";
import {FaIconComponent} from "@fortawesome/angular-fontawesome";
import {FormsModule} from "@angular/forms";
import {RegistrationRequest} from "../../services/models/registration-request";
import {Router} from "@angular/router";
import {AuthenticationService} from "../../services/services/authentication.service";

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    NgIf,
    FaIconComponent,
    FormsModule,
    NgForOf
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {


  errorMessage: Array<String> = [];
  authRequest: RegistrationRequest = {
    email: '',
    password: '' ,
    nome: '',
    cognome:'',
    codiceFiscale:'',
    dataNascita:'',
    luogoDiNascita:'',
  }


  constructor(
    private router: Router,
    private authService: AuthenticationService
  ) { }

  register(){

    this.errorMessage = [];
    this.authService.register({
      body: this.authRequest
    }).subscribe({
      next: (res) => {
        this.router.navigate(['login']);     // TODO DA Cambiare con pagina di auth codice
      },
      error: (err) => {
        console.log(err);
        if (err.error.validationErrors) {
          this.errorMessage = err.error.validationErrors;
        }else{
          this.errorMessage.push(err.error.error);
        }
      }
    })
  }


}

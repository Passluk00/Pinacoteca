import {Component} from "@angular/core";
import {NgForOf, NgIf} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {AuthenticationService} from "../../services/services/authentication.service";
import {TokenService} from "../../services/token/token.service";
import {AuthenticationRequest} from "../../services/models/authentication-request";
import {HttpClientModule} from "@angular/common/http";
import {FaIconComponent} from "@fortawesome/angular-fontawesome";
import {Router, RouterLink} from "@angular/router";
import {faKey, faUser} from "@fortawesome/free-solid-svg-icons";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    NgIf,
    NgForOf,
    FormsModule,
    FaIconComponent,
    RouterLink,


  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {


  authRequest: AuthenticationRequest = {email:'', password:''}
  errorMessage: Array<String> = [];

  constructor(
    private router:Router,
    private authService: AuthenticationService,
    private tokenService: TokenService,
  ) {}


  login() {
    this.errorMessage = [];
    localStorage.removeItem("token")
    this.authService.authenticate({
      body: this.authRequest
    }).subscribe({

      next: (res) => {

        var toc = res.token;
        if(toc != undefined) {
          //mette il token nel localStorage
          this.tokenService.token = toc;
        }
        this.router.navigate(['']);
      },
      error: (err) => {
        console.log(err);
        if (err.error.validationErrors) {
          this.errorMessage = err.error.validationErrors;
        } else {
          this.errorMessage.push(err.error.error);
        }
      }
    });
  }








  register() {
    this.router.navigate(['register']);
  }

  forgotPassword() {
    this.router.navigate(['forgot-password']);
  }


  protected readonly faUser = faUser;
  protected readonly faKey = faKey;
}

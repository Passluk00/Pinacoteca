import {ChangeDetectorRef, Component, HostListener, Inject, OnInit, PLATFORM_ID} from '@angular/core';
import {CuratorControllerService} from "../../services/services/curator-controller.service";
import {UserResponse} from "../../services/models/user-response";
import {isPlatformBrowser, NgClass, NgIf} from "@angular/common";
import {AuthenticationService} from "../../services/services/authentication.service";
import {FaIconComponent} from "@fortawesome/angular-fontawesome";
import {Router, RouterLink} from "@angular/router";
import {faArrowRightFromBracket, faShop, faTriangleExclamation, faUser} from "@fortawesome/free-solid-svg-icons";

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    NgIf,
    FaIconComponent,
    RouterLink,
    NgClass
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss'
})
export class NavbarComponent implements OnInit{
  constructor(
    private router:Router,
    private userService: CuratorControllerService,
    private authService: AuthenticationService,
    private cdr: ChangeDetectorRef,
    @Inject(PLATFORM_ID) private platformId: Object
  ) {
  }

  userData: UserResponse = {}
  isLogged: boolean = false
  isDropdownOpen: boolean = false
  isAdmin: boolean = true


  ngOnInit() {
    this.checkLoginStatus()
    this.showData()
    this.cdr.detectChanges()
  }

  showData(){

    if(this.userData != undefined){

      console.error("nome: "+ this.userData?.nome)

    }

  }


  getUserData(){

    this.userService.getCuratorData().subscribe({
      next: (res) => {
        this.userData = res
        this.cdr.detectChanges()
      },
      error: (err) => {
        console.log(err);
      }
    })
  }


  toggleDropdown(){
    this.isDropdownOpen = !this.isDropdownOpen;
  }

  @HostListener('document:click', ['$event'])
  onClick(event: MouseEvent){
    const target = event.target as HTMLElement;
    const clickedInside = target.closest('.relative');
    if(!clickedInside){
      this.isDropdownOpen = false;
    }
  }

  checkLoginStatus(): void{
    if(isPlatformBrowser(this.platformId)) {
      const toc = this.getToken()

      if(toc) {
        this.authService.check({
          token: toc
        }).subscribe({
          next: (res) => {
            this.isLogged = res;
            this.getUserData()
            this.cdr.detectChanges();

          },
          error: (err) => {
            console.error("Errore nel verificare se sei loggato: " + err)
          }
        })
      }
    }
  }


  getToken(): string | null{
    if(typeof localStorage.getItem('token') !== 'undefined' && typeof window !=='undefined'){
      return localStorage.getItem("token")
    }
    return null
  }


  goToManage() {
    this.router.navigate(['curator']);
  }

  goToAdminPage() {
    this.router.navigate(['admin']);
  }

  logout() {
    if(typeof localStorage.getItem('token') !== 'undefined'&& typeof window !=='undefined'){
      localStorage.removeItem("token")
      window.location.reload()
    }
  }

  protected readonly faShop = faShop;
  protected readonly faUser = faUser;
  protected readonly faArrowRightFromBracket = faArrowRightFromBracket;
  protected readonly faTriangleExclamation = faTriangleExclamation;
}

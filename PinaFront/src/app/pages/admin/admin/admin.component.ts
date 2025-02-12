import {Component, OnInit} from '@angular/core';
import {NavbarComponent} from "../../../components/navbar/navbar.component";
import {FooterComponent} from "../../../components/footer/footer.component";
import {NgForOf, NgIf} from "@angular/common";
import {FaIconComponent} from "@fortawesome/angular-fontawesome";
import {faMagnifyingGlass} from "@fortawesome/free-solid-svg-icons";
import {FormsModule} from "@angular/forms";
import {ArtistaRequest} from "../../../services/models/artista-request";
import {AdminControllerService} from "../../../services/services/admin-controller.service";
import {OperaRequest} from "../../../services/models/opera-request";
import {ListArtist} from "../../../services/models/list-artist";
import {ListOpereComponent} from "../component/list-opere/list-opere.component";
import {ListArtistiComponent} from "../component/list-artisti/list-artisti.component";
import {Artista} from "../../../services/models/artista";

import {OperaFront} from "../../../services/models/opera-front";
import {AreaRequest} from "../../../services/models/area-request";
import {AreaFront} from "../../../services/models/area-front";
import {FrontEndControllerService} from "../../../services/services/front-end-controller.service";
import {UserFront} from "../../../services/models/user-front";

@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [
    NavbarComponent,
    FooterComponent,
    NgIf,
    FaIconComponent,
    FormsModule,
    NgForOf,
    ListOpereComponent,
    ListArtistiComponent
  ],
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.scss'
})
export class AdminComponent implements OnInit{

  constructor(
    private adminService: AdminControllerService,
    private frontService: FrontEndControllerService
  ) {
  }


  visibleElement: string | null = 'Aree';      // sostituire con Allopere
  isDragging = false;
  previewImage: string | null = null;
  previewImageMod: string | null = null;
  previewImageDel: string | null = null;
  previewImageModOp: string | null = null;
  previewImageDelOp: string | null = null;

  uploadFile: File | null = null;
  toShow:any;
  newArtista: ArtistaRequest = {cognome: "", dataDiMorte: "", dataDiNascita: "", luogoDiNascita: "", name: ""}
  newOpera: OperaRequest = { title:"", anno: 0, tecnica:""}
  artistaSelezionato:number = 0;
  data:ListArtist = {}
  artistaDaMod: number = 0;
  artDaMod: Artista = {}
  artistaDaDel: number = 0
  artDaDel: Artista = {}

  listaOp:OperaFront[] = []
  opDaMod: OperaFront = {}
  operaDaMod: number = 0
  artOpSelected: number =0


  operaDaDel: number = 0;
  opDaDel: OperaFront = {}




  ngOnInit() {
    this.getArtist()
    this.getAllOpere()
    this.prendiDatiAree()
    this.getAllOpereFree()
    this.getCuratoriLiberi()
  }




  eliminaArtista(){

    if(this.previewImageDel != null && this.artistaDaDel != null){

      this.adminService.deleteArtista({
        idArt: this.artistaDaMod,
      }).subscribe({
        next: () => {
          console.error("inviato")
        },
        error:(err) => {
          console.error("Errore del dati: "+ err)
        }
      })

    }


  }

  modificaArtista(){

    if(this.previewImageMod != null && this.artistaDaMod != null){

      this.adminService.modArtista({
        idArt: this.artistaDaMod,
        body: this.artDaMod
      }).subscribe({
        next: () => {
          console.error("inviato")
        },
        error:(err) => {
          console.error("Errore mod dati: "+ err)
        }
      })

    }
  }


  setupDatiDelOp(){
    if(this.opDaDel.img != null) {
      this.previewImageDelOp = this.opDaDel?.img
    }
  }


  setupDati(){
    if(this.artDaMod.immagine != null) {
      this.previewImageMod = this.artDaMod?.immagine
    }
  }

  setupDatiDel(){
    if(this.artDaDel.immagine != null) {
      this.previewImageDel = this.artDaDel?.immagine
    }
  }

  setupDatiModOp(){
    if(this.opDaMod.img != null) {
      this.previewImageModOp = this.opDaMod?.img
    }
  }



  prendiDatiDel(){

    if(this.artistaDaMod != 0){

      this.adminService.getArtistaDaMod({
        idArt: this.artistaDaMod
      }).subscribe({
        next: (res) => {
          this.artDaDel = res
          this.setupDatiDel();
        },
        error:() => {
          console.error("Impossibile predere dati da mod")
        }
      })

    }

  }

  prendiDati(){

    if(this.artistaDaMod != 0){

      this.adminService.getArtistaDaMod({
        idArt: this.artistaDaMod
      }).subscribe({
        next: (res) => {
          this.artDaMod = res
          this.setupDati();
        },
        error:() => {
          console.error("Impossibile predere dati da mod")
        }
      })

    }

  }



  getArtist(){

    this.adminService.getAllArtistForMenu().subscribe({
      next:(res) => {
        this.data = res;
      },
      error:(err) => {
        console.error("errore fetch dati artisti: "+err)
      }
    })

  }

  showElement(element:string, event:Event){
    event.preventDefault()
    this.visibleElement = element;
    this.removeImage()
  }

  nuovaOpera(){
    if(this.artistaSelezionato != 0 && this.uploadFile != null && this.newOpera != null) {
      this.adminService.creaOpera({
        idArtista: this.artistaSelezionato,
        body:{
          file:this.uploadFile,
          req: this.newOpera
        }
      }).subscribe({
        next:() => {
          console.error("inviato")
          this.removeImage()
        },
        error:(err) => {
          console.error("errore invio opera: "+err)
        }
      })
    }

  }

  nuovoArtista(){

    if(this.newArtista != undefined && this.uploadFile != null){

      this.adminService.creaArtista({
        body:{
          file: this.uploadFile,
          req: this.newArtista
        }
      }).subscribe({
        next:() => {
          console.error("inviato")
          this.removeImage()
          window.location.reload()
        },
        error:(err) => {
          console.error("rifiutato")
        }
      })


    }

  }








  // opera



  getAllOpere(){

    this.adminService.getAllOpereForDisplay().subscribe({
      next: (res) => {
        this.listaOp = res
        console.log("ok")
      },
      error:() => {
        console.error("errore fetch dati opere admin")
      }
    })

  }




  prendiDatiOpera(){

    if(this.operaDaMod != undefined){

      this.adminService.getOperaDaMod({
        idOpera: this.operaDaMod
      }).subscribe({
        next: (res) => {
          this.opDaMod = res
          this.setupDatiModOp()
        },
        error: (err) => {
          console.error("errore fetch dati orpera da mod")
        }
      })

    }

  }

  prendiDatiOperaDel(){

    if(this.operaDaMod != undefined){

      this.adminService.getOperaDaMod({
        idOpera: this.operaDaDel
      }).subscribe({
        next: (res) => {
          this.opDaDel = res
          this.setupDatiDelOp()
        },
        error: (err) => {
          console.error("errore fetch dati orpera da mod")
        }
      })

    }

  }


  modificaOpera(){

    if(this.previewImageModOp != null && this.operaDaMod != 0 && this.artOpSelected != 0){

      this.adminService.modOpera({
        idOpera: this.operaDaMod,
        idArt: this.artOpSelected,
        body: this.opDaMod
      }).subscribe({
        next: () => {
          window.location.reload()
        },
        error: (err) => {
          console.error("errore modifica opera")
        }
      })

    }

  }


  eliminaOpera(){

    if(this.operaDaDel != 0){
      this.adminService.deleteOpera({
        idOpera: this.operaDaDel
      }).subscribe({
        next: () => {
          window.location.reload()
        },
        error:(err) => {
          console.error("errore cancellazione opera")
        }
      })
    }
  }









  // Gestione DragAndDrop


  triggerFileSelect(){
    const fileInput = document.getElementById('fileInput') as HTMLInputElement;
    fileInput.click()
  }

  onFileSelect(event: Event){
    const target = event.target as HTMLInputElement;
    const files = target.files;

    if(files && files.length > 0){
      this.handleFile(files[0])
    }
  }

  onDragOver(event: DragEvent){
    event.preventDefault();
    this.isDragging = true;
  }

  onDragLeave(event: DragEvent){
    event.preventDefault()
    this.isDragging = false;
  }

  onFileDrop(event: DragEvent){
    event.preventDefault()
    this.isDragging = false;

    if(event.dataTransfer?.files && event.dataTransfer.files.length > 0){
      this.handleFile(event.dataTransfer.files[0])
    }
  }

  handleFile(file: File){
    if(file.type.startsWith('image/')){
      this.uploadFile = file;
      this.generatePreview(file);
    }
    else{
      console.log("Inserire un immagine")
    }
  }

  generatePreview(file: File) {
    const reader = new FileReader();
    reader.onload = (event: ProgressEvent<FileReader>) => {
      if (event.target && event.target.result) {
        this.previewImage = event.target.result as string; // Assegna il risultato come stringa
      }
    };
    reader.readAsDataURL(file); // Legge il file
  }

  removeImage(){
    this.uploadFile = null;
    this.previewImage = null;
    this.toShow = null;
  }




  // gestione aree

  newArea: AreaRequest = {name:""}

  creaArea(){

    if(this.newArea.name != "" && this.curaSelezionato != 0){

      this.adminService.creaArea({
        idCur: this.curaSelezionato,
        body: this.newArea
      }).subscribe({
        next: () => {
          window.location.reload()
        },
        error: (err) => {
          console.error("Errore creazione area")
        }
      })

    }

  }

  aree: AreaFront[] = []
  areaSelezionata: number = 0;


  prendiDatiAree(){
    this.frontService.getAree().subscribe({
      next: (res) => {
        this.aree = res;
      },
      error: (err)=>{
        console.error("Errore fetch dati area "+ err)
    }
    })
  }




  cancellaArea(){
    this.adminService.delArea({
      idArea: this.areaSelezionata
    }).subscribe({
      next: () => {
        window.location.reload()
      },
      error:(err) => {
        console.error("Errore cancellazione area: ",err)
      }
    })
  }



  quadriFiltrati: OperaFront[] = []  // opere con il campo posizione vuoto
  areaSelezionataAcuiAggiungere: number = 0
  quadroDaAggiungere = 0


  getAllOpereFree(){
    this.adminService.getAllFree().subscribe({
      next: (res) => {
        this.quadriFiltrati = res;
      },
      error: () => {
        console.error("errore fetch opere filtrate")
      }
    })
  }


  aggiungiAdArea(){
    if(this.areaSelezionataAcuiAggiungere!= 0 && this.quadroDaAggiungere != 0){
      this.adminService.addOperaAdarea({
        idArea: this.areaSelezionataAcuiAggiungere,
        idOpera: this.quadroDaAggiungere
      }).subscribe({
        next: () => {
          console.error("aggiunto con successo")
        },
        error: (res) => {
          console.error("Fail to add to area: "+ res)
        }
      })

    }
  }

  dataCura:UserFront[] =[]
  curaSelezionato: number = 0
  getCuratoriLiberi(){

    this.adminService.getAllCuraFree().subscribe({
      next: (res) => {
        this.dataCura = res
      },
      error: (err) => {
        console.error("fetch dati utenti non avvenuto")
      }
    })
  }






  protected readonly faMagnifyingGlass = faMagnifyingGlass;
}

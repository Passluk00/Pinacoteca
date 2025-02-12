import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomepageComponent} from "./homepage/homepage.component";
import {LandingPageComponent} from "./landing-page/landing-page.component";
import {OperaComponent} from "./opera/opera.component";
import {ArtistaComponent} from "./artista/artista.component";

const routes: Routes = [

  {
    path:"",
    component: HomepageComponent,
    children:[

      {
        path:"",
        component:LandingPageComponent,
        title:"Pinacoteca"
      },
      {
        path: "opera/:id",
        component:OperaComponent
      },
      {
        path: "artista/:id",
        component:ArtistaComponent
      }

    ]
  }


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomepageRoutingModule { }

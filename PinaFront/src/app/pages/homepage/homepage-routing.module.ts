import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomepageComponent} from "./homepage/homepage.component";
import {LandingPageComponent} from "./landing-page/landing-page.component";

const routes: Routes = [

  {
    path:"",
    component: HomepageComponent,
    children:[

      {
        path:"",
        component:LandingPageComponent,
        title:"Pinacoteca"
      }

    ]
  }


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomepageRoutingModule { }

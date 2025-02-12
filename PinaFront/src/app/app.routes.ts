import {RouterModule, Routes} from '@angular/router';
import {NgModule} from "@angular/core";
import {LoginComponent} from "./pages/login/login.component";
import {RegisterComponent} from "./pages/register/register.component";
import {AdminComponent} from "./pages/admin/admin/admin.component";

export const routes: Routes = [

  {
    path: '',
    loadChildren: () => import('./pages/homepage/homepage.module').then(m =>m.HomepageModule)
  },
  {
    path:"login",
    component:LoginComponent,
    title:"Login"
  },
  {
    path:"register",
    component:RegisterComponent,
    title:"Register"
  },
  {
    path:"admin",
    component:AdminComponent,
    title:"Admin Panel"
  }


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports:[RouterModule]
})

export class AppRoutes {}

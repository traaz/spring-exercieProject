import { Routes } from '@angular/router';
import { PersonComponent } from './components/person/person.component';
import { PersonAddComponent } from './components/person-add/person-add.component';
import { LoginComponent } from './components/login/login.component';
import { loginGuard } from './guards/login.guard';

export const routes: Routes = [
    {path : "", component : PersonComponent, canActivate : [loginGuard]},
    {path:"people", component : PersonComponent, canActivate : [loginGuard]},
    {path:"people/country/:countryId", component : PersonComponent, canActivate : [loginGuard]},
    {path:"people/state/:stateId", component : PersonComponent, canActivate : [loginGuard]},
    {path:"people/add", component : PersonAddComponent, canActivate : [loginGuard]},
    {path:"login", component : LoginComponent},
];

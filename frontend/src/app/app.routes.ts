import { Routes } from '@angular/router';
import { PersonComponent } from './components/person/person.component';
import { PersonAddComponent } from './components/person-add/person-add.component';

export const routes: Routes = [
    {path : "", component : PersonComponent},
    {path:"people", component : PersonComponent},
    {path:"people/country/:countryId", component : PersonComponent},
    {path:"people/state/:stateId", component : PersonComponent},
    {path:"people/add", component : PersonAddComponent},
 
];

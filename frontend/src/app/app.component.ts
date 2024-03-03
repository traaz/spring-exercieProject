import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CountryComponent } from './components/country/country.component';
import { PersonComponent } from './components/person/person.component';
import { StateComponent } from './components/state/state.component';
import { NaviComponent } from './components/navi/navi.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CountryComponent, PersonComponent, StateComponent,NaviComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'proje';
}

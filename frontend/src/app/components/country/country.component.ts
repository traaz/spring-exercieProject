import { Component } from '@angular/core';
import { CountryService } from '../../services/country.service';
import { Country } from '../../models/country';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-country',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './country.component.html',
  styleUrl: './country.component.css'
})
export class CountryComponent {
  countries : Country[] = []
  constructor(private countryService : CountryService){}
  ngOnInit() : void{
    this.getCountries()
  } 

  getCountries(){
    this.countryService.getCountries().subscribe(
      response => {
        this.countries = response.data;
        
      }
    )
  }

  
}

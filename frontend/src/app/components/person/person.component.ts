import { Component } from '@angular/core';
import { Person } from '../../models/person';
import { HttpClient } from '@angular/common/http';

import { CommonModule } from '@angular/common';
import { PeopleService } from '../../services/people.service';
import { response } from 'express';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { FilterPipePipe } from '../../pipes/filter-pipe.pipe';
import { FormsModule } from '@angular/forms';
import { Country } from '../../models/country';
import { CountryService } from '../../services/country.service';
import { State } from '../../models/state';
import { StateService } from '../../services/state.service';
import { SurnamePipePipe } from "../../pipes/surname-pipe.pipe";
import { AgePipePipe } from "../../pipes/age-pipe.pipe";
import { CountryPipePipe } from "../../pipes/country-pipe.pipe";
import { StatePipePipe } from "../../pipes/state-pipe.pipe";


@Component({
  selector: 'app-person',
  standalone: true,
  imports: [ CommonModule, FilterPipePipe, FormsModule, SurnamePipePipe, AgePipePipe, CountryPipePipe, StatePipePipe],
  templateUrl: './person.component.html',
  styleUrl: './person.component.css'
})
export class PersonComponent {
  ulkeIsmi:String;
  personList : Person[] = []
  metin : String ;
  aranacakIsim : string;
  aranacakSoyIsim: string;
  aranacakYas: number;
  countries : Country[] ;
  states : State[];
  selectedCountry:any=[];
  selectedState : any = [];

  constructor(private peopleService : PeopleService, 
    private activatedRoute : ActivatedRoute, 
    private countryService: CountryService,
  private stateService : StateService,
private router : Router){}

  ngOnInit(): void {
    this.countryService.getCountries().subscribe(
      response => {
        this.countries = response.data
      }
    )


    this.activatedRoute.params.subscribe(
      params => {
        if(params["countryId"]){
          this.getPeopleByCountry(params["countryId"])
          if(this.metin ="Veri Yok"){
            this.metin = ""
          }

        }
       
        else{
          this.getPeople()
        }
      }
    )
   
  }
  getPeople(){
    this.peopleService.getPeople().subscribe(
      response => {
        this.personList = response.data;
        
      }
    )
  }
  getPeopleByCountry(countryId : number){
    if(countryId == 1){
      this.ulkeIsmi = "Türkiye"
    }
    else if(countryId == 3){
      this.ulkeIsmi = "Gürcistan"
    }
    else if(countryId == 6){
      this.ulkeIsmi = "Amerikan"
    }
    else if(countryId == 2){
      this.ulkeIsmi = "Almanya"
    }
    this.peopleService.getPeopleAccordingToCountry(countryId).subscribe(
      response => {
        this.personList = response.data
        console.log(this.personList)
        if(this.personList.length == 0){
          this.metin = "Mevcut Kişi Yok."
        }
      }
    )
  }

  getPeopleByState(stateId : number){
    this.peopleService.getPeopleAccordingToState(stateId).subscribe(
      response => {
        this.personList = response.data
      }
    )
  }

  onSelect(countryId : any){
    console.log(this.selectedCountry.id)
     this.stateService.getStatesAccordingToCountryId(this.selectedCountry.id).subscribe(
      response => {
        
       this.states = response.data;
         console.log(response.data)
      }
     )
    
  }
  resetFilter(){
    this.aranacakIsim = ""
    this.aranacakSoyIsim = ""
    this.aranacakYas = 0
    this.selectedCountry = ""
    this.selectedState = ""
    this.router.navigateByUrl('');
  }
 
}

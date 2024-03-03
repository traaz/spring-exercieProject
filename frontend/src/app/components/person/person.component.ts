import { Component } from '@angular/core';
import { Person } from '../../models/person';
import { HttpClient } from '@angular/common/http';

import { CommonModule } from '@angular/common';
import { PeopleService } from '../../services/people.service';
import { response } from 'express';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { FilterPipePipe } from '../../pipes/filter-pipe.pipe';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-person',
  standalone: true,
  imports: [CommonModule, FilterPipePipe, FormsModule],
  templateUrl: './person.component.html',
  styleUrl: './person.component.css'
})
export class PersonComponent {
  personList : Person[] = []
  metin : String ;
  aranacakIsim : string;
  constructor(private peopleService : PeopleService, private activatedRoute : ActivatedRoute){}

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(
      params => {
        if(params["countryId"]){
          this.getPeopleByCountry(params["countryId"])
          if(this.metin ="Veri Yok"){
            this.metin = ""
          }

        }
        else if(params["stateId"]){
          this.getPeopleByState(params["stateId"])
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
  
}

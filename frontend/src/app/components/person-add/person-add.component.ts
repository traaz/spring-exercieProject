import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { PeopleService } from '../../services/people.service';
import { CountryService } from '../../services/country.service';
import { StateService } from '../../services/state.service';
import { Country } from '../../models/country';
import { State } from '../../models/state';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-person-add',
  standalone: true,
  imports: [FormsModule,  RouterModule, ReactiveFormsModule, CommonModule],
  templateUrl: './person-add.component.html',
  styleUrl: './person-add.component.css'
})
export class PersonAddComponent {  
  personAddForm : FormGroup;
  countries : Country[] ;
  states : State[];
  
  
  constructor(
    private router : Router,
    private formBuilder : FormBuilder,
    private peopleService : PeopleService, 
    private countryService : CountryService, 
    private stateService : StateService
    ){}

  ngOnInit(): void {
    this.countryService.getCountries().subscribe(
      response => {
        this.countries = response.data
      }
    )
    this.createPerson();
  }

  createPerson(){
    this.personAddForm = this.formBuilder.group(
      {
        name:["", Validators.required],
        surname:["", Validators.required],
        age:["", Validators.required],
        country:["", Validators.required],
        state:["", Validators.required]
      }
    )
  }

  add(){
    if(this.personAddForm.valid){
      let createPerson  = {name: this.personAddForm.value.name, surname:this.personAddForm.value.surname, age:this.personAddForm.value.age  ,countryId:this.personAddForm.value.country, stateId:this.personAddForm.value.state}
      this.peopleService.addPerson(createPerson).subscribe(
        response => {
          console.log(response.message)
          this.personAddForm.reset()
          this.router.navigate(["people"])
        }
      )
    }else{

    }
  }

  onSelect(countryId : any){
    this.stateService.getStatesAccordingToCountryId(countryId).subscribe(
      response => {
        this.states = response.data;
        console.log(response.data)
      }
    )
  }
}

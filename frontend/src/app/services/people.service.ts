import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Observable } from 'rxjs';
import { ListResponseModel } from '../models/listResponseModel';
import { Person } from '../models/person';
import { CreatePerson } from '../models/createPerson';
import { ResponseModel } from '../models/responseModel';

@Injectable({
  providedIn: 'root'
})
export class PeopleService {
  apiUrl  = 'http://localhost:8080/person/'

  constructor(private httpClient : HttpClient) { }

  getPeople(): Observable<ListResponseModel<Person>>{
    return this.httpClient.get<ListResponseModel<Person>>(this.apiUrl + "getAll");
    
  }

  getPeopleAccordingToCountry(countryId : number):Observable<ListResponseModel<Person>>{

    return this.httpClient.get<ListResponseModel<Person>>(this.apiUrl + "country/" + countryId);
  }
  
  getPeopleAccordingToState(stateId : number) : Observable<ListResponseModel<Person>>{
    return this.httpClient.get<ListResponseModel<Person>>(this.apiUrl + "state/" + stateId );
  }

  addPerson(createPerson : CreatePerson) : Observable<ResponseModel>{
    return this.httpClient.post<ResponseModel>(this.apiUrl + "add", createPerson);
  }
}

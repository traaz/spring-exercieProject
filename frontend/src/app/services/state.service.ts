import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { State } from '../models/state';
import { Observable } from 'rxjs';
import { ListResponseModel } from '../models/listResponseModel';

@Injectable({
  providedIn: 'root'
})
export class StateService {
  apiUrl  = 'http://localhost:8080/state/'

  constructor(private httpClient : HttpClient) { }

  getStates(): Observable<ListResponseModel<State>>{
    return this.httpClient.get<ListResponseModel<State>>(this.apiUrl + "getAll");
    
  }

  getStatesAccordingToCountryId(countryId : number):Observable<ListResponseModel<State>>{
    return this.httpClient.get<ListResponseModel<State>>(this.apiUrl + "country/" + countryId);
  }
 
}

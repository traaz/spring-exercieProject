import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Country } from '../models/country';
import { ListResponseModel } from '../models/listResponseModel';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CountryService {
  apiUrl  = 'http://localhost:8080/country/getAll'

  constructor(private httpClient : HttpClient) { }

  getCountries(): Observable<ListResponseModel<Country>>{
    return this.httpClient.get<ListResponseModel<Country>>(this.apiUrl);
    
  }
}

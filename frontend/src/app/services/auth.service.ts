import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TokenResponse } from '../models/tokenResponse';
import { LogInUser } from '../models/logInUser';
import { DOCUMENT } from '@angular/common';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  apiUrl  = 'http://localhost:8080/user/'
  constructor(private httpClient : HttpClient, @Inject(DOCUMENT) private document: Document) { }
  localStorage = this.document.defaultView?.localStorage;
  logIn(logIn : LogInUser) : Observable<TokenResponse>{
    return this.httpClient.post<TokenResponse>(this.apiUrl+ "login" , logIn);
  }
  isAuthenticated(){
    

    if(localStorage.getItem("token")){
      return true;
    }else{
      return false;
    }
  }

}

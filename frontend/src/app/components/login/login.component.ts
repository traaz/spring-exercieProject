import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  loginForm : FormGroup;
  constructor( private formBuilder : FormBuilder, private authService : AuthService,     private router : Router){

  }
  ngOnInit(){
    this.loginOperationForm();
  }

  loginOperationForm(){
    this.loginForm = this.formBuilder.group(
      {
      userName : ["", Validators.required],
      password : ["", Validators.required]
    }
    )
  }

  logIn(){
    if(this.loginForm.valid){
      let login = {name : this.loginForm.value.userName, password : this.loginForm.value.password}
      this.authService.logIn(login).subscribe(
        
          response => {
            console.log(login)
            localStorage.setItem("token",response.data)
            this.router.navigate(['/people'])
          }, responseError => {
            console.log("Hatalı Giriş.")
          }
        
      )
    }
   
  }


}

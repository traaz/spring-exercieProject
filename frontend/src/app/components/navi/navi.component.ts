import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-navi',
  standalone: true,
  imports: [RouterModule, FormsModule, CommonModule],
  templateUrl: './navi.component.html',
  styleUrl: './navi.component.css'
})
export class NaviComponent {
 constructor(private authService : AuthService, private router : Router){
  
 }
 isAuth():boolean{
  if(this.authService.isAuthenticated()){
    return true;
  }else{
    return false;  
  }
 }
 logOut(){
  localStorage.clear()
  this.router.navigate(["/login"])
 }
}

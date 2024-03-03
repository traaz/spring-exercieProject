import { CanActivateFn, Router} from '@angular/router';
import { AuthService } from '../services/auth.service';
import { inject } from '@angular/core';

export const loginGuard: CanActivateFn = (route, state) => {
  let token = localStorage.getItem("token")
  
const router = inject(Router) //new anahtarı yerine inject
  if(token){
    return true;
  }else{
    router.navigate(["/login"])
    return false;
  }
  
};


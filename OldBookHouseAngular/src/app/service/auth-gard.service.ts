import { Injectable } from '@angular/core';
import { CanActivate, Router, RouterStateSnapshot, ActivatedRouteSnapshot } from '@angular/router';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGardService implements CanActivate{

  constructor(public router:Router,
    public authService:AuthenticationService) { }

  canActivate(route:ActivatedRouteSnapshot,state:RouterStateSnapshot){
    if(this.authService.isUserLoggedIn()){
      return true;
    }
    return false;
  }
}

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  object: any;
  constructor(public httpClient: HttpClient) { }

    // this method is use to loging the user
    authenticate(username, password) {
     return  this.httpClient.post<any>('http://localhost:8080/authenticate', {username, password});
    }

    isUserLoggedIn() {
      const user = sessionStorage.getItem('username');
      return !(user === null);
    }
    logOut() {
      sessionStorage.removeItem('username');
    }
}

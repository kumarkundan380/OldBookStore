import { Component, OnInit } from '@angular/core';
import { MatDialogConfig, MatDialog } from '@angular/material/dialog';
import { LoginComponent } from '../login/login.component';
import { LoginServeiceService } from '../share/login-serveice.service';
import { RegistrationService } from '../share/registration.service';
import { RegistrationComponent } from '../registration/registration.component';
import { JavaServiceService } from '../java-service.service';
import { AuthenticationService } from '../service/authentication.service';
import { Router } from '@angular/router';
import { ProfileComponent } from '../profile/profile.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  cartshow:boolean=false;
  constructor(public dialog: MatDialog,
    public loginService:LoginServeiceService,
    public registrationService:RegistrationService,
    public javaCallObj:JavaServiceService,
    public hasLogin:AuthenticationService,
    public router:Router) { }
  fun(){
    this.cartshow=true;
    // console.log("fuction call");
  }
  ngOnInit() {
    // this.isRole=this.javaCallObj.hasRole();
    // console.log(this.hasLogin.isUserLoggedIn());
  }
  searchBook(searchValue:any){
    this.javaCallObj.searchBook(searchValue.value);
  }

  deliveryRequestFun(){
      this.router.navigate(["/deliveryRequest"]);
  }

  login(){
    this.loginService.initializeFormGroup();
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "40%";
    this.dialog.open(LoginComponent,dialogConfig);
  }
  registration(){
    this.registrationService.initializeFormGroup();
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "60%";
    this.dialog.open(RegistrationComponent,dialogConfig);
  }
  viewProfile(){
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "40%";
    //{position: {top: '0%', left: '20%'}}
   // dialogConfig.position({ top: '50px', left: '50px' });
    this.dialog.open(ProfileComponent,dialogConfig);
  }
  logOut(){
    this.hasLogin.logOut();
    sessionStorage.removeItem('userRole');
    this.router.navigate(["/mainslider"]);

  }
  
}

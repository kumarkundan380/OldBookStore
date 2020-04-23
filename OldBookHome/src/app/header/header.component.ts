import { Component, OnInit } from '@angular/core';
import { MatDialogConfig, MatDialog } from '@angular/material/dialog';
import { LoginComponent } from '../login/login.component';
import { LoginServeiceService } from '../share/login-serveice.service';
import { RegistrationService } from '../share/registration.service';
import { RegistrationComponent } from '../registration/registration.component';
import { ProfileComponent } from '../profile/profile.component';
import { AuthenticationService } from '../service/authentication.service';
import { Router } from '@angular/router';
import { JavaServiceService } from '../java-service.service';

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
    public authenticationService:AuthenticationService,
    public javaService:JavaServiceService,
    public router:Router ) { }
  fun(){
    this.cartshow=true;
    console.log("fuction call");
  }
  ngOnInit() {
  }
  login(){
    this.loginService.initializeFormGroup();
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "40%";
    this.dialog.open(LoginComponent,dialogConfig);
  }

  searchBook(searchValue:any){
    console.log(searchValue.value);
    this.javaService.search=searchValue.value;
    this.javaService.searchBook(searchValue.value);
   // this.router.navigate(['/showbook']);
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
  logout(){
    this.authenticationService.logOut();
    this.router.navigate(['\mainslider']);
  }
}

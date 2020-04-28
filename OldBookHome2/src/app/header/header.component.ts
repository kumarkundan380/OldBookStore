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
  bookList:any;
  constructor(public dialog: MatDialog,
    public loginService:LoginServeiceService,
    public registrationService:RegistrationService,
    public javaCallObj:JavaServiceService,
    public hasLogin:AuthenticationService,
    public router:Router) { }
  fun(){
    this.cartshow=true;
  }
  ngOnInit() {
  }
  searchBook(searchValue:any){
    this.javaCallObj.searchBook(searchValue.value);
  }

  getNotificationBook(){
    if(this.hasLogin.isUserLoggedIn()){
      this.javaCallObj.getBuyBook().subscribe(
        book=>{
          this.bookList=book;
          console.log(this.bookList);
        });
    }
  }  

  deleteBookRequest(requestId:number){
    this.javaCallObj.delteBookRequest(requestId).subscribe(
      data=>{
        this.javaCallObj.getBookNotification();
      });
  }

  //this method used for geeting all book request for deliver 
  deliverySellRequest(){
    this.router.navigate(["/deliverBuyRequest"]);
  }

  deliveryRequestFun(){
      this.router.navigate(["/deliveryRequest"]);
  }
  listUserFun(){
    this.router.navigate(["/listUser"]);
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
    this.dialog.open(ProfileComponent,dialogConfig);
  }
  logOut(){
    this.hasLogin.logOut();
    sessionStorage.removeItem('userRole');
    sessionStorage.removeItem('notification1');
    sessionStorage.setItem('notification1','0');
    this.router.navigate(["/mainslider"]);

  }
  // ---------------admin part---------------

  deliverySellRequestAdmin(){
    this.router.navigate(["/deliverBuyRequest"]);
  }
  deliveryRequestFunAdmin(){
    this.router.navigate(["/deliveryRequest"]);
  }
  
}

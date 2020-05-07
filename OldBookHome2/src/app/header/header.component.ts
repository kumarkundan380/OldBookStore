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
import { NgxSpinnerService } from "ngx-spinner";
import { NotificationService } from '../share/notification.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  cartshow:boolean=false;
  bookList:any;
  catogoryList:any=[];
  nav_toggel:boolean=false;

  constructor(public dialog: MatDialog,
    public loginService:LoginServeiceService,
    public registrationService:RegistrationService,
    public javaCallObj:JavaServiceService,
    public notificationService:NotificationService,
    public hasLogin:AuthenticationService,
    public spinner:NgxSpinnerService,
    public router:Router) { }
  fun(){
    this.cartshow=true;
  }
  ngOnInit() {
  }

  home(){
    this.spinner.show();
    setTimeout(() => {
      /** spinner ends after 2 seconds */
      this.spinner.hide();
    }, 2000);
    this.router.navigateByUrl('mainslider');
  }

  searchBook(searchValue:any){
    this.javaCallObj.searchBook(searchValue.value);
  }

  getPrice(){
    this.javaCallObj.totalPrice=0;
    for (let index = 0; index < this.bookList.length; index++) {
      this.javaCallObj.totalPrice=this.javaCallObj.totalPrice+this.bookList[index].amount*this.bookList[index].quantity;  
    }
  }

  getNotificationBook(){
    if(this.hasLogin.isUserLoggedIn()){
      this.javaCallObj.getBuyBook().subscribe(
        book=>{
          this.bookList=book;
          this.getPrice();
        });
    }
  }
  
  //---this method is used to find the all the catogory of book
  
  // getBookCatogory(){
  //   this.javaCallObj.getBookCatogory().subscribe(book=>{
  //     this.catogoryList=book;
  //     console.log(book);
  //   });
  // }

  searchCatogory(category:string){
    this.javaCallObj.getBookByCategory(category).subscribe(data=>{
      this.bookList=data;
    });
  }

  deleteBookRequest(requestId:number){
    this.javaCallObj.delteBookRequest(requestId).subscribe(
      data=>{
        this.spinner.show();
        setTimeout(() => {
        this.spinner.hide();
      }, 2000);
      this.javaCallObj.getBookNotification();
    });
    this.notificationService.warn("Remove from cart Successfully.");
  }

  //this method used for geeting all book request for deliver 
  deliverySellRequest(){
    this.spinner.show();
    setTimeout(() => {
      this.spinner.hide();
    }, 2000);
    this.router.navigate(["/deliverBuyRequest"]);
  }

  deliveryRequestFun(){
    this.spinner.show();
    setTimeout(() => {
     this.spinner.hide();
    }, 2000);
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
    this.dialog.open(ProfileComponent,dialogConfig);
  }
  logOut(){
    this.hasLogin.logOut();
    sessionStorage.removeItem('userRole');
    sessionStorage.removeItem('notification1');
    sessionStorage.setItem('notification1','0');
    this.router.navigate(["/mainslider"]);

  }

  //........Hystory Part...........

  buyOrder(){
    this.spinner.show();
    setTimeout(() => {
    this.spinner.hide();
    }, 2000);
    this.router.navigate(["/buyHistory"]);
  }

  sellOrder(){
    this.spinner.show();
    setTimeout(() => {
    this.spinner.hide();
    }, 2000);
    this.router.navigate(["/sellHistory"]);
  }


  // ---------------admin part---------------

  listUserFun(){
    this.spinner.show();
    setTimeout(() => {
    this.spinner.hide();
    }, 2000);
    this.router.navigate(["/listUser"]);
  }
  
  deliverySellRequestAdmin(){
    this.spinner.show();
    setTimeout(() => {
    this.spinner.hide();
    }, 2000);
    this.router.navigate(["/deliverBuyRequest"]);
  }

  deliveryRequestFunAdmin(){
    this.spinner.show();
    setTimeout(() => {
    this.spinner.hide();
    }, 2000);
    this.router.navigate(["/deliveryRequest"]);
  }
  
}

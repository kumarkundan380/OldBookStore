import { Component, OnInit } from '@angular/core';
import { AddAddressService } from '../share/add-address.service';
import { MatDialog,MatDialogConfig } from '@angular/material/dialog';
import { BookDeliverAddressComponent } from '../book-deliver-address/book-deliver-address.component';
import { JavaServiceService } from '../java-service.service';
import { AuthenticationService } from '../service/authentication.service';
import { from } from 'rxjs';
import { NotificationService } from '../share/notification.service';
import { Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  bookList:any;
  shipingCharge=0;

  constructor(public addreqService:AddAddressService,
    public javaServiceObj:JavaServiceService,
    public hasLogin:AuthenticationService,
    public notificationService:NotificationService,
    public dialog:MatDialog,
    public router:Router,
    public spinner:NgxSpinnerService) { }

  ngOnInit() {
    if(this.hasLogin.isUserLoggedIn()){
      this.javaServiceObj.getBuyBook().subscribe(
        book=>{
          this.bookList=book;
          this.getPrice();
        });
     }
  }

  getPrice(){
    this.shipingCharge=60
    this.javaServiceObj.totalPrice=this.shipingCharge;
    for (let index = 0; index < this.bookList.length; index++) {
      this.javaServiceObj.totalPrice=this.javaServiceObj.totalPrice+this.bookList[index].amount*this.bookList[index].quantity;  
    }
  }

  plusQuantity(requestId:number){
    this.javaServiceObj.plusQuantity(requestId);
  }

  minusQuantity(requestId:number){
    this.javaServiceObj.minusQuantity(requestId);
  }

  deleteBookRequest(requestId:number){
    this.javaServiceObj.delteBookRequest(requestId).subscribe(
      data=>{
        this.router.navigateByUrl('/refresh', { skipLocationChange: true }).then(() => {
          this.javaServiceObj.getBookNotification();
          this.spinner.show();
          setTimeout(() => {
            this.spinner.hide();
          }, 1000);
          this.router.navigateByUrl('/checkout');
        });
      });
  }

  buyNow(){
    this.javaServiceObj.checkCart=true;
    this.addreqService.initializeFormGroup();
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "50%";
    this.dialog.open(BookDeliverAddressComponent,dialogConfig);
  }

}

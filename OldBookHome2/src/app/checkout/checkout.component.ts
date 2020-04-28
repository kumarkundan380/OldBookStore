import { Component, OnInit } from '@angular/core';
import { AddAddressService } from '../share/add-address.service';
import { MatDialog,MatDialogConfig } from '@angular/material/dialog';
import { BookDeliverAddressComponent } from '../book-deliver-address/book-deliver-address.component';
import { JavaServiceService } from '../java-service.service';
import { AuthenticationService } from '../service/authentication.service';
import { from } from 'rxjs';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  bookList:any;

  constructor(public addreqService:AddAddressService,
    public javaServiceObj:JavaServiceService,
    public hasLogin:AuthenticationService,
    public dialog:MatDialog) { }

  ngOnInit() {
    if(this.hasLogin.isUserLoggedIn()){
      this.javaServiceObj.getBuyBook().subscribe(
        book=>{
          this.bookList=book;
        //  console.log(this.bookList);
        });
     }
  }
  deleteBookRequest(requestId:number){
    this.javaServiceObj.delteBookRequest(requestId).subscribe(
      data=>{
        this.javaServiceObj.getBookNotification();
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

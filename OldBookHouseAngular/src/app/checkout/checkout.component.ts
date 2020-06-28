import { Component, OnInit } from '@angular/core';
import { JavaServiceService } from '../java-service.service';
import { MatDialogConfig, MatDialog } from '@angular/material/dialog';
import { BookDeliverAddressComponent } from '../book-deliver-address/book-deliver-address.component';
import { AddAddressService } from '../share/add-address.service';
import { AuthenticationService } from '../service/authentication.service';
import { Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { NotificationService } from '../share/notification.service';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  bookList: any;
  shipingCharge = 0;
  totalLength: number;

  constructor( public javaServiceObj: JavaServiceService,
               public addreqService: AddAddressService,
               public dialog: MatDialog,
               public hasLogin: AuthenticationService,
               public router: Router,
               public spinner: NgxSpinnerService,
               public notificationService: NotificationService) { }

  // this method is use to calculate the total price
  getPrice() {
    this.shipingCharge = 60;
    this.javaServiceObj.totalPrice = this.shipingCharge;
    // tslint:disable-next-line: prefer-const
    for (let index of this.bookList) {
        this.javaServiceObj.totalPrice = this.javaServiceObj.totalPrice + index.amount * index.quantity;
      }
  }

  // this mehod is use to check the book quantity
  checkQuantity() {
    for (const index of this.bookList) {
      if (index.quantity === 0) {
        this.deleteBookRequest(index.buyOrderRequestId);
      }
    }
  }

  // this mehtod is use to add book quantity
  plusQuantity(requestId: number, bookId: number, quantity: number) {
    this.javaServiceObj.getQuantity(bookId).subscribe(
      data => {
        const bookQuantity = data;
        if (quantity < bookQuantity) {
          this.javaServiceObj.plusQuantity(requestId);
        } else {
          this.notificationService.warn('No more quantity is available.');
        }
      });
  }

  // this mehod is use to minus the book quatity
  minusQuantity(requestId: number) {
    this.javaServiceObj.minusQuantity(requestId);
  }

  ngOnInit() {
    if (this.hasLogin.isUserLoggedIn()) {
      this.javaServiceObj.getBuyBook().subscribe(
        book => {
          this.bookList = book;
          this.totalLength = this.bookList.length;
          if (this.totalLength > 0) {
            this.getPrice();
            this.checkQuantity();
          } else {
            this.javaServiceObj.totalPrice = 0;
          }
        });
    } else {
      this.javaServiceObj.totalPrice = 0;
    }
  }

 // this method is use to delete the book
  deleteBookRequest(requestId: number) {
    this.javaServiceObj.delteBookRequest(requestId).subscribe(
      data => {
        this.router.navigateByUrl('/refresh', { skipLocationChange: true }).then(() => {
        this.javaServiceObj.getBookNotification();
        this.javaServiceObj.getSpinner(1000);
        this.router.navigateByUrl('/checkout');
        });
      });
  }

  // this mehod is use to purchage the book
  buyNow() {
    if (this.hasLogin.isUserLoggedIn()) {
      this.javaServiceObj.checkCart = true;
      this.addreqService.initializeFormGroup();
      const dialogConfig = new MatDialogConfig();
      dialogConfig.disableClose = true;
      dialogConfig.autoFocus = true;
      dialogConfig.width = '50%';
      this.dialog.open(BookDeliverAddressComponent, dialogConfig);
    } else {
      this.notificationService.warn('please Login first.');
    }
  }
}

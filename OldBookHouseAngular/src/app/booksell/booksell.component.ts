import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { JavaServiceService } from '../java-service.service';
import { MatDialogConfig, MatDialog } from '@angular/material/dialog';
import { BookreqAddressComponent } from '../bookreq-address/bookreq-address.component';
import { AddAddressService } from '../share/add-address.service';
import { NgxSpinnerService } from 'ngx-spinner';
import { AuthenticationService } from '../service/authentication.service';
import { NotificationService } from '../share/notification.service';


@Component({
  selector: 'app-booksell',
  templateUrl: './booksell.component.html',
  styleUrls: ['./booksell.component.css']
})
export class BooksellComponent implements OnInit {

  book: any;
  name: any;
  array: any;
  listBook: number[] = [0, 1, 2];
  index = 0;
  index2 = 1;
  isBook = false;

  constructor(public dialog: MatDialog,
              public addressService: AddAddressService,
              public httpClient: HttpClient,
              public javaService: JavaServiceService,
              public spinner: NgxSpinnerService,
              public hasLogin: AuthenticationService,
              public notificationService: NotificationService) { }

  // this method use to call to google book api
  sendData() {
    this.name = this.book.value;
    this.httpClient.get('https://www.googleapis.com/books/v1/volumes?q=' + this.name.isbnNo).subscribe(
      data => {
      this.javaService.getSpinner(1500);
      this.array = data;
    //  console.log(this.array);
      this.isBook = true;
      });
  }

  ngOnInit(): void {
    this.book = new FormGroup({
      isbnNo: new FormControl('')
    });
    if (this.javaService.bookISBN.length > 0) {
      this.name = this.javaService.bookISBN;
      this.httpClient.get('https://www.googleapis.com/books/v1/volumes?q=' + this.name).subscribe(
        data => {
          this.javaService.getSpinner(1500);
          this.array = data;
          // console.log(this.array);
          // console.log(typeof this.array);
          this.isBook = true;
        });
    }
  }

  // this mehod is use to save the book details
  sellBook(bookNumber: number) {
    console.log(this.array);
  //  console.log(this.array.items[bookNumber].volumeInfo.title);
    try{
      this.javaService.bookObj.bookName = this.array.items[bookNumber].volumeInfo.title;
    } catch (error){
      this.javaService.bookObj.bookName = 'Anonymous';
    }
      //  console.log(this.javaService.bookObj.bookName);
    try{
      this.javaService.bookObj.authors = this.array.items[bookNumber].volumeInfo.authors[this.index];
    } catch (error){
      this.javaService.bookObj.authors = 'Anonymous';
    }
    try {
      this.javaService.bookObj.description = this.array.items[bookNumber].volumeInfo.description.substring(0, 255);
    } catch (error) {
      this.javaService.bookObj.description = 'No Description';
    }
    try{
      this.javaService.bookObj.publisher = this.array.items[bookNumber].volumeInfo.publisher;
    } catch (error){
      this.javaService.bookObj.publisher = 'Anonymous';
    }
    try{
      this.javaService.bookObj.publishedDate = this.array.items[bookNumber].volumeInfo.publishedDate;
    } catch (error){
      this.javaService.bookObj.publishedDate = 'Anonymous';
    }
    try {
      this.javaService.bookObj.categories = this.array.items[bookNumber].volumeInfo.categories[this.index];
    } catch (error) {
      this.javaService.bookObj.categories = 'Miscellaneous';
    }
    try{
      this.javaService.bookObj.contentVersion = this.array.items[bookNumber].volumeInfo.contentVersion;
    } catch (error){
      this.javaService.bookObj.contentVersion = 'Anonymous';
    }
    try {
      this.javaService.bookObj.isbnType10 = this.array.items[bookNumber].volumeInfo.industryIdentifiers[this.index].type;
    } catch (error){
      this.javaService.bookObj.isbnType10 = 'Anonymous';
    }
    try {
      this.javaService.bookObj.isbnNo1 = this.array.items[bookNumber].volumeInfo.industryIdentifiers[this.index].identifier;
    } catch (error){
      this.javaService.bookObj.isbnNo1 = 'Anonymous';
    }
    try {
      this.javaService.bookObj.isbnType13 = this.array.items[bookNumber].volumeInfo.industryIdentifiers[this.index2].type;
    } catch (error){
      this.javaService.bookObj.isbnType13 = 'Anonymous';
    }
    try{
      this.javaService.bookObj.isbnNo2 = this.array.items[bookNumber].volumeInfo.industryIdentifiers[this.index2].identifier;
    } catch (error){
      this.javaService.bookObj.isbnNo2 = 'Anonymous';
    }
    try {
      this.javaService.bookObj.smallThumbnail = this.array.items[bookNumber].volumeInfo.imageLinks.smallThumbnail;
      this.javaService.bookObj.thumbnail = this.array.items[bookNumber].volumeInfo.imageLinks.thumbnail;
    } catch (error) {
      this.javaService.bookObj.smallThumbnail = '';
      this.javaService.bookObj.thumbnail = '';
    }
    try {
      this.javaService.bookObj.checkPrice = this.array.items[bookNumber].saleInfo.isEbook;
    } catch (error){
      this.javaService.bookObj.checkPrice = false;
    }
    if (this.javaService.bookObj.checkPrice) {
        this.javaService.bookObj.amount = 0;
        this.javaService.bookObj.currencyCode = this.array.items[bookNumber].saleInfo.listPrice.currencyCode;
      }
    if (this.hasLogin.isUserLoggedIn()) {
        this.addressService.initializeFormGroup();
        const dialogConfig = new MatDialogConfig();
        dialogConfig.disableClose = true;
        dialogConfig.autoFocus = true;
        dialogConfig.width = '50%';
        this.dialog.open(BookreqAddressComponent, dialogConfig);
      } else {
        this.notificationService.warn('please Login first.');
      }
  }
}

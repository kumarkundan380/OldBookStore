import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialogConfig, MatDialog } from '@angular/material/dialog';
import { BookSellSearchComponent } from '../book-sell-search/book-sell-search.component';
import { BookSellSearchService } from '../share/book-sell-search.service';
import { JavaServiceService } from '../java-service.service';
import { NotificationService } from '../share/notification.service';
import { NgxSpinnerService } from 'ngx-spinner';
//import { NgxSpinnerModule } from "ngx-spinner";

@Component({
  selector: 'app-productdisplay',
  templateUrl: './productdisplay.component.html',
  styleUrls: ['./productdisplay.component.css']
})
export class ProductdisplayComponent implements OnInit {
  
  bookList:any;
  newList:any;
  notEmptyPost = true;
  notscrolly = true;
  catogoryList:any=[];
  
  constructor(public router:Router,
    public loginService:BookSellSearchService,
    public spinner:NgxSpinnerService,
    public notificationService:NotificationService,
    public dialog:MatDialog,
    public javaService:JavaServiceService) { }

  ngOnInit() {
    this.javaService.findBooks(0,3).subscribe((books: any[]) => {
      this.bookList = books;
   });
   this.javaService.getBookCatogory().subscribe(data=>{
     this.catogoryList=data;
   });
  }
  booksell(){
    this.loginService.initializeFormGroup();
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "40%";
    this.dialog.open(BookSellSearchComponent,dialogConfig);

  }

  onScroll() {
    if (this.notscrolly && this.notEmptyPost){
      //this.spinner.show();
      
      this.notscrolly = false;
      this.loadNextPost();
    }
    
  }
  loadNextPost() {
    this.javaService.findBooks(this.bookList.length,this.bookList.length+3).subscribe((newBookList: any[]) => {
      if (newBookList.length === 0 ) {
        this.notEmptyPost =  false;
        //this.spinner.hide();
      }
      this.bookList = this.bookList.concat(newBookList);
      this.notscrolly = true;
    });
  }

  buyBook(sellOrderRequestId:number){
    this.javaService.bookId=sellOrderRequestId;
    this.spinner.show();
    setTimeout(() => {
    this.spinner.hide();
    }, 1000);
    this.router.navigate(['/buybook']);
  }

  addToCart(bookId:number){
   // console.log(bookId);
    this.javaService.addSellOrderRequest(bookId);
    this.notificationService.success("Added to Cart Successfully.")
  } 

  purchaseBook(){
    this.spinner.show();
    setTimeout(() => {
    this.spinner.hide();
    }, 1000);
    this.router.navigate(['/checkout']);
  }

  findBookByAuthor(author:string){
    this.notEmptyPost=false;
    this.javaService.findBookByAuthor(author).subscribe(data=>{
    //  console.log(data)
      this.bookList=data;
      this.spinner.show();
      setTimeout(() => {
      this.spinner.hide();
      this.bookList=data;
      }, 1000);
    });
  }

  findBookByPublisher(publisher:string){
    this.javaService.getBookByPublisher(publisher).subscribe(data=>{
      this.bookList=data;
      this.spinner.show();
      setTimeout(() => {
      this.spinner.hide();
      this.bookList=data;
      }, 1000);
    });
  }

  searchCatogory(category:string){
   // this.notEmptyPost=false;
    this.javaService.getBookByCategory(category).subscribe(data=>{
      this.bookList=data;
      this.spinner.show();
      setTimeout(() => {
      this.spinner.hide();
      this.bookList=data;
      }, 1000);
      
    });
  }


}

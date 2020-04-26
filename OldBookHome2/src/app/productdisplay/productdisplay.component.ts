import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialogConfig, MatDialog } from '@angular/material/dialog';
import { LoginServeiceService } from '../share/login-serveice.service';
import { BookSellSearchComponent } from '../book-sell-search/book-sell-search.component';
import { BookSellSearchService } from '../share/book-sell-search.service';
import { JavaServiceService } from '../java-service.service';

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
  constructor(private router:Router,private loginService:BookSellSearchService,private dialog:MatDialog,private javaService:JavaServiceService) { }

  ngOnInit() {
    this.javaService.findBooks(0,3).subscribe((books: any[]) => {
      // console.log(books);
      this.bookList = books;
   });
  }
  booksell(){
    //console.log("for book sell.....");
    // this.router.navigate(['/booksell']);
    this.loginService.initializeFormGroup();
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "40%";
    this.dialog.open(BookSellSearchComponent,dialogConfig);

  }

  onScroll() {
    if (this.notscrolly && this.notEmptyPost){
      // this.spinner.show();
      this.notscrolly = false;
      this.loadNextPost();
    }
    
  }
  loadNextPost() {
    this.javaService.findBooks(this.bookList.length,this.bookList.length+3).subscribe((newBookList: any[]) => {
      // this.spinner.hide();
      if (newBookList.length === 0 ) {
        this.notEmptyPost =  false;
      }
      this.bookList = this.bookList.concat(newBookList);
      this.notscrolly = true;
    });
  }

  buyBook(sellOrderRequestId:number){
    this.javaService.bookId=sellOrderRequestId;
    this.router.navigate(['/buybook']);
  }


}

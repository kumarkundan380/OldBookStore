import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialogConfig, MatDialog } from '@angular/material/dialog';
import { LoginServeiceService } from '../share/login-serveice.service';
import { JavaServiceService } from '../java-service.service';
import { BookSellSearchComponent } from '../book-sell-search/book-sell-search.component';

@Component({
  selector: 'app-productdisplay',
  templateUrl: './productdisplay.component.html',
  styleUrls: ['./productdisplay.component.css']
})
export class ProductdisplayComponent implements OnInit {

  constructor(private router:Router,private loginService:LoginServeiceService,private javaService:JavaServiceService,private dialog:MatDialog) { }

  bookList:any[];

  ngOnInit() {
    this.javaService.getBooks().subscribe((books: any[]) => {
      this.bookList = books;
      console.log(this.bookList[0]);
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
  buyBook(sellOrderRequestId:number){
    this.router.navigate(['/buybook',sellOrderRequestId]);
  }

}

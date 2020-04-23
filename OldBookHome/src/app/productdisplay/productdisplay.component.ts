import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialogConfig, MatDialog } from '@angular/material/dialog';
import { LoginServeiceService } from '../share/login-serveice.service';
import { JavaServiceService } from '../java-service.service';
import { BookSellSearchComponent } from '../book-sell-search/book-sell-search.component';
import { NgxSpinnerService } from 'ngx-spinner';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-productdisplay',
  templateUrl: './productdisplay.component.html',
  styleUrls: ['./productdisplay.component.css']
})
export class ProductdisplayComponent implements OnInit {

  constructor(public router:Router,
    public loginService:LoginServeiceService,
    public javaService:JavaServiceService,
    public dialog:MatDialog,
    private spinner: NgxSpinnerService) {

   }

  bookList;
  newList;
  notEmptyPost = true;
  notscrolly = true;

  ngOnInit() {
       this.javaService.findBooks(0,3).subscribe((books: any[]) => {
       console.log(books);
       this.bookList = books;
      //  console.log(this.bookList[0]);
      //  this.router.navigate(['/mainslider']);
    });
   // this.relodeData();
  }
  relodeData(){
    this.javaService.getBooks().subscribe((books: any[]) => {
      this.bookList = books;
      console.log(this.bookList);
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

  onScroll() {
    if (this.notscrolly && this.notEmptyPost) 
      this.spinner.show();
      this.notscrolly = false;
      this.loadNextPost();
     
  }

  // load th next 6 posts
    loadNextPost() {
   // const url = 'http://localhost:8080/findBooks';
  //return last post from the array
  //  const min = this.bookList[this.bookList.length - 1];
  // get id of last post
   // console.log("hii:",this.bookList.length);  
  //  const max = min+6;
  // sent this id as key value pare using formdata()
  //  console.log(max);
    this.javaService.findBooks(this.bookList.length,this.bookList.length+3).subscribe((newBookList: any[]) => {
    console.log(newBookList);
    this.newList = newBookList;
    this.spinner.hide();
    if (newBookList.length === 0 ) {
      this.notEmptyPost =  false;
    }
    this.bookList = this.newList.concat(this.newList);
    this.notscrolly = true;
  });  
  
   // const dataToSend = new FormData();
   // dataToSend.append('id', lastPostId);
  // call http request
    // this.http.post(url, dataToSend)
    // .subscribe( (data: any) => {
    //    const newPost = data[0];
    //    this.spinner.hide();
    //    if (newPost.length === 0 ) {
    //      this.notEmptyPost =  false;
    //   }
     // add newly fetched posts to the existing post
      // this.allpost = this.allpost.concat(newPost);
      // this.notscrolly = true;
    //  });
}

}

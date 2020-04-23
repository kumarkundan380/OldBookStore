import { Component, OnInit } from '@angular/core';
import { JavaServiceService } from '../java-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-show-book',
  templateUrl: './show-book.component.html',
  styleUrls: ['./show-book.component.css']
})
export class ShowBookComponent implements OnInit {
  
  bookList:any;
  constructor(public javaService:JavaServiceService,public router:Router) { 
    console.log("praveen is great and vey intelligent...");
    this.bookList=this.javaService.bookList;
  }

  
  searchVal:any;  
  ngOnInit() {
  //     this.searchVal = this.javaService.search;
     console.log("ngonit....");
  //     this.javaService.searchBook(this.searchVal).subscribe((books: any[]) => {
  //     console.log(books);
  //     this.bookList = books;
  //     this.router.navigate(['/showbook']);
  //  });
  }
  // relodeBook(){
  //   this.searchVal = this.javaService.search;
  //     console.log("ngonit....",this.searchVal);
  //     this.javaService.searchBook(this.searchVal).subscribe((books: any[]) => {
  //     console.log(books);
  //     this.bookList = books;
  //    //  this.router.navigate(['/mainslider']);
  //  });
  // }
}

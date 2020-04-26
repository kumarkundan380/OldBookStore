import { Component, OnInit } from '@angular/core';
import { JavaServiceService } from '../java-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-buy-book',
  templateUrl: './buy-book.component.html',
  styleUrls: ['./buy-book.component.css']
})
export class BuyBookComponent implements OnInit {
  bookDetail:any;
  constructor(private javaServiceObj:JavaServiceService,public router:Router) { }

  ngOnInit() {
    this.javaServiceObj.getBookById(this.javaServiceObj.bookId).subscribe((book) => {
      this.bookDetail=book;
      console.log(this.bookDetail);
  });
  }

  viewCart(){
    this.router.navigate(['/checkout']);
  }

}

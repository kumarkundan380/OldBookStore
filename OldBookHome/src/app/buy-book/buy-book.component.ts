import { Component, OnInit, AfterViewInit } from '@angular/core';
import { JavaServiceService } from '../java-service.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-buy-book',
  templateUrl: './buy-book.component.html',
  styleUrls: ['./buy-book.component.css']
})
export class BuyBookComponent implements OnInit,AfterViewInit {

  ngAfterViewInit(): void {  
  } 


  constructor(private javaService:JavaServiceService,public route: ActivatedRoute) { }

  bookDetail:any;
  id:number;
  ngOnInit(): void {
    // this.javaService.getBooks().subscribe((books: any[]) => {
    //   this.bookList = books;
    //   console.log(this.bookList[0]);
    // });
    this.id = this.route.snapshot.params['sellOrderRequestId'];
    this.javaService.getBookById(this.id).subscribe((book) => {
        this.bookDetail=book;
        console.log(this.bookDetail);
    });
  }  
}

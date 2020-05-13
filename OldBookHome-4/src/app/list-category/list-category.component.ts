import { Component, OnInit } from '@angular/core';
import { JavaServiceService } from '../java-service.service';
import { Router } from '@angular/router';
import { AuthenticationService } from '../service/authentication.service';
import { NotificationService } from '../share/notification.service';

@Component({
  selector: 'app-list-category',
  templateUrl: './list-category.component.html',
  styleUrls: ['./list-category.component.css']
})
export class ListCategoryComponent implements OnInit {

  bookList:any;
  constructor(public javaService:JavaServiceService,
    public router:Router,
    public hasLogin:AuthenticationService,
    public notificationService:NotificationService) { }

  ngOnInit() {
    this.javaService.getBookByCategory(this.javaService.categoryList).subscribe(data=>{
      this.bookList=data;
      console.log(data);
    });
  }

  viewBook(sellOrderRequestId:number){
    this.javaService.bookId=sellOrderRequestId;
    this.router.navigate(['/buybook']);
  }
  addToCart(bookId:number){
    if(this.hasLogin.isUserLoggedIn()){
      this.javaService.addSellOrderRequest(bookId);
      this.notificationService.success("book added sucessfully");
    }else{
      this.notificationService.warn("please Login first.");
    }
    
  }

}

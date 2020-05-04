import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../service/authentication.service';
import { JavaServiceService } from '../java-service.service';

@Component({
  selector: 'app-sell-history',
  templateUrl: './sell-history.component.html',
  styleUrls: ['./sell-history.component.css']
})
export class SellHistoryComponent implements OnInit {

  bookList:any;
  date:any=[];
  constructor(public hasLogin:AuthenticationService,
    public javaService:JavaServiceService) { }

  ngOnInit(): void {
    if(this.hasLogin.isUserLoggedIn()){
      this.javaService.sellUserId=sessionStorage.getItem('username');
      this.javaService.getSellHistory(this.javaService.sellUserId).subscribe(data=>{
        console.log(data);
        this.bookList=data;
      });
      this.javaService.sellDate(this.javaService.sellUserId).subscribe(data=>{
        this.date=data;
       // console.log(data);
      });
    }

  }

}

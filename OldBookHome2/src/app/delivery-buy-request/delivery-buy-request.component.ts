import { Component, OnInit } from '@angular/core';
import { JavaServiceService } from '../java-service.service';
import { MatDialogConfig, MatDialog } from '@angular/material/dialog';
import { UpdateBookStatusService } from '../share/update-book-status.service';
import { UpdateBookStatusComponent } from '../update-book-status/update-book-status.component';

@Component({
  selector: 'app-delivery-buy-request',
  templateUrl: './delivery-buy-request.component.html',
  styleUrls: ['./delivery-buy-request.component.css']
})
export class DeliveryBuyRequestComponent implements OnInit {

  array:any;
  constructor(public  javaServiceObj:JavaServiceService,
    public dialog: MatDialog,
    public updateService:UpdateBookStatusService) { }

  ngOnInit() {
    if(this.javaServiceObj.hasAdminRole()){
      this.javaServiceObj.getDeliverySellRequestAdmin().subscribe(
        data=>{
          this.array=data;
         // console.log(data);
        });
    }else{
      this.javaServiceObj.getDeliverySellRequest().subscribe(
        data=>{
          this.array=data;
        //  console.log(data);
        });
    }   
  }
  updateStatus(bookId:number){
    this.javaServiceObj.deliveryForBuy=true;
    this.javaServiceObj.bookId=this.array[bookId][0];
    this.updateService.initializeFormGroup();
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "40%";
    this.dialog.open(UpdateBookStatusComponent,dialogConfig);
  }

}

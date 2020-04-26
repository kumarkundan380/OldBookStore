import { Component, OnInit } from '@angular/core';
import { AddAddressService } from '../share/add-address.service';
import { MatDialog,MatDialogConfig } from '@angular/material/dialog';
import { BookDeliverAddressComponent } from '../book-deliver-address/book-deliver-address.component';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {

  constructor(public addreqService:AddAddressService,
    public dialog:MatDialog) { }

  ngOnInit() {
  }

  buyNow(){
    this.addreqService.initializeFormGroup();
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "50%";
    this.dialog.open(BookDeliverAddressComponent,dialogConfig);
  }

}

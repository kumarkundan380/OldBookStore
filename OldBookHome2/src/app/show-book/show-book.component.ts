import { Component, OnInit } from '@angular/core';
import { JavaServiceService } from '../java-service.service';
import { AddAddressService } from '../share/add-address.service';
import { MatDialog,MatDialogConfig } from '@angular/material/dialog';
import { BookDeliverAddressComponent } from '../book-deliver-address/book-deliver-address.component';

@Component({
  selector: 'app-show-book',
  templateUrl: './show-book.component.html',
  styleUrls: ['./show-book.component.css']
})
export class ShowBookComponent implements OnInit {
  bookList:any;
  constructor(public javaService:JavaServiceService,
    public addreqService:AddAddressService,
    public dialog:MatDialog) { 
    this.bookList=this.javaService.bookList;
  }

  ngOnInit() {
  }

  buyBook(){
    this.addreqService.initializeFormGroup();
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = "50%";
    this.dialog.open(BookDeliverAddressComponent,dialogConfig);
  }

}

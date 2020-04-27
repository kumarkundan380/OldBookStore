import { Component, OnInit } from '@angular/core';
import { UpdateBookStatusService } from '../share/update-book-status.service';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { JavaServiceService } from '../java-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-update-book-status',
  templateUrl: './update-book-status.component.html',
  styleUrls: ['./update-book-status.component.css']
})
export class UpdateBookStatusComponent implements OnInit {
    
    status:any;
    
    constructor(public bookStatus:UpdateBookStatusService,
      public dialogRef: MatDialogRef<UpdateBookStatusComponent>,
      public dialog: MatDialog,
      public javaService:JavaServiceService,
      public router:Router) { }

    ngOnInit() {
    }
    onSubmit() {
      if (this.bookStatus.form.valid) {
      this.status=this.bookStatus.form.value;
      this.status.sellOrderRequestId=this.javaService.bookId;
      console.log(this.status);
      this.javaService.updateBookStatus(this.status);
      this.bookStatus.form.reset();
      this.bookStatus.initializeFormGroup();
      this.onClose(); 
    }
  }

  onClose() {
    this.bookStatus.form.reset();
    this.bookStatus.initializeFormGroup();
    this.dialogRef.close();
  }
}
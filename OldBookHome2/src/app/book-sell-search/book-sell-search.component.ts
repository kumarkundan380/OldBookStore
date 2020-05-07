import { Component, OnInit } from '@angular/core';
import { JavaServiceService } from '../java-service.service';
import { MatDialogRef, MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { BookSellSearchService } from '../share/book-sell-search.service';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-book-sell-search',
  templateUrl: './book-sell-search.component.html',
  styleUrls: ['./book-sell-search.component.css']
})
export class BookSellSearchComponent implements OnInit {

  constructor(public loginService: BookSellSearchService,
    public dialogRef: MatDialogRef<BookSellSearchComponent>,
    public dialog: MatDialog,
    public javaService:JavaServiceService,
    public spinner:NgxSpinnerService,
    private router:Router) {

  }

  ngOnInit() {
  }

  onSubmit() {
    if (this.loginService.form.valid) {
      this.javaService.bookISBN=this.loginService.form.value;
      this.loginService.form.reset();
      this.loginService.initializeFormGroup();
      this.onClose();
      // this.spinner.show();
      // setTimeout(() => {
      // this.spinner.hide();
      // }, 2000);
      this.router.navigate(['/booksell']);
    }
  }

  onClose() {
    this.loginService.form.reset();
    this.loginService.initializeFormGroup();
    this.dialogRef.close();
  }

}

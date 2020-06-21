import { Component, OnInit } from '@angular/core';
import { NgxSpinnerService } from 'ngx-spinner';
import { JavaServiceService } from '../java-service.service';

@Component({
  selector: 'app-refresh',
  templateUrl: './refresh.component.html',
  styleUrls: ['./refresh.component.css']
})
export class RefreshComponent implements OnInit {

  constructor(public spinner: NgxSpinnerService,
              public javaService: JavaServiceService) { }

  ngOnInit() {
    this.javaService.getSpinner(1000);
  }
}

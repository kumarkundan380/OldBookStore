import { Component, OnInit, NgZone } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MatDialogRef } from '@angular/material/dialog';
import { NgxSpinnerService } from 'ngx-spinner';
import { Router } from '@angular/router';
import { NotificationService } from '../share/notification.service';
import { JavaServiceService,Payment } from '../java-service.service';
import { PaymentService } from '../share/payment.service';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})

export class PaymentComponent implements OnInit {

  hide = true;
  paymentInfo: any;
  parsedJson:any;
  data:any;
  payment=new Payment();

  constructor(public paymentService: PaymentService,
    public javaService: JavaServiceService,
    public notificationService: NotificationService,
    public router: Router,
    public http: HttpClient,
    public zone: NgZone,
    public spinner: NgxSpinnerService,
    public dialogRef: MatDialogRef<PaymentComponent>) { }

  ngOnInit() {
    let amount=this.javaService.totalPrice
    this.paymentService.setFormValue(amount);
  }
  onClear() {
    this.paymentService.form.reset();
    this.paymentService.initializeFormGroup();
  }

  onClose() {
    this.paymentService.form.reset();
    this.paymentService.initializeFormGroup();
    this.dialogRef.close();
  }

  //this method is use to get all information of card
  chargeCreditCard(payment:Payment) {
    (<any>window).Stripe.card.createToken({
      name: payment.name,
      number: payment.cardNumber,
      exp_month: payment.expMonth,
      exp_year: payment.expYear,
      cvc: payment.cvc,
  }, (status: number, response: any) => {
      if (status === 200) {
        let token = response.id;
        this.javaService.chargeCard(token,payment.amount);
      } else {
        console.log(response.error.message);
      }
    });
    this.javaService.getSpinner(3000);
    this.dialogRef.close();
  }
  
  onSubmit() {
    if (this.paymentService.form.valid) {
      this.payment=this.paymentService.form.value;
      console.log(this.payment);
      this.chargeCreditCard(this.payment);
    }
  }
}

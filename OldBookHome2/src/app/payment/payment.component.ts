import { Component, OnInit, NgZone } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { NotificationService } from '../share/notification.service';
import { JavaServiceService } from '../java-service.service';
import { Router } from '@angular/router';
import { PaymentService } from '../share/payment.service';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { NgxSpinnerService } from 'ngx-spinner';
import { from } from 'rxjs';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {

  hide = true;
  paymentInfo: any;
  parsedJson:any;
  constructor(public paymentService: PaymentService,
    public javaService: JavaServiceService,
    public notificationService: NotificationService,
    public router: Router,
    public http: HttpClient,
    public zone: NgZone,
    public spinner: NgxSpinnerService,
    public dialogRef: MatDialogRef<PaymentComponent>) { }

  ngOnInit(): void {
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

  chargeCreditCard(cc_name, cardNumber, expMonth, expYear, cvc, amount) {
    let form = document.getElementsByTagName("form")[0];
    (<any>window).Stripe.card.createToken({
      name: cc_name,
      number: cardNumber,
      exp_month: expMonth,
      exp_year: expYear,
      cvc: cvc
    }, (status: number, response: any) => {
      if (status === 200) {
        let token = response.id;
        this.chargeCard(token, amount);
      } else {
        console.log(response.error.message);
      }
    });
    //console.log(cardNumber);
  }

  chargeCard(token: string, amount1) {
    //  console.log(amount1);
    let amount: number = +amount1;
    const headers = new HttpHeaders({ 'token': token });
    this.http.post('http://localhost:8080/charge', amount, { headers: headers, responseType: 'text' })
      .subscribe(resp => {
        this.paymentInfo = resp;
        //  console.log(resp);
        //console.log(this.paymentInfo);
        // console.log(this.paymentInfo.id);
        // console.log(this.paymentInfo.balanceTransaction);
        this.parsedJson = JSON.parse(this.paymentInfo); 
        console.log(this.parsedJson);
        console.log(this.parsedJson.id);
        console.log(this.parsedJson.balanceTransaction);
        console.log(this.parsedJson.status);
        console.log(this.parsedJson.amount);
        console.log(this.parsedJson.created);
        this.javaService.payment.payment_id=this.parsedJson.id;
        this.javaService.payment.transaction_id=this.parsedJson.balanceTransaction;
        this.javaService.payment.status=this.parsedJson.status;
        this.javaService.payment.amount=this.parsedJson.amount;
        this.javaService.payment.created=this.parsedJson.created;
      });
    this.javaService.savePaymentDetails(this.javaService.payment);  
    this.spinner.show();
    setTimeout(() => {
      this.spinner.hide();
    }, 1000);
    this.notificationService.success(':: Payment successfully');
    this.dialogRef.close();
    this.router.navigate(['mainslider']);
    //  this.zone.run(() => this.router.navigate(['mainslider']));
    
  }


}

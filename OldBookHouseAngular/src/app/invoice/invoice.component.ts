import { Component, OnInit } from '@angular/core';
import { JavaServiceService } from '../java-service.service';
import * as jspdf from 'jspdf';
import html2canvas from 'html2canvas';

@Component({
  selector: 'app-invoice',
  templateUrl: './invoice.component.html',
  styleUrls: ['./invoice.component.css']
})
export class InvoiceComponent implements OnInit {

  invoiceData: any = [];
  total = 0;
  constructor(public javaServiceObj: JavaServiceService) { }

  // this method is use to calculate total price
  getTotal() {
    for (const invoice of this.invoiceData) {
      this.total += invoice[10] * invoice[11];
    }
  }
  ngOnInit() {
   // console.log(this.javaServiceObj.paymentData);
    this.javaServiceObj.getInvoice(this.javaServiceObj.payment.transactionId).subscribe(
      data => {
        this.invoiceData = data;
        this.getTotal();
      });
  }

  // this method is use to generate pdf
  public convetToPDF() {
    const data = document.getElementById('contentToConvert');
    html2canvas(data).then(canvas => {
        const imgWidth = 200;
        const pageHeight = 295;
        const imgHeight = 50;
        const heightLeft = imgHeight;
        const contentDataURL = canvas.toDataURL('image/png');
        const pdf = new jspdf('p', 'mm', 'a4');
        const position = 0;
        pdf.addImage(contentDataURL, 'PNG', 0, position, imgWidth, imgHeight);
        pdf.save('new-file.pdf');
    });
  }

}

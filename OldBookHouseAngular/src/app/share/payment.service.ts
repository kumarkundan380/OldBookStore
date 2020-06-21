import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  constructor() { }
  form: FormGroup = new FormGroup({
    name: new FormControl('', Validators.required),
    cardNumber: new FormControl('', Validators.required),
    expMonth: new FormControl('', Validators.required),
    expYear: new FormControl('', Validators.required),
    cvc: new FormControl('', Validators.required),
    amount: new FormControl(''),
  });

  initializeFormGroup() {
    this.form.setValue({
      name: '',
      cardNumber: '',
      expMonth: '',
      expYear: '',
      cvc: '',
      amount: 0.0,
    });
  }
  setFormValue(data: any) {
    this.form.controls.amount.setValue(data);
  }

}

import { Component, OnInit } from '@angular/core';
import { JavaServiceService,UserInfo } from '../java-service.service';
import { AddAddressService } from '../share/add-address.service';
import { NotificationService } from '../share/notification.service';
import { MatDialog,MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-book-deliver-address',
  templateUrl: './book-deliver-address.component.html',
  styleUrls: ['./book-deliver-address.component.css']
})
export class BookDeliverAddressComponent implements OnInit {


  userInfo=new UserInfo();
  isShowAddress:boolean=false;
  address:any;

  constructor(
    public addaddressservice:AddAddressService,
    public notificationService:NotificationService,
    public dialogRef:MatDialogRef<BookDeliverAddressComponent>,
    public dialog: MatDialog,
    public javaServiceObj:JavaServiceService) { }

  ngOnInit(): void {
    this.javaServiceObj.getAddress().subscribe(
      (data)=>{
        this.address=data;
      });
  }

  onClear() {
    this.addaddressservice.form.reset();
    this.addaddressservice.initializeFormGroup();
    this.notificationService.warn(':: Clear successfully');
  }
  sendId(id:number){
    this.javaServiceObj.bookObj.addressId=id;
    this.javaServiceObj.requestBookDetails(this.javaServiceObj.bookObj);
    this.onClose();
  }
  onSubmit() {
    if (this.addaddressservice.form.valid) {
      this.userInfo=this.addaddressservice.form.value;
      this.javaServiceObj.addAddress(this.userInfo).subscribe(
        data=>{
          this.javaServiceObj.bookObj.addressId=this.address.address[this.address.address.length-1].id;
          this.sendId(this.javaServiceObj.bookObj.addressId);
        });
      this.addaddressservice.form.reset();
      this.addaddressservice.initializeFormGroup();
      this.notificationService.success(':: Submitted successfully');
      this.onClose();
    }
  }
    
  onClose() {
    this.addaddressservice.form.reset();
    this.addaddressservice.initializeFormGroup();
    this.dialogRef.close();
  }
  addAddress(){
    this.isShowAddress=true;
  }

}

import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { JavaServiceService,UserInfo } from '../java-service.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  constructor(public javaServiceObj:JavaServiceService,public dialogRef:MatDialogRef<ProfileComponent>) { }

  userInfo=new UserInfo();
  profile:any;
  
  ngOnInit(): void {
    this.javaServiceObj.getAddress().subscribe(
      (data)=>{
        this.profile=data;
        console.log(this.profile);
      });
  }
  
  onClose() {
    this.dialogRef.close();
  }

}

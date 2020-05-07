import { Component, OnInit } from '@angular/core';
import { EdituserService } from '../share/edituser.service';
import { MatDialogRef } from '@angular/material/dialog';
import { NotificationService } from '../share/notification.service';
import { JavaServiceService, UserDetail } from '../java-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  userRecord: any;

  roles: any[] = [
    {value: 'user', viewValue: 'User'},
    {value: 'deliveryPerson', viewValue: 'Delivery Person'},
    {value: 'admin', viewValue: 'Admin'}
  ];
  constructor(public edituserService: EdituserService,
    public javaService: JavaServiceService,
    public notificationService: NotificationService,
    public router: Router,
    public dialogRef: MatDialogRef<EditUserComponent>) { }

  ngOnInit(): void {
    this.userRecord = new UserDetail();
    this.javaService.getUserById(this.javaService.userId).subscribe(data => {
      this.userRecord = data;
      let json = {
        userId: this.userRecord.userId,
        firstName: this.userRecord.firstName,
        lastName: this.userRecord.lastName,
        email: this.userRecord.email,
        mobileNumber: this.userRecord.mobileNumber,
        role:this.userRecord.role,
      };
      this.edituserService.setFormValue(json);
    }, error => console.log(error));

  }

  onSubmit() {
    if (this.edituserService.form.valid) {
      this.javaService.updateUserDetail(this.edituserService.form.value)
        .subscribe(data => {
            this.router.navigateByUrl('/refresh', { skipLocationChange: true }).then(() => {
            this.router.navigateByUrl('userList');
          });
        });
      this.notificationService.success(':: Updated successfully');
    }
    this.onClose();
  }

  onClear() {
    this.edituserService.form.reset();
    this.edituserService.initializeFormGroup();
  }

  onClose() {
    this.edituserService.form.reset();
    this.edituserService.initializeFormGroup();
    this.dialogRef.close();
  }

}

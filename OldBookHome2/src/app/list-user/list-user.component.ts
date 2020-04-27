import { Component, OnInit } from '@angular/core';
import { JavaServiceService } from '../java-service.service';
import { EditUserComponent } from '../edit-user/edit-user.component';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';

@Component({
  selector: 'app-list-user',
  templateUrl: './list-user.component.html',
  styleUrls: ['./list-user.component.css']
})
export class ListUserComponent implements OnInit {

  userList: any = [];
  // name:string;
  // mobileNumber:string;
  // role:string;
  constructor(public javaService: JavaServiceService,
    public dialog: MatDialog) { }

  ngOnInit(): void {
    this.javaService.userList().subscribe((user: any[]) => {
      this.userList = user;
      console.log(user);
      // this.name=this.userList.firstName+" "+this.userList.lastName;
      // this.mobileNumber=this.userList.mobileNumber;
      // this.role=this.userList.role;
    });
  }

  updateUser(userId: number) {
    console.log("update user called");
    this.javaService.userId = userId;
    console.log(userId);
    const editDialog = new MatDialogConfig();
    editDialog.disableClose = true;
    editDialog.autoFocus = true;
    editDialog.width = "50%";
    this.dialog.open(EditUserComponent, editDialog);
  }
  deleteUser(userId: number) {
    console.log("delete user called");
   // console.log(userId);
    //this.javaService.userId = userId;
    this.javaService.deleteUser(userId).subscribe(data=>{
      console.log(data);
    });
  }

}

import { Component, OnInit } from '@angular/core';
import { JavaServiceService } from '../java-service.service';
import { EditUserComponent } from '../edit-user/edit-user.component';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { NotificationService } from '../share/notification.service';
import { Router } from '@angular/router';
import * as Chart from 'chart.js';

@Component({
  selector: 'app-list-user',
  templateUrl: './list-user.component.html',
  styleUrls: ['./list-user.component.css']
})
export class ListUserComponent implements OnInit {

  userList: any = [];
  adminNo:number=0;
  userNo:number=0;
  deliveryPersonNo:number=0;
  canvas: any;
  ctx: any;

  constructor(public javaService: JavaServiceService,
    public notificationService:NotificationService,
    public router:Router,
    public dialog: MatDialog) { }

  ngOnInit(): void {
    this.javaService.userList().subscribe((user: any[]) => {
    this.userList = user;
    //console.log(this.userList.length);
    for (let index = 0; index < this.userList.length; index++) {
      if(this.userList[index].role==='admin'){
        this.adminNo++;
      }else if(this.userList[index].role==='user'){
        this.userNo++;
      }else{
        this.deliveryPersonNo++;
      }
    }
    //console.log(this.adminNo);
    this.chartGraph();
    });
  }

  updateUser(userId: number) {
   // console.log("update user called");
    this.javaService.userId = userId;
   // console.log(userId);
    const editDialog = new MatDialogConfig();
    editDialog.disableClose = true;
    editDialog.autoFocus = true;
    editDialog.width = "50%";
    this.dialog.open(EditUserComponent, editDialog);
  }

  deleteUser(userId: number) {
    this.javaService.deleteUser(userId).subscribe(data=>{
      this.router.navigateByUrl('/refresh', { skipLocationChange: true }).then(() => {
      this.router.navigateByUrl('/userList');
     });
    });
    this.notificationService.warn("User deleted Sucessfully.")
  }
  chartGraph(){
    this.canvas = document.getElementById('myChart');
    this.ctx = this.canvas.getContext('2d');
    let myChart = new Chart(this.ctx, {
      type: 'pie',
      data: {
          labels: ["Admin", "DeliveryPerson", "User"],
          datasets: [{
              label: '# of Votes',
              data: [this.adminNo,this.deliveryPersonNo,this.userNo],
              backgroundColor: [
                  'rgba(255, 99, 132, 1)',
                  'rgba(54, 162, 235, 1)',
                  'rgba(255, 206, 86, 1)'
              ],
              borderWidth: 1
          }]
      },
      options: {
        responsive: false,
        display:true
      }
    });
  }
}

import { Component, OnInit } from '@angular/core';
import { JavaServiceService } from '../java-service.service';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { EditUserComponent } from '../edit-user/edit-user.component';
import { Router } from '@angular/router';
import * as Chart from 'chart.js';
import { DialogService } from '../share/dialog.service';
import { NotificationService } from '../share/notification.service';


@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  userList: any[] = [];
  adminNo = 0;
  userNo = 0;
  deliveryPersonNo = 0;
  canvas: any;
  ctx: any;

  constructor(public javaService: JavaServiceService,
              public dialogService: DialogService,
              public dialog: MatDialog,
              public router: Router,
              public notificationService: NotificationService) { }

  ngOnInit() {
    this.javaService.userList().subscribe((user: any[]) => {
      this.userList = user;
      console.log(this.userList);
      for (const index of this.userList) {
        //  console.log(typeof(this.userList));
          if (index.role === 'admin') {
            this.adminNo++;
          } else if (index.role === 'user') {
            this.userNo++;
          } else {
            this.deliveryPersonNo++;
          }
      }
     // console.log(this.adminNo);
      this.chartGraph();
    });
  }

 // this method is use to open the EditUserComponent
  updateUser(userId: number) {
  //  console.log('update user called');
    this.javaService.userId = userId;
    const editDialog = new MatDialogConfig();
    editDialog.disableClose = true;
    editDialog.autoFocus = true;
    editDialog.width = '50%';
    this.dialog.open(EditUserComponent, editDialog);
  }

 // this method is use to delete the user
  deleteUser(userId: number) {
    this.dialogService.openConfirmDialog('Are you sure to delete this user ?')
      .afterClosed().subscribe(res => {
        if (res) {
          this.javaService.deleteUser(userId).subscribe(data => {
            this.router.navigateByUrl('/refresh', { skipLocationChange: true }).then(() => {
            this.router.navigateByUrl('/userList');
            this.notificationService.warn('! Deleted successfully');
          });
        });
      }
    });
  }

 // this method is use to draw the chart
  chartGraph() {
    this.canvas = document.getElementById('myChart');
    this.ctx = this.canvas.getContext('2d');
    const myChart = new Chart(this.ctx, {
      type: 'pie',
      data: {
          labels: ['Admin', 'DeliveryPerson', 'User'],
          datasets: [{
              label: '# of Votes',
              data: [this.adminNo, this.deliveryPersonNo, this.userNo],
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
        display: true
      }
    });

  }

}

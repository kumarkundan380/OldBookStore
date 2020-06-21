import { Component, OnInit } from '@angular/core';
import { LoginServeiceService } from '../share/login-serveice.service';
import { MatDialogRef, MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { RegistrationComponent } from '../registration/registration.component';
import { RegistrationService } from '../share/registration.service';
import { UserLogin, JavaServiceService } from '../java-service.service';
import { AuthenticationService } from '../service/authentication.service';
import { Router } from '@angular/router';
import { ForgetPasswordComponent } from '../forget-password/forget-password.component';
import { ForgetPasswordService } from '../share/forget-password.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  userLogin = new UserLogin();
  islogin = false;

  constructor(public loginService: LoginServeiceService,
              public registrationService: RegistrationService,
              public forgetPasswordService: ForgetPasswordService,
              public dialogRef: MatDialogRef<LoginComponent>,
              public javaCallObj: JavaServiceService,
              public dialog: MatDialog,
              public loginAuth: AuthenticationService,
              public router: Router) {}

  ngOnInit(): void {
  }

  // this method is use to login the user and set data in sessionStorage
  onSubmit() {
    this.islogin = false;
    if (this.loginService.form.valid) {
      this.userLogin = this.loginService.form.value;
      this.loginAuth.authenticate(this.userLogin.userName.trim(), this.userLogin.userPassword).subscribe(
        data => {
          sessionStorage.setItem('username', this.userLogin.userName);
          const tokenStr = 'Bearer ' + data.token;
          sessionStorage.setItem('token', tokenStr);
          this.router.navigate(['/mainslider']);
          this.javaCallObj.getRole();
          this.javaCallObj.getBookNotification();
          this.loginService.form.reset();
          this.loginService.initializeFormGroup();
          this.onClose();
        },
        error => {
        this.islogin = true;
        });
    }
  }
 // this method is use to close login component
  onClose() {
    this.loginService.form.reset();
    this.loginService.initializeFormGroup();
    this.dialogRef.close();
  }

  // this method is use to open forgetPassoword component
  forgetPassoword() {
    this.onClose();
    this.forgetPasswordService.initializeFormGroup();
    const passwordDialog = new MatDialogConfig();
    passwordDialog.disableClose = true;
    passwordDialog.autoFocus = true;
    passwordDialog.width = '40%';
    this.dialog.open(ForgetPasswordComponent, passwordDialog);
  }

  // this method is use to open registration component
  registration() {
    this.onClose();
    this.registrationService.initializeFormGroup();
    const registratinDialog = new MatDialogConfig();
    registratinDialog.disableClose = true;
    registratinDialog.autoFocus = true;
    registratinDialog.width = '60%';
    this.dialog.open(RegistrationComponent, registratinDialog);
  }
}

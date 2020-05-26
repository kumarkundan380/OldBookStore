import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { NgxSpinnerModule } from "ngx-spinner";
import { InfiniteScrollModule } from 'ngx-infinite-scroll';
import { LoginServeiceService } from './share/login-serveice.service';
import { RegistrationService } from './share/registration.service';
import { NotificationService } from './share/notification.service';
import { PaymentService } from './share/payment.service';
import { DialogService } from './share/dialog.service';
import { AuthGardService } from './service/auth-gard.service';
import { JavaServiceService } from './java-service.service';
import { BasicAuthHttpInterceptorService } from './service/basic-auth-http-interceptor.service';
import { MaterialModule } from './material/material.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { MainsliderComponent } from './mainslider/mainslider.component';
import { ProductdisplayComponent } from './productdisplay/productdisplay.component';
import { FooterComponent } from './footer/footer.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { BooksellComponent } from './booksell/booksell.component';
import { BookreqAddressComponent } from './bookreq-address/bookreq-address.component';
import { BookSellSearchComponent } from './book-sell-search/book-sell-search.component';
import { DeliveryRequestComponent } from './delivery-request/delivery-request.component';
import { UpdateBookStatusComponent } from './update-book-status/update-book-status.component';
import { ProfileComponent } from './profile/profile.component';
import { ShowBookComponent } from './show-book/show-book.component';
import { BuyBookComponent } from './buy-book/buy-book.component';
import { BookDeliverAddressComponent } from './book-deliver-address/book-deliver-address.component';
import { DeliveryBuyRequestComponent } from './delivery-buy-request/delivery-buy-request.component';
import { UserListComponent } from './user-list/user-list.component';
import { EditUserComponent } from './edit-user/edit-user.component';
import { RefreshComponent } from './refresh/refresh.component';
import { BuyHistoryComponent } from './buy-history/buy-history.component';
import { SellHistoryComponent } from './sell-history/sell-history.component';
import { ForgetPasswordComponent } from './forget-password/forget-password.component';
import { UpdateBookPriceComponent } from './update-book-price/update-book-price.component';
import { UpdatePriceComponent } from './update-price/update-price.component';
import { PaymentComponent } from './payment/payment.component';
import { ContactComponent } from './contact/contact.component';
import { InvoiceComponent } from './invoice/invoice.component';
import { ConfirmDialogComponent } from './confirm-dialog/confirm-dialog.component';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    MainsliderComponent,
    ProductdisplayComponent,
    FooterComponent,
    CheckoutComponent,
    LoginComponent,
    RegistrationComponent,
    BooksellComponent,
    BookreqAddressComponent,
    BookSellSearchComponent,
    DeliveryRequestComponent,
    UpdateBookStatusComponent,
    ProfileComponent,
    ShowBookComponent,
    BuyBookComponent,
    BookDeliverAddressComponent,
    DeliveryBuyRequestComponent,
    UserListComponent,
    EditUserComponent,
    RefreshComponent,
    BuyHistoryComponent,
    SellHistoryComponent,
    ForgetPasswordComponent,
    UpdateBookPriceComponent,
    UpdatePriceComponent,
    PaymentComponent,
    ContactComponent,
    InvoiceComponent,
    ConfirmDialogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    FormsModule,
    MaterialModule,
    HttpClientModule,
    InfiniteScrollModule,
    NgxSpinnerModule
  ],
  providers: [LoginServeiceService,RegistrationService,NotificationService,JavaServiceService,PaymentService,DialogService,AuthGardService,
    {    
      provide:HTTP_INTERCEPTORS,useClass:BasicAuthHttpInterceptorService,multi:true
    }
  ],
  bootstrap: [AppComponent],
  entryComponents:[MainsliderComponent,ConfirmDialogComponent]

})
export class AppModule { }

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { MainsliderComponent } from './mainslider/mainslider.component';
import { ProductdisplayComponent } from './productdisplay/productdisplay.component';
import { FooterComponent } from './footer/footer.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { LoginComponent } from './login/login.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material/material.module';
import { LoginServeiceService } from './share/login-serveice.service';
import { RegistrationComponent } from './registration/registration.component';
import { RegistrationService } from './share/registration.service';
import { NotificationService } from './share/notification.service';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { BasicAuthHttpInterceptorService } from './service/basic-auth-http-interceptor.service';
import { AuthGardService } from './service/auth-gard.service';
import { BooksellComponent } from './booksell/booksell.component';
import { BookreqAddressComponent } from './bookreq-address/bookreq-address.component';
import { BookSellSearchComponent } from './book-sell-search/book-sell-search.component';
import { DeliveryRequestComponent } from './delivery-request/delivery-request.component';
import { UpdateBookStatusComponent } from './update-book-status/update-book-status.component';
import { BookDeliverAddressComponent } from './book-deliver-address/book-deliver-address.component';
import { InfiniteScrollModule } from 'ngx-infinite-scroll';
import { ProfileComponent } from './profile/profile.component';
import { ShowBookComponent } from './show-book/show-book.component';
import { BuyBookComponent } from './buy-book/buy-book.component';
import { DeliveryBuyRequestComponent } from './delivery-buy-request/delivery-buy-request.component';
import { ListUserComponent } from './list-user/list-user.component';
import { EditUserComponent } from './edit-user/edit-user.component';
import { NgxSpinnerModule } from "ngx-spinner";
import { BuyHistoryComponent } from './buy-history/buy-history.component';
import { SellHistoryComponent } from './sell-history/sell-history.component';


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
    ListUserComponent,
    EditUserComponent,
    BuyHistoryComponent,
    SellHistoryComponent
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
  providers: [LoginServeiceService,RegistrationService,NotificationService,AuthGardService,
    {    
      provide:HTTP_INTERCEPTORS,useClass:BasicAuthHttpInterceptorService,multi:true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HeaderComponent } from './header/header.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { MainsliderComponent } from './mainslider/mainslider.component';
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
import { ListUserComponent } from './list-user/list-user.component';
import { EditUserComponent } from './edit-user/edit-user.component';
import { DeliveryBuyRequestComponent } from './delivery-buy-request/delivery-buy-request.component';
import { BuyHistoryComponent } from './buy-history/buy-history.component';
import { SellHistoryComponent } from './sell-history/sell-history.component';
import { RefreshComponent } from './refresh/refresh.component';
import { PaymentComponent } from './payment/payment.component';

const routes: Routes = [
  {path:'',redirectTo:'/mainslider',pathMatch:'full'},
  {path:'home',component:HeaderComponent}, 
  {path:'mainslider',component:MainsliderComponent},
  {path:'checkout',component:CheckoutComponent},
  {path:'login',component:LoginComponent},
  {path:'registration',component:RegistrationComponent},
  {path:'booksell',component:BooksellComponent},
  {path:'address',component:BookreqAddressComponent},
  {path:'booksellsearch',component:BookSellSearchComponent},
  {path:'deliveryRequest',component:DeliveryRequestComponent},
  {path:'bookStatus',component:UpdateBookStatusComponent},
  {path:'profile',component:ProfileComponent},
  {path:'showbook',component:ShowBookComponent},
  {path:'buybook',component:BuyBookComponent},
  {path:'checkout',component:CheckoutComponent},
  {path:'listUser',component:ListUserComponent},
  {path:'deliverBuyRequest',component:DeliveryBuyRequestComponent},
  {path:'editUser',component:EditUserComponent},
  {path:'refresh',component:RefreshComponent},
  {path:'buyHistory',component:BuyHistoryComponent},
  {path:'sellHistory',component:SellHistoryComponent},
  {path:'payment',component:PaymentComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents=[MainsliderComponent,CheckoutComponent,LoginComponent,
  RegistrationComponent,BooksellComponent,BookreqAddressComponent,BookSellSearchComponent,
  DeliveryRequestComponent,UpdateBookStatusComponent,ProfileComponent,ShowBookComponent,
  BuyBookComponent]

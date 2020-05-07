import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { NgxSpinnerService } from "ngx-spinner";

// registration details 
export class UserInfo{
  public firstName:string;
  public lastName: string;
  public email: string;
  public mobileNumber:string;
  public password:string; 
  public confirmPassword: string;
  public address: string;
  public address2: string;
  public district: string;
  public location: string;
  public pinCode: string;
  public state: string;
} 

export class UserDetail{
  public userId:number;
  public firstName:string;
  public lastName: string;
  public email: string;
  public mobileNumber:string;
  public role:string;
}

// login details
export class UserLogin{
  public userName:string;
  public userPassword:string;
}

// book details
export class BookDetails{
  public book_name:string;
  public authors:string;
  public description:string; 
  public publisher:string;
  public publishedDate:string;
  public categories:string;
  public contentVersion:string;
  public isbn_type_10:string; 
  public isbnNo1:number; 
  public isbn_type_13:string;
  public isbnNo2:number;
  public smallThumbnail:string;
  public thumbnail:string;
  public amount:number=0.0;
  public currencyCode:string;
  public checkPrice:boolean;
  public addressId:number;
  public date:string;
}

@Injectable({
  providedIn: 'root'
})
export class JavaServiceService {

  bookObj=new BookDetails();
  bookISBN:any;
  bookId:any;
  bookList:any;
  notification:any;
  sucessNotification:any=0;
  pendingNotification:any;
  checkCart:boolean=false;
  addressNo:number;
  deliveryForBuy:boolean=false;
  userId:number;
  sellUserId:string;
  buyUserId:string;
  totalPrice:number=0;
  // userRole:string;
  
  private urls:string;
  constructor(private http:HttpClient, private router:Router,
    public spinner:NgxSpinnerService) { 
  }
  public url="http://localhost:8080/";
  register(data:UserInfo){
    this.urls=this.url+"add";
    return this.http.post(this.urls,data);
  }
  getRole(){
    this.urls=this.url+"getRole";
    this.http.get(this.urls,{responseType: 'text'}).subscribe(
      data=>{
        sessionStorage.setItem('userRole',data);
      });
  }
  requestBookDetails(bookObj:BookDetails ){
    this.urls=this.url+"bookDetailsRequest";
    this.http.post(this.urls,bookObj).subscribe(
      data=>{
      });
  }
  findBookByAuthor(author:string){
    this.urls=this.url+"fetchAuthor";
    return this.http.post(this.urls,author);
  }

  userList(){
    this.urls=this.url+"listUser";
    return this.http.get(this.urls);
  }

  addAddress(data:UserInfo){
    this.urls=this.url+"addAddress";
    return this.http.post(this.urls,data);
  }
  getAddress(){
    this.urls=this.url+"getAddress";
    return this.http.get(this.urls);
  }
  hasAdminRole(){
    if(sessionStorage.getItem('userRole')==="admin"){
      return true;
    }
    return false;
  }
  hasRole(){
    if(sessionStorage.getItem('userRole')==="deliveryPerson"){
      return true;
    }
    return false;
  }

  // deliveryPerson get the notificaton for bookBuy
  getDeliveryRequest(){
    this.urls=this.url+"getRequest";
    return this.http.get(this.urls);
  }

  updateBookStatus(status:any){
    this.urls=this.url+"bookStatus";
    this.http.post(this.urls,status).subscribe(
      data=>{
        this.spinner.show();
        setTimeout(() => {
          this.spinner.hide();
        }, 1000);
        this.router.navigateByUrl('/refresh', { skipLocationChange: true }).then(() => {
          this.router.navigateByUrl('deliveryRequest');
        });
      });
  }


  // deliveryPerson get the notificaton for bookSell 
  getDeliverySellRequest(){
    this.urls=this.url+"getSellRequest";
    return this.http.get(this.urls);
  }

  findBooks(min:number,max:number){
    this.urls=this.url+"findBooks/"+`${min}` +"/" + `${max}`;
    return this.http.get(this.urls);
  }

  searchBook(searchValue:string){
    this.urls=this.url+"searchBook";
    this.http.post(this.urls,searchValue).subscribe((books:any[]) =>
    {
    //  console.log(books);
      this.bookList=books;
      this.spinner.show();
      setTimeout(() => {
      /** spinner ends after 5 seconds */
      this.spinner.hide();
      this.router.navigate(['/showbook']);
    }, 2000);
     // this.router.navigate(['/showbook']);
      
    });
  }

  
  addSellOrderRequest(bookId:number){
    this.urls=this.url+"sellBookRequest";
    this.http.post(this.urls,bookId).subscribe(
      totalRequest=>{
        this.notification=totalRequest;
        sessionStorage.setItem('notification1',this.notification);
      });
  }

  getNotification(){
    return sessionStorage.getItem('notification1');
  }

  getBookNotification(){
    this.urls=this.url+"getNotification";
    this.http.get(this.urls).subscribe(
      totalRequest=>{
        this.notification=totalRequest;
        sessionStorage.setItem('notification1',this.notification);
      });
  }

  getBookCatogory(){
   this.urls=this.url+"allCatogory"; 
   return this.http.get(this.urls);
  }

  getBuyBook(){
    this.urls=this.url+"getBuyBook";
    return this.http.get(this.urls);
  }

  plusQuantity(requestId:number){
    this.urls=this.url+"plusQuantity";
    this.http.post(this.urls,requestId).subscribe(
      data=>{
        console.log(data);
        this.router.navigateByUrl('/refresh', { skipLocationChange: true }).then(() => {
        this.router.navigateByUrl('checkout');
        });
      });
  }

  minusQuantity(requestId:number){
    this.urls=this.url+"minusQuantity";
    this.http.post(this.urls,requestId).subscribe(
      data=>{
        console.log(data);
        this.router.navigateByUrl('/refresh', { skipLocationChange: true }).then(() => {
        this.router.navigateByUrl('checkout');
        });
      });
  }


  delteBookRequest(requestId:number){
    this.urls=this.url+"deleteBookRequest";
    return this.http.post(this.urls,requestId);
  }

  bookDeliverAddressMultipleBook(addressId:number){
    this.urls=this.url+"addDeliverAddress";
    this.http.post(this.urls,addressId).subscribe(
      totalRequest=>{
        this.notification=totalRequest;
        sessionStorage.setItem('notification1',this.notification);
      });
  }

  bookDeliverAddressSingleBook(addressId:number){
    var array=new Array(2);
    array[0]=addressId;
    array[1]=this.bookId;
    this.urls=this.url+"addDeliverAddressSingleBook";
    this.http.post(this.urls,array).subscribe(
      totalRequest=>{
      });
  }

  //-----Update Book Status---------

  updateBuyBookStatus(status:any){
    this.urls=this.url+"updateBuyBookStatus";
    this.http.post(this.urls,status).subscribe(
      data=>{
        this.spinner.show();
        setTimeout(() => {
          this.spinner.hide();
        }, 1000);
        this.router.navigateByUrl('/refresh', { skipLocationChange: true }).then(() => {
          this.router.navigateByUrl('deliverBuyRequest');
        });
      });
  }

  getUserById(id:number){
    this.urls=this.url+"fetchUser";
    return this.http.post(this.urls,id);
  }

  updateUserDetail(value:UserDetail) {
    this.urls=this.url+"updateUser";
    return this.http.post(this.urls,value);
  }

  deleteUser(userId:number){
    this.urls=this.url+"deleteUser";
    return this.http.post(this.urls,userId);
  }

  //book serching for purchage topCatagory
  getBookByCategory(category:string){
    this.urls=this.url +"fetchCategory";
    return this.http.post(this.urls,category);
  }

  getBookByPublisher(publisher:string){
    this.urls=this.url+"fetchPublisher";
    return this.http.post(this.urls,publisher);
  }

  getBookById(id:number){
    this.urls=this.url+"fetch";
    return this.http.post(this.urls,id);
  }

  //--------System Date----------
  
  sellDate(userId:string){
    this.urls=this.url+"sellDate";
    return this.http.post(this.urls,userId);
  }

  buyDate(userId:string){
    this.urls=this.url+"buyDate";
    return this.http.post(this.urls,userId);
  }

  //----------------History Part-----------
  
  getSellHistory(sellUserId:string){
    this.urls=this.url+"sellHistory";
    return this.http.post(this.urls,sellUserId);
  }

  getBuyHistory(buyUserId:string){
    this.urls=this.url+"buyHistory";
    return this.http.post(this.urls,buyUserId);
  }

  // -------------- Admin part--------------------
  getDeliverySellRequestAdmin(){
    this.urls=this.url+"getSellRequestAdmin";
    return this.http.get(this.urls);
  }
  getDeliveryRequestAdmin(){
    this.urls=this.url+"getRequestAdmin";
    return this.http.get(this.urls);
  }
}

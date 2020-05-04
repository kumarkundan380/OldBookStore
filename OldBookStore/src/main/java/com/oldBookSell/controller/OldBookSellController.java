package com.oldBookSell.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oldBookSell.dto.BuyOrderRequestDTO;
import com.oldBookSell.dto.OldBookSellDTO;
import com.oldBookSell.dto.SellOrderRequestDTO;
import com.oldBookSell.model.BuyOrderRequest;
import com.oldBookSell.model.SellOrderRequest;
import com.oldBookSell.model.UserDetails;
import com.oldBookSell.service.BuyOrderRequestService;
import com.oldBookSell.service.OldBookSellServices;
import com.oldBookSell.service.SellOrderRequestService;
import com.oldBookSell.serviceImpl.OldBookSellServiceImpl;

@RestController
@RequestMapping
@CrossOrigin
public class OldBookSellController {
	
		public static final Logger LOGGER=LoggerFactory.getLogger(OldBookSellServiceImpl.class);
	
		@Autowired
		OldBookSellServices oldBookSellServices;
		
		@Autowired
		SellOrderRequestService sellOrderRequestService;
		
		@Autowired
		BuyOrderRequestService buyOrderRequestService;
		
		@GetMapping("/hello")
		public  String hello(Principal principal) {
			LOGGER.info("Controller hello method is caiing....");
			return "hello fundtion excute sucessfully";
		}
		
		@RequestMapping("/add")
		public OldBookSellDTO createUser(@RequestBody OldBookSellDTO userDetail) {
			LOGGER.info("Controller createUser method is caiing....");
			return  oldBookSellServices.createUser(userDetail);
		}
			
		@RequestMapping("/bookDetailsRequest")
		public SellOrderRequestDTO addBookDetails(@RequestBody SellOrderRequestDTO sellOrderRequestDTO) {
			LOGGER.info("Controller addBookDetails method is caiing....");
			int deliveryId=oldBookSellServices.getDeliveryPerson();
			LOGGER.info("In Controller addBookDetails method Delivery Id=" +deliveryId);
			sellOrderRequestDTO.setDileveryPersonId(deliveryId);
			return sellOrderRequestService.bookRequest(sellOrderRequestDTO);
		}
		
		@RequestMapping("/addAddress")
		public UserDetails addAddress(@RequestBody OldBookSellDTO userDetail) {
			LOGGER.info("Controller addAddress method is caiing....");
			return  oldBookSellServices.addAddress(userDetail);
		}
		
		@GetMapping("/listUser")
		public List<UserDetails> userList(){
			LOGGER.info("Controller userList method is caiing....");
			return oldBookSellServices.userList();
		}
		
		@RequestMapping("/fetchUser")
		public UserDetails getUserById(@RequestBody int id) {
			LOGGER.info("Controller getUserById method is caiing....");
			return oldBookSellServices.findById(id).get();
		}
		
		
		@RequestMapping("/updateUser")
		public UserDetails updateUser(@RequestBody UserDetails user){
			LOGGER.info("Controller updateUser method is caiing....");
			return oldBookSellServices.updateUser(user).get();
		}
		
		@RequestMapping("/deleteUser")
		public int deleteUser(@RequestBody int userId ){
			LOGGER.info("Controller deleteUser method is caiing....");
			return oldBookSellServices.deleteUser(userId);
		}
		
		@GetMapping("/getAddress")
		public UserDetails getAddress() {
			LOGGER.info("Controller userDetails method is caiing....");
			return oldBookSellServices.getAddress();
		}
		
		@GetMapping("/getRequest")
		public Iterable<Object> getDeliveryRequest() {
			LOGGER.info("Controller getDeliveryRequest method is caiing....");
			int deliveryId=oldBookSellServices.getDeliveryPersonId();
			LOGGER.info("In Controller getDeliveryRequest method Delivery Id=" +deliveryId);
			return sellOrderRequestService.deliveryRequest(deliveryId);
		}
		
		@RequestMapping("/bookStatus")
		public void updateBookStatus(@RequestBody SellOrderRequestDTO sellOrderRequestDTO) {
			LOGGER.info("Controller updateBookStatus method is caiing....");
			sellOrderRequestService.updateBookStatus(sellOrderRequestDTO);
		}

		@GetMapping("/findBooks/{min}/{max}")
		public List<SellOrderRequest> getBook(@PathVariable(value = "min")int min,@PathVariable(value = "max")int max){
			LOGGER.info("Controller getBook method is caiing....");
			LOGGER.info("In Controller getBook method Min=" +min);
			LOGGER.info("In Controller getBook method Max=" +max);
			return sellOrderRequestService.findBooks(min,max);
		}
			
		@RequestMapping("/fetch")
		public Optional<SellOrderRequest> fetchBook(@RequestBody int bookId) {
			LOGGER.info("Controller fetchBook method is caiing....");
			LOGGER.info("In Controller fetchBook method bookId=" +bookId);
			return sellOrderRequestService.findById(bookId);
		}
		
		@RequestMapping("/fetchAuthor")
		public List<SellOrderRequest> findBookByAuthor(@RequestBody String author){
			LOGGER.info("Controller findBookByAuthor method is caiing....");
			LOGGER.info("In Controller findBookByAuthor method Author Name=" +author);
			return sellOrderRequestService.findBookByAuthor(author);
		}
		
		@RequestMapping("/fetchPublisher")
		public List<SellOrderRequest> findBookByPublisher(@RequestBody String publisher){
			LOGGER.info("Controller findBookByPublisher method is caiing....");
			LOGGER.info("In Controller findBookByPublisher method Publisher Name=" +publisher);
			return sellOrderRequestService.findBookByPublisher(publisher);
		}
		
		@RequestMapping("/sellBookRequest")
		public int addBuyOrderRequest(@RequestBody int bookId) {
			
			LOGGER.info("Controller addBuyOrderRequest method is caiing....");
			
			Optional<SellOrderRequest> sellOrderRequest=sellOrderRequestService.findById(bookId);
			LOGGER.info(" In Controller sellOrderRequest="+sellOrderRequest.get().getBookName());
			BuyOrderRequestDTO buyOrderRequestDTO =new BuyOrderRequestDTO();
			
			buyOrderRequestDTO.setBookName(sellOrderRequest.get().getBookName());
			buyOrderRequestDTO.setAuthors(sellOrderRequest.get().getAuthors());
			buyOrderRequestDTO.setSmallThumbnail(sellOrderRequest.get().getSmallThumbnail());
			buyOrderRequestDTO.setAmount(sellOrderRequest.get().getAmount());
			buyOrderRequestDTO.setQuantity(sellOrderRequest.get().getQuantity());
			buyOrderRequestDTO.setCheckStatus("user");
			buyOrderRequestDTO.setBookId(sellOrderRequest.get().getSellOrderRequestId());		
			return buyOrderRequestService.saveRequest(buyOrderRequestDTO);
		}
		
		@RequestMapping("/getNotification")
		public int getNotification() {
			LOGGER.info("Controller getNotification method is caiing....");
			return buyOrderRequestService.getNotification();
		}
		
		@RequestMapping("/getBuyBook")
		public List<BuyOrderRequest> getOderRequest(){
			LOGGER.info("Controller getOderRequest method is caiing....");
			return buyOrderRequestService.getOrderRequest();
		}
		
		@RequestMapping("/deleteBookRequest")
		public int deleteBookRequest(@RequestBody int requestBookId){
			LOGGER.info("Controller deleteBookRequest method is caiing....");
			buyOrderRequestService.deleteBookRequest(requestBookId);
			return 0;
		}
		
		@RequestMapping("/addDeliverAddress")
		public int addDeliveryAddress(@RequestBody int addressId){
			LOGGER.info("Controller addDeliveryAddress method is caiing....");
			int deliveryId=oldBookSellServices.getDeliveryPerson();
			buyOrderRequestService.addDeliverAddress(addressId,deliveryId);
			return 0;
		}
		
		@RequestMapping("/addDeliverAddressSingleBook")
		public int addDeliveryAddressSingleBook(@RequestBody int array[]){
			LOGGER.info("Controller addDeliveryAddressSingleBook method is caiing....");
			int addressID=array[0];
			int bookId=array[1];
			
			Optional<SellOrderRequest> sellOrderRequest=sellOrderRequestService.findById(bookId);
			BuyOrderRequestDTO buyOrderRequestDTO =new BuyOrderRequestDTO();
			
			buyOrderRequestDTO.setBookName(sellOrderRequest.get().getBookName());
			buyOrderRequestDTO.setAuthors(sellOrderRequest.get().getAuthors());
			buyOrderRequestDTO.setSmallThumbnail(sellOrderRequest.get().getSmallThumbnail());
			buyOrderRequestDTO.setAmount(sellOrderRequest.get().getAmount());
			buyOrderRequestDTO.setQuantity(sellOrderRequest.get().getQuantity());
			buyOrderRequestDTO.setCheckStatus("pending");
			buyOrderRequestDTO.setAddressId(addressID+"");
			buyOrderRequestDTO.setBookId(sellOrderRequest.get().getSellOrderRequestId());

			int deliveryId=oldBookSellServices.getDeliveryPerson();
			buyOrderRequestDTO.setDileveryPersonId(deliveryId);

			return buyOrderRequestService.saveRequest(buyOrderRequestDTO);
		}


		@GetMapping("/getSellRequest")
		public Iterable<Object> getDeliverySellRequest() {
			LOGGER.info("Controller getDeliverySellRequest method is calling....");
			int deliveryId=oldBookSellServices.getDeliveryPersonId();
			LOGGER.info("In Controller getDeliverySellRequest method Delivery ID=" +deliveryId);
			return buyOrderRequestService.deliverySellRequest(deliveryId);
		}
		
		@RequestMapping("/sellOrderNotification")
		public int getSellOrderNotification(@RequestBody String status) {
			LOGGER.info("Controller getSellOrderNotification method is caiing....");
			return sellOrderRequestService.getSellOrderNotification(status);
		}
		
		@RequestMapping("/updateBuyBookStatus")
		public void updateBuyBookStatus(@RequestBody SellOrderRequestDTO sellOrderRequestDTO) {
			LOGGER.info("Controller updateBuyBookStatus method is calling....");
			LOGGER.info("In Controller updateBuyBookStatus method SellOrderRequestId = "+sellOrderRequestDTO.getSellOrderRequestId());
			LOGGER.info("In Controller updateBuyBookStatus method sellOrderRequestDTO.getFeedBack = "+sellOrderRequestDTO.getFeedBack());
			buyOrderRequestService.updateBuyBookStatus(sellOrderRequestDTO.getSellOrderRequestId(),sellOrderRequestDTO.getCheck_status());
		}
		
		@RequestMapping("/fetchCategory")
		public List<SellOrderRequest> findBookByCategory(@RequestBody String category){
			LOGGER.info("Controller findBookByCategory method is caiing....");
			LOGGER.info("In Controller findBookByCategory method Category=" +category);
			return sellOrderRequestService.findBookByCategory(category);
		}
			
		@GetMapping("/getRole")
		public String getRole() {
			LOGGER.info("Controller getRole method is caiing....");
			return oldBookSellServices.getRole();
		}
			
		@RequestMapping("/searchBook")
		public List<SellOrderRequest> searchBook(@RequestBody String bookName) {
			LOGGER.info("Controller searchBook method is caiing....");
			return sellOrderRequestService.findBookByNameAuthorAndIsbn(bookName);
			
		}
		
		@RequestMapping("/sellHistory")
		public List<SellOrderRequest> findSellHistory(@RequestBody String sellUserId){
			LOGGER.info("Controller findSellHistory method is caiing....");
			return sellOrderRequestService.findSellHistory(sellUserId);
		}
		
		@RequestMapping("/sellDate")
		public Iterable<Object> sellDate(@RequestBody String userId) {
			LOGGER.info("Controller sellDate method is caiing....");
			return sellOrderRequestService.sellDate(userId);
		}
		
		@RequestMapping("/buyDate")
		public Iterable<Object> buyDate(@RequestBody String userId){
			LOGGER.info("Controller buyDate method is caiing....");
			return buyOrderRequestService.buyDate(userId);
		}
		
		@GetMapping("/getSellRequestAdmin")
		public Iterable<Object> getDeliverySellRequestAdmin() {
			LOGGER.info("Controller getDeliverySellRequestAdmin method is caiing....");
			return buyOrderRequestService.deliverySellRequestAdmin();
		}
		
		@GetMapping("/getRequestAdmin")
		public Iterable<Object> getDeliveryRequestAdmin() {
			LOGGER.info("Controller getDeliveryReuestAdmin method is caiing....");
			return sellOrderRequestService.deliveryRequestAdmin();
		}
		
		@RequestMapping("/buyHistory")
		public List<BuyOrderRequest> findBuyHistory(@RequestBody String buyUserId){
			LOGGER.info("Controller findBuyHistory method is calling....");
			return buyOrderRequestService.findBuyHistory(buyUserId);
		}
}

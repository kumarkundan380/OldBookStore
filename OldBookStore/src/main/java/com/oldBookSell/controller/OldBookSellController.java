package com.oldBookSell.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oldBookSell.dto.BuyOrderRequestDTO;
import com.oldBookSell.dto.OldBookSellDTO;
import com.oldBookSell.dto.SellOrderRequestDTO;
import com.oldBookSell.exception.ResourceNotFoundException;
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
		
		@PostMapping("/addtocrt")
		public BuyOrderRequestDTO buyBookDetails(@RequestBody BuyOrderRequestDTO buyOrderRequestDTO) {
			LOGGER.info("Controller buyBookDetails method is caiing....");
			return buyOrderRequestService.saveRequest(buyOrderRequestDTO);
		}
		
		@GetMapping("/showcart")
		public List<BuyOrderRequest> findFromCart(){
			LOGGER.info("Controller findFromCart method is caiing....");
			return buyOrderRequestService.findRequest();
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
		
//		/* Get a book by id*/
//		@GetMapping("/fetch/{id}")
//		public SellOrderRequest getBookById(@PathVariable(value = "id")int id) throws ResourceNotFoundException {
//			return sellOrderRequestService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found :: " + id));
//		}
		
		@RequestMapping("/fetch")
		public Optional<SellOrderRequest> fetchBook(@RequestBody int bookId) {
			LOGGER.info("Controller fetchBook method is caiing....");
			LOGGER.info("In Controller fetchBook method bookId=" +bookId);
			return sellOrderRequestService.findById(bookId);
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
		
//		@GetMapping("/searchBook/{searchValue}")
//		public List<SellOrderRequest> searchBook(@PathVariable(value = "searchValue")String searchValue){
//			System.out.println("search book.....");
//			return sellOrderRequestService.findBookByNameAuthorAndIsbn(searchValue);
//		}
		
		@RequestMapping("/searchBook")
		public List<SellOrderRequest> searchBook(@RequestBody String bookName) {
			LOGGER.info("Controller searchBook method is caiing....");
			return sellOrderRequestService.findBookByNameAuthorAndIsbn(bookName);
			
		}
}

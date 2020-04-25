package com.oldBookSell.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@RestController
@RequestMapping
@CrossOrigin
public class OldBookSellController {
	
		@Autowired
		OldBookSellServices oldBookSellServices;
		
		@Autowired
		SellOrderRequestService sellOrderRequestService;
		
		@Autowired
		BuyOrderRequestService buyOrderRequestService;
		
		@GetMapping("/hello")
		public  String hello(Principal principal) {
			return "hello fundtion excute sucessfully";
		}
		
		@RequestMapping("/add")
		public OldBookSellDTO createUser(@RequestBody OldBookSellDTO userDetail) {
			return  oldBookSellServices.createUser(userDetail);
		}
		
		@PostMapping("/addtocrt")
		public BuyOrderRequestDTO buyBookDetails(@RequestBody BuyOrderRequestDTO buyOrderRequestDTO) {
			return buyOrderRequestService.saveRequest(buyOrderRequestDTO);
		}
		
		@GetMapping("/showcart")
		public List<BuyOrderRequest> findFromCart(){
			return buyOrderRequestService.findRequest();
		}
			
		@RequestMapping("/bookDetailsRequest")
		public SellOrderRequestDTO addBookDetails(@RequestBody SellOrderRequestDTO sellOrderRequestDTO) {
			return sellOrderRequestService.bookRequest(sellOrderRequestDTO);
		}
		
		@RequestMapping("/addAddress")
		public UserDetails addAddress(@RequestBody OldBookSellDTO userDetail) {
			return  oldBookSellServices.addAddress(userDetail);
		}
		
		@GetMapping("/getAddress")
		public UserDetails getAddress() {
			return oldBookSellServices.getAddress();
		}
		
		@GetMapping("/getBooks")
		public List<SellOrderRequest> getBooks() {
			return sellOrderRequestService.findAll();
		}
		
		@GetMapping("/findBooks/{min}/{max}")
		public List<SellOrderRequest> getBook(@PathVariable(value = "min")int min,@PathVariable(value = "max")int max){
			return sellOrderRequestService.findBooks(min,max);
		}
		
		/* Get a book by id*/
		@GetMapping("/fetch/{id}")
		public SellOrderRequest getBookById(@PathVariable(value = "id")int id) throws ResourceNotFoundException {
			return sellOrderRequestService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found :: " + id));
		}
		
		@GetMapping("/getRole")
		public String getRole() {
			return oldBookSellServices.getRole();
		}
		
		@GetMapping("/searchBook/{searchValue}")
		public List<SellOrderRequest> searchBook(@PathVariable(value = "searchValue")String searchValue){
			System.out.println("search book.....");
			return sellOrderRequestService.findBookByNameAuthorAndIsbn(searchValue);
		}
		
}

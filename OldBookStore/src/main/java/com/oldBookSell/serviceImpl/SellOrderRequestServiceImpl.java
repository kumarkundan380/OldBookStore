package com.oldBookSell.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.oldBookSell.dto.SellOrderRequestDTO;
import com.oldBookSell.model.SellOrderRequest;
import com.oldBookSell.repository.SellOrderRequestRepository;
import com.oldBookSell.service.SellOrderRequestService;

@Service
public class SellOrderRequestServiceImpl implements SellOrderRequestService {
	
	public static final Logger LOGGER=LoggerFactory.getLogger(SellOrderRequestServiceImpl.class);

	@Autowired
	SellOrderRequestRepository sellOrderRequest;
	
	@Override
	public SellOrderRequestDTO bookRequest(SellOrderRequestDTO sellOrderRequestDTO) {
		LOGGER.info("SellOrderRequestService bookRequest method is calling....");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		SellOrderRequest sellOrderRequestObj=new SellOrderRequest();
		
		sellOrderRequestObj.setBookName(sellOrderRequestDTO.getBook_name());
		sellOrderRequestObj.setAuthors(sellOrderRequestDTO.getAuthors());
		sellOrderRequestObj.setDescription(sellOrderRequestDTO.getDescription());
		sellOrderRequestObj.setPublisher(sellOrderRequestDTO.getPublisher());
		sellOrderRequestObj.setPublishedDate(sellOrderRequestDTO.getPublishedDate());
		sellOrderRequestObj.setCategories(sellOrderRequestDTO.getCategories());
		sellOrderRequestObj.setIsbnType10(sellOrderRequestDTO.getIsbn_type_10());
		sellOrderRequestObj.setIsbnNo1(sellOrderRequestDTO.getIsbnNo1());
		sellOrderRequestObj.setIsbnType13(sellOrderRequestDTO.getIsbn_type_13());
		sellOrderRequestObj.setIsbnNo2(sellOrderRequestDTO.getIsbnNo2());
		sellOrderRequestObj.setSmallThumbnail(sellOrderRequestDTO.getSmallThumbnail());
		sellOrderRequestObj.setThumbnail(sellOrderRequestDTO.getThumbnail());
		sellOrderRequestObj.setAmount(sellOrderRequestDTO.getAmount());
		sellOrderRequestObj.setCurrencyCode(sellOrderRequestDTO.getCurrencyCode());
		sellOrderRequestObj.setQuantity(sellOrderRequestDTO.getQuantity());
		sellOrderRequestObj.setCheckStatus(sellOrderRequestDTO.getCheck_status());
		sellOrderRequestObj.setUserId(authentication.getName());
		sellOrderRequestObj.setAddressId(sellOrderRequestDTO.getAddressId());
		sellOrderRequestObj.setDileveryPersonId(sellOrderRequestDTO.getDileveryPersonId());
		sellOrderRequest.save(sellOrderRequestObj);
		LOGGER.info("Sell Book Request save to sell_order_request Table ");
		return sellOrderRequestDTO;
		
	}

	@Override
	public Iterable<Object> deliveryRequest(int deliveryPerson_id) {
		LOGGER.info("SellOrderRequestService deliveryRequest method is caling....");
		Iterable<Object>result= sellOrderRequest.deliveryPersonRequest(deliveryPerson_id);
		LOGGER.info("In SellOrderRequestService Delivery Person id="+deliveryPerson_id);
		return result;
	}

	@Override
	public void updateBookStatus(SellOrderRequestDTO sellOrderRequestDTO) {
		LOGGER.info("SellOrderRequestService updateBookStatus method is calling....");
		sellOrderRequest.updateBookStatus(sellOrderRequestDTO.getCheck_status(),sellOrderRequestDTO.getFeedBack(),sellOrderRequestDTO.getSellOrderRequestId());
	}

//	@Override
//	public List<SellOrderRequest> findBooks(int min, int max) {
//		LOGGER.info("SellOrderRequestService findBooks method is calling....");
//		LOGGER.info("In SellOrderService findBooks Min="+min);
//		LOGGER.info("In SellOrderService findBooks Max="+max);
//		return sellOrderRequest.findBooks(min, max);
//	}
	
	
	
	@Override
	public List<SellOrderRequest> findSellHistory(String sellUserId){
		LOGGER.info("SellOrderRequestService findSellHistory method is calling....");
		return sellOrderRequest.findSellHistory(sellUserId);
		
	}
	
	
	
//	@Override
//	public Iterable<Object> sellDate(String userId) {
//		LOGGER.info("SellOrderRequestService sellDate method is calling....");
//		Iterable<Object> result= sellOrderRequest.sellDate(userId);
//		return result;
//	}
	
	@Override
	public int getSellOrderNotification(String status) {
		LOGGER.info("SellOrderRequestService getSellOrderNotification method is calling....");
		return sellOrderRequest.getSellOrderNotification(status);
	}
	
	@Override
	public Iterable<Object> deliveryRequestAdmin() {
		LOGGER.info("SellOrderRequestService deliveryRequestAdmin method is calling.....");
		Iterable<Object>result= sellOrderRequest.deliveryPersonRequestAdmin();
		return result;
	}
	
	@Override
	public List<SellOrderRequest> getAllBook() {
		LOGGER.info("SellOrderRequestService getAllBook method is calling.....");
		return sellOrderRequest.getAllBook();
	}
	
	@Override
	public List<SellOrderRequest> getAllBook2() {
		LOGGER.info("SellOrderRequestService getAllBook2 method is calling.....");
		return sellOrderRequest.getAllBookForUpdate();
	}

	@Override
	public List<SellOrderRequest> updateBookPrice(int bookId, int price) {
		LOGGER.info("SellOrderRequestService updateBookPrice method is calling.....");
		Optional<SellOrderRequest> obj=sellOrderRequest.findById(bookId);
		System.out.println(obj.get().getAmount());
		obj.get().setAmount(price);
		sellOrderRequest.save(obj.get());
		return null;
	}
	

	
	
	
}

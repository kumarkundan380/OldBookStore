package com.oldBookSell.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.oldBookSell.dto.BuyOrderRequestDTO;
import com.oldBookSell.model.BuyOrderRequest;
import com.oldBookSell.repository.BuyOrderRequestRepository;
import com.oldBookSell.service.BuyOrderRequestService;

@Service
public class BuyOrderRequestServiceImpl implements BuyOrderRequestService{

	public static final Logger LOGGER=LoggerFactory.getLogger(BuyOrderRequestServiceImpl.class);
	
	@Autowired
	BuyOrderRequestRepository buyOrderRequestRepository;
	
	@Override
	public int saveRequest(BuyOrderRequestDTO buyOrderRequestDto) {
		
		LOGGER.info("BuyOrderRequestService saveRequest method is calling....");
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		BuyOrderRequest buyOrderRequestObj=new BuyOrderRequest();
		
		buyOrderRequestObj.setBookName(buyOrderRequestDto.getBookName());
		buyOrderRequestObj.setAuthors(buyOrderRequestDto.getAuthors());
		buyOrderRequestObj.setSmallThumbnail(buyOrderRequestDto.getSmallThumbnail());
		buyOrderRequestObj.setAmount(buyOrderRequestDto.getAmount());
		buyOrderRequestObj.setQuantity(buyOrderRequestDto.getQuantity());
		buyOrderRequestObj.setCheckStatus(buyOrderRequestDto.getCheckStatus());
		buyOrderRequestObj.setBookId(buyOrderRequestDto.getBookId());
		buyOrderRequestObj.setUserId(authentication.getName());
		buyOrderRequestObj.setAddressId(buyOrderRequestDto.getAddressId());
		buyOrderRequestObj.setDileveryPersonId(buyOrderRequestDto.getDileveryPersonId());
		
		buyOrderRequestRepository.save(buyOrderRequestObj);
		LOGGER.info("Buy Book Request save to buy_order_request Table ");
		return buyOrderRequestRepository.countOrderRequest(authentication.getName(),"user");
	}

	@Override
	public int getNotification() {
		LOGGER.info("BuyOrderRequestService getNotification method is calling....");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return buyOrderRequestRepository.countOrderRequest(authentication.getName(),"user");
	}
	
	@Override
	public List<BuyOrderRequest> findBuyHistory(String buyUserId){
		LOGGER.info("BuyOrderRequestService findBuyHistory method is calling....");
		return buyOrderRequestRepository.findBuyHistory(buyUserId);
	}

	@Override
	public List<BuyOrderRequest> getOrderRequest() {
		LOGGER.info("BuyOrderRequestService getOrderRequest method is calling....");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return buyOrderRequestRepository.getOrderRequest(authentication.getName(),"user");
	}
	
	@Override
	public Iterable<Object> buyDate(String userId){
		LOGGER.info("BuyOrderRequestService buyDate method is calling....");
		Iterable<Object> result=buyOrderRequestRepository.buyDate(userId);
		return result;
	}

	@Override
	public void deleteBookRequest(int requestBookId) {
		LOGGER.info("BuyOrderRequestService deleteBookRequest method is calling....");
		buyOrderRequestRepository.deleteById(requestBookId);
	}

	@Override
	public void addDeliverAddress(int addressId,int deliveryPersonId) {
		LOGGER.info("BuyOrderRequestService addDeliveryAddress method is calling....");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		buyOrderRequestRepository.addDeliverAddress("pending",addressId,deliveryPersonId,authentication.getName(),"user");
	}

	@Override
	public Iterable<Object> deliverySellRequest(int deliveryId) {
		LOGGER.info("BuyOrderRequestService deliverySellRequest method is calling....");
		Iterable<Object>result= buyOrderRequestRepository.deliveryPersonRequest(deliveryId);
		return result;
	}

	@Override
	public void updateBuyBookStatus(int buyOrderRequestId, String check_status) {
		LOGGER.info("BuyOrderRequestService updateBuyBookStatus method is calling....");
		buyOrderRequestRepository.updateBuyBookStatus(buyOrderRequestId,check_status);
	}
	
	@Override
	public Iterable<Object> deliverySellRequestAdmin() {
		LOGGER.info("BuyOrderRequestService deliverySellRequestAdmin method is calling....");
		return buyOrderRequestRepository.deliveryGetAdmin();
	}

}

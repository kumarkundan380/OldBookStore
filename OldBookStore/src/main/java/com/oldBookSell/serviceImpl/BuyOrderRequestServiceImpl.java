package com.oldBookSell.serviceImpl;

import java.util.List;
import java.util.Optional;

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

	private static final Logger LOGGER=LoggerFactory.getLogger(BuyOrderRequestServiceImpl.class);
	
	@Autowired
	private BuyOrderRequestRepository buyOrderRequestRepository;
	
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
		buyOrderRequestObj.setStatus(buyOrderRequestDto.getStatus());
		buyOrderRequestObj.setTransactionId(buyOrderRequestDto.getTransactionId());
		
		BuyOrderRequest result=buyOrderRequestRepository.checkBook(authentication.getName(),buyOrderRequestDto.getBookId(),"user");
		if(result!=null) {
			addQuantity(result.getBuyOrderRequestId());
			LOGGER.info("In BuyOrderRequestService BuyOrderRequestId is: "+result.getBuyOrderRequestId());
		}else {
			LOGGER.info("Buy Book Request save to buy_order_request Table ");
			buyOrderRequestRepository.save(buyOrderRequestObj);
		}	
		return buyOrderRequestRepository.countOrderRequest(authentication.getName(),"user");		
	}

	@Override
	public int getNotification() {
		LOGGER.info("BuyOrderRequestService getNotification method is calling....");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return buyOrderRequestRepository.countOrderRequest(authentication.getName(),"user");
	}
	
	@Override
	public List<BuyOrderRequest> findBuyHistory(){
		LOGGER.info("BuyOrderRequestService findBuyHistory method is calling....");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return buyOrderRequestRepository.findBuyHistory(authentication.getName());
	}

	@Override
	public List<BuyOrderRequest> getOrderRequest() {
		LOGGER.info("BuyOrderRequestService getOrderRequest method is calling....");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return buyOrderRequestRepository.getOrderRequest(authentication.getName(),"user");
	}
	
	@Override
	public void deleteBookRequest(int requestBookId) {
		LOGGER.info("BuyOrderRequestService deleteBookRequest method is calling....");
		buyOrderRequestRepository.deleteById(requestBookId);
	}

	@Override
	public Iterable<Object> deliverySellRequest(int deliveryId) {
		LOGGER.info("BuyOrderRequestService deliverySellRequest method is calling....");
		Iterable<Object>result= buyOrderRequestRepository.deliveryPersonRequest(deliveryId);
		return result;
	}

	@Override
	public void updateBuyBookStatus(int buyOrderRequestId, String checkStatus) {
		LOGGER.info("BuyOrderRequestService updateBuyBookStatus method is calling....");
		buyOrderRequestRepository.updateBuyBookStatus(buyOrderRequestId,checkStatus);
	}
	
	@Override
	public Iterable<Object> deliverySellRequestAdmin() {
		LOGGER.info("BuyOrderRequestService deliverySellRequestAdmin method is calling....");
		return buyOrderRequestRepository.deliveryGetAdmin();
	}
	
	@Override
	public BuyOrderRequest addQuantity(int requestBookId) {
		LOGGER.info("BuyOrderRequestService addQuantity method is calling....");
		Optional<BuyOrderRequest> buyOrderRequest =buyOrderRequestRepository.findById(requestBookId);
		BuyOrderRequest buyOrderRequestObj=new BuyOrderRequest();
		buyOrderRequestObj=buyOrderRequest.get();
		buyOrderRequestObj.setQuantity(buyOrderRequestObj.getQuantity()+1);
		LOGGER.info("In BuyOrderRequestService quantity"+buyOrderRequestObj.getQuantity());
		return buyOrderRequestRepository.save(buyOrderRequestObj);
		
	}

	@Override
	public BuyOrderRequest minusQuantity(int requestBookId) {
		LOGGER.info("BuyOrderRequestService minusQuantity method is calling....");
		Optional<BuyOrderRequest> buyOrderRequest =buyOrderRequestRepository.findById(requestBookId);
		BuyOrderRequest buyOrderRequestObj=new BuyOrderRequest();
		buyOrderRequestObj=buyOrderRequest.get();
		buyOrderRequestObj.setQuantity(buyOrderRequestObj.getQuantity()-1);
		LOGGER.info("In BuyOrderRequestService quantity"+buyOrderRequestObj.getQuantity());
		return buyOrderRequestRepository.save(buyOrderRequestObj);
	}
	
	@Override
	public List<BuyOrderRequest> addDeliverAddress(int addressId, int deliveryPersonId, String status, String transactionId) {
		LOGGER.info("BuyOrderRequestService addDeliverAddress method is calling....");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		List<BuyOrderRequest> result= buyOrderRequestRepository.getBuyRequest(authentication.getName(),"user");
		buyOrderRequestRepository.addDeliverAddress("ProcessingOrder",addressId,deliveryPersonId,status,transactionId,authentication.getName(),"user");
		return result;
	}

	@Override
	public int getQuantity(int bookId) {
		LOGGER.info("BuyOrderRequestService getQuantity method is calling....");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		BuyOrderRequest result=buyOrderRequestRepository.checkBook(authentication.getName(),bookId,"user");
		if(result!=null)
			return result.getQuantity();
		else
			return 0;
	}

}

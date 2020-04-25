package com.oldBookSell.serviceImpl;

import java.util.List;

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

	@Autowired
	BuyOrderRequestRepository buyOrderRequest;
	
	@Override
	public BuyOrderRequestDTO saveRequest(BuyOrderRequestDTO buyOrderRequestDTO) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		BuyOrderRequest buyOrderRequestObj=new BuyOrderRequest();
		
		buyOrderRequestObj.setBookName(buyOrderRequestDTO.getBook_name());
		buyOrderRequestObj.setAuthors(buyOrderRequestDTO.getAuthors());
		buyOrderRequestObj.setDescription(buyOrderRequestDTO.getDescription());
		buyOrderRequestObj.setPublisher(buyOrderRequestDTO.getPublisher());
		buyOrderRequestObj.setPublishedDate(buyOrderRequestDTO.getPublishedDate());
		buyOrderRequestObj.setCategories(buyOrderRequestDTO.getCategories());
		buyOrderRequestObj.setIsbnType10(buyOrderRequestDTO.getIsbn_type_10());
		buyOrderRequestObj.setIsbnNo1(buyOrderRequestDTO.getIsbnNo1());
		buyOrderRequestObj.setIsbnType13(buyOrderRequestDTO.getIsbn_type_13());
		buyOrderRequestObj.setIsbnNo2(buyOrderRequestDTO.getIsbnNo2());
		buyOrderRequestObj.setSmallThumbnail(buyOrderRequestDTO.getSmallThumbnail());
		buyOrderRequestObj.setThumbnail(buyOrderRequestDTO.getThumbnail());
		buyOrderRequestObj.setAmount(buyOrderRequestDTO.getAmount());
		buyOrderRequestObj.setCurrencyCode(buyOrderRequestDTO.getCurrencyCode());
		buyOrderRequestObj.setQuantity(buyOrderRequestDTO.getQuantity());
		buyOrderRequestObj.setCheckStatus(buyOrderRequestDTO.getCheck_status());
		buyOrderRequestObj.setUserId(authentication.getName());
		buyOrderRequestObj.setAddressId(buyOrderRequestDTO.getAddressId());
		buyOrderRequestObj.setDileveryPersonId(buyOrderRequestDTO.getDileveryPersonId());
		
		buyOrderRequest.save(buyOrderRequestObj);
		return buyOrderRequestDTO;

	}

	@Override
	public List<BuyOrderRequest> findRequest() {
		
		return buyOrderRequest.findAll();
	}

}

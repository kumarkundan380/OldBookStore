package com.oldBookSell.serviceImpl;

import java.util.List;
import java.util.Optional;

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
	
	@Autowired
	SellOrderRequestRepository sellOrderRequest;
	
	@Override
	public SellOrderRequestDTO bookRequest(SellOrderRequestDTO sellOrderRequestDTO) {
	
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
		return sellOrderRequestDTO;
	}

	@Override
	public List<SellOrderRequest> findAll() {
		
		return sellOrderRequest.findAll();
	}


	@Override
	public Optional<SellOrderRequest> findById(int id) {
		return sellOrderRequest.findById(id);
	}
	
	@Override
	public List<SellOrderRequest> findBooks(int min,int max){
		return sellOrderRequest.findBooks(min, max);
	}

	@Override
	public List<SellOrderRequest> findBookByNameAuthorAndIsbn(String searchType) {
		return sellOrderRequest.findBookByNameAuthorAndIsbn(searchType);
	}
	
}

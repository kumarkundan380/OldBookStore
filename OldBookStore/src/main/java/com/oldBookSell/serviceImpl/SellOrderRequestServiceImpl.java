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
		//this code for usefull for book tabel find the the unique book and update quatity
//		SellOrderRequest abc=sellOrderRequest.findByBookNameAndAuthor(sellOrderRequestDTO.getBook_name(),sellOrderRequestDTO.getAuthors());
//		
//		try {	
//			
//			if(abc == null) {
//				sellOrderRequest.save(sellOrderRequestObj);
//			}else {
//				sellOrderRequestObj.setQuantity(abc.getQuantity()+1);
//				sellOrderRequestObj.setSellOrderRequestId(abc.getSellOrderRequestId());
//				sellOrderRequest.save(sellOrderRequestObj);
//			}
//			
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
		
		// delivery person logic
//		List list = sellOrderRequest.findAllByRole()
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

	@Override
	public List<SellOrderRequest> findBooks(int min, int max) {
		LOGGER.info("SellOrderRequestService findBooks method is calling....");
		LOGGER.info("In SellOrderService findBooks Min="+min);
		LOGGER.info("In SellOrderService findBooks Max="+max);
		return sellOrderRequest.findBooks(min, max);
	}

	@Override
	public List<SellOrderRequest> findBookByNameAuthorAndIsbn(String bookName) {
		LOGGER.info("SellOrderRequestService findBooksByNameAuthorAndIsbn method is calling....");
		return sellOrderRequest.findBookByNameAuthorAndIsbn(bookName);
	}
	
	@Override
	public List<SellOrderRequest> findBookByCategory(String category){
		LOGGER.info("SellOrderRequestService findBookByCategory method is calling....");
		return sellOrderRequest.findBookByCategory(category);
	}

	@Override
	public Optional<SellOrderRequest> findById(int bookId) {
		LOGGER.info("SellOrderRequestService findById method is calling.....");
		return sellOrderRequest.findById(bookId);
	}
	
}

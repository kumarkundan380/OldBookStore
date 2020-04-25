package com.oldBookSell.service;

import java.util.List;

import com.oldBookSell.dto.BuyOrderRequestDTO;
import com.oldBookSell.model.BuyOrderRequest;

public interface BuyOrderRequestService {
	
	public BuyOrderRequestDTO saveRequest(BuyOrderRequestDTO bookDetail);
	
	public List<BuyOrderRequest> findRequest();

}

package com.oldBookSell.service;

import java.util.List;
import java.util.Optional;

import com.oldBookSell.dto.SellOrderRequestDTO;
import com.oldBookSell.model.SellOrderRequest;

public interface SellOrderRequestService {
	
	public SellOrderRequestDTO bookRequest(SellOrderRequestDTO sellOrderRequestDTO);
	
	public List<SellOrderRequest> findAll();
	
	public Optional<SellOrderRequest> findById(int id);
}

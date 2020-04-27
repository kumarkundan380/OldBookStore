package com.oldBookSell.service;

import java.util.List;
import java.util.Optional;

import com.oldBookSell.dto.SellOrderRequestDTO;
import com.oldBookSell.model.SellOrderRequest;

public interface SellOrderRequestService {
	
	public SellOrderRequestDTO bookRequest(SellOrderRequestDTO sellOrderRequestDTO);
	
	public Optional<SellOrderRequest> findById(int id);

	public List<SellOrderRequest> findBooks(int min, int max);
	
	public List<SellOrderRequest> findBookByCategory(String category);
	
	public List<SellOrderRequest> findBookByNameAuthorAndIsbn(String searchType);
	
	public Iterable<Object> deliveryRequest(int deliveryPerson_id);
	
	public void updateBookStatus(SellOrderRequestDTO sellOrderRequestDTO);

}

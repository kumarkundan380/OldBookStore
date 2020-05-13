package com.oldBookSell.service;

import java.util.List;
import com.oldBookSell.dto.SellOrderRequestDTO;
import com.oldBookSell.model.SellOrderRequest;

public interface SellOrderRequestService {
	
	public SellOrderRequestDTO bookRequest(SellOrderRequestDTO sellOrderRequestDTO);

//	public List<SellOrderRequest> findBooks(int min, int max);
	
	public List<SellOrderRequest> findSellHistory(String sellUserId);
	
//	public Iterable<Object> sellDate(String userId);
	
	public int getSellOrderNotification(String status);
	
	public Iterable<Object> deliveryRequest(int deliveryPerson_id);
	
	public void updateBookStatus(SellOrderRequestDTO sellOrderRequestDTO);

	public Iterable<Object> deliveryRequestAdmin();
	
	public List<SellOrderRequest> getAllBook();

	public List<SellOrderRequest> updateBookPrice(int i, int j);

	public List<SellOrderRequest> getAllBook2();
	
}

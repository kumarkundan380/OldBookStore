package com.oldBookSell.service;

import java.util.List;
import java.util.Optional;

import com.oldBookSell.dto.OldBookSellDTO;
import com.oldBookSell.model.UserDetails;

public interface OldBookSellServices {
	
	public OldBookSellDTO createUser(OldBookSellDTO userDetail);
	
	public UserDetails addAddress(OldBookSellDTO address);
	
	public UserDetails getAddress();
	
	public List<UserDetails> userList();
	
	public Optional<UserDetails> updateUser(UserDetails userDetails);
	
	public Optional<UserDetails> findById(int id);
	
	public int deleteUser(int id);
	
	public String getRole();
	
	public int getDeliveryPerson();
	
	public int getDeliveryPersonId();
	
	public void changePassword(String userName);
	
	public String sendMail(String email, String msg);

}

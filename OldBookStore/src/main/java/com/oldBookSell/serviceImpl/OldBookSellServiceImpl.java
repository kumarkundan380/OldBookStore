package com.oldBookSell.serviceImpl;

import java.util.ArrayList;	
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.oldBookSell.dto.OldBookSellDTO;
import com.oldBookSell.model.Address;
import com.oldBookSell.model.UserDetails;
import com.oldBookSell.repository.UserDetailRepository;
import com.oldBookSell.service.OldBookSellServices;


@Service
public class OldBookSellServiceImpl implements OldBookSellServices{

	public static final Logger LOGGER=LoggerFactory.getLogger(OldBookSellServiceImpl.class);
	
	@Autowired
	UserDetailRepository userDetailRepository;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Override
	public OldBookSellDTO createUser(OldBookSellDTO odlBookSellDTO) {
		
		LOGGER.info("OldBookSellService createUser method is calling.....");
		
		UserDetails userDetails=new UserDetails();
		
		userDetails.setFirstName(odlBookSellDTO.getFirstName());
		userDetails.setLastName(odlBookSellDTO.getLastName());
		userDetails.setMobileNumber(odlBookSellDTO.getMobileNumber());
		userDetails.setEmail(odlBookSellDTO.getEmail());
		userDetails.setPassword(bcryptEncoder.encode(odlBookSellDTO.getPassword()));
		userDetails.setRole(odlBookSellDTO.getRole());
		
		Address addressObj=new Address();
		
		addressObj.setAddress(odlBookSellDTO.getAddress());
		addressObj.setAddress2(odlBookSellDTO.getAddress2());
		addressObj.setDistrict(odlBookSellDTO.getDistrict());
		addressObj.setLocation(odlBookSellDTO.getLocation());
		addressObj.setPostalCode(odlBookSellDTO.getPinCode());
		addressObj.setState(odlBookSellDTO.getState());
		
		List<Address> list=new ArrayList<>();
		list.add(addressObj);
		
		if(userDetailRepository.existsByEmail(odlBookSellDTO.getEmail())) {
			UserDetails userObj=	userDetailRepository.findByEmail(odlBookSellDTO.getEmail());
			list.addAll(userObj.getAddress());
			userObj.setAddress(list);
			userDetailRepository.save(userObj);
		}else {
			userDetails.setAddress(list);
			userDetailRepository.save(userDetails);
		}
		
		LOGGER.info("User information is saved in user_details table");
		
		return odlBookSellDTO;
	}

	@Override
	public UserDetails addAddress(OldBookSellDTO address) {
		
		LOGGER.info("OldBookSellService addAddress method is calling.....");
		Address addressObj=new Address();
		
		addressObj.setAddress(address.getAddress());
		addressObj.setAddress2(address.getAddress2());
		addressObj.setDistrict(address.getDistrict());
		addressObj.setLocation(address.getLocation());
		addressObj.setPostalCode(address.getPinCode());
		addressObj.setState(address.getState());
		
		List<Address> list=new ArrayList<>();
		
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		UserDetails userObj=	userDetailRepository.findByEmail(authentication.getName());

		
		list.addAll(userObj.getAddress());
		list.add(addressObj);
		userObj.setAddress(list);
		
		userDetailRepository.save(userObj);
		LOGGER.info("Address is addedd in address table");
		return userDetailRepository.findByEmail(authentication.getName());
	}
	
	@Override
	public List<UserDetails> userList(){
		LOGGER.info("OldBookSellService userList method is calling...");
		return userDetailRepository.findAll();
		
	}
	
	@Override
	public Optional<UserDetails> findById(int id) {
		
		LOGGER.info("OldBookSellService findById method is calling...");
		return userDetailRepository.findById(id);
	}

	@Override
	public Optional<UserDetails> updateUser(UserDetails userDetails){
		
		LOGGER.info("OldBookSellService updateUser method is calling...");
		
		UserDetails user=userDetailRepository.findById(userDetails.getUserId()).get();
		user.setFirstName(userDetails.getFirstName());
		user.setLastName(userDetails.getLastName());
		user.setEmail(userDetails.getEmail());
		user.setMobileNumber(userDetails.getMobileNumber());
		user.setRole(userDetails.getRole());
		userDetailRepository.save(user);
		return Optional.of(user);
	}
	
	@Override
	public int deleteUser(int id){
		LOGGER.info("OldBookSellService deleteUser method is calling...");
		
		//UserDetails user=userDetailRepository.findById(id).get();
		userDetailRepository.deleteById(id);
//		if(user==null) {
//			return ResponseEntity.notFound().build();
//		}
//		userDetailRepository.delete(user);
		return 0;
	}
	
	@Override
	public UserDetails getAddress() {
		
		LOGGER.info("OldBookSellService getAddress method is calling.....");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return userDetailRepository.findByEmail(authentication.getName());
	}

	@Override
	public String getRole() {
		
		LOGGER.info("OldBookSellService getRole method is calling.....");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();		
		return userDetailRepository.hasRole(authentication.getName());
	}

	@Override
	public int getDeliveryPerson() {
		LOGGER.info("OldBookSellService getDelivery method is calling.....");
		List<Integer> list= userDetailRepository.findAllByRole("deliveryPerson");
		int i=(int) (Math.random()*list.size());
		LOGGER.info("In OldBookSellService getDeliveryPersion List="+list);
		LOGGER.info("In OldBookSellService getDeliveryPersion i="+i);
		return list.get(i);
	}

	@Override
	public int getDeliveryPersonId() {
		LOGGER.info("getDeliveryPersonId method is calling.....");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return userDetailRepository.getDevileryPersonId(authentication.getName());
	}
	

}

package com.security.jwt.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.jwt.model.UserDetails;
import com.security.jwt.repository.UserDetailRepository;
import com.security.jwt.service.UserDetailServices;

@Service
public class UserDetailServicesImpl implements UserDetailServices {
	
	@Autowired
	UserDetailRepository userDetailRepository;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails createUser(UserDetails userDetail) {
		UserDetails userDetails=new UserDetails();
		userDetails.setFirstName(userDetail.getFirstName());
		userDetails.setLastName(userDetail.getLastName());
		userDetails.setEmail(userDetail.getEmail());
		userDetails.setMobileNo(userDetail.getMobileNo());
		userDetails.setPassword(bcryptEncoder.encode(userDetail.getPassword()));
		userDetails.setGender(userDetail.getGender());
		userDetails.setAddress(userDetail.getAddress());;
		userDetails.setAddress2(userDetail.getAddress2());
		userDetails.setDistrict(userDetail.getDistrict());
		userDetails.setPostalCode(userDetail.getPostalCode());
		userDetails.setLocation(userDetail.getLocation());
		userDetails.setState(userDetail.getState());
		return userDetailRepository.save(userDetails);
	}

}

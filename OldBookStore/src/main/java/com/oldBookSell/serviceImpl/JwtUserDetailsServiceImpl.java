package com.oldBookSell.serviceImpl;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.oldBookSell.repository.UserDetailRepository;
import com.oldBookSell.service.JwtUserDetailsService;

@Service
public class JwtUserDetailsServiceImpl implements JwtUserDetailsService {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(JwtUserDetailsServiceImpl.class);
	
	@Autowired
	private UserDetailRepository userDetailRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		  LOGGER.info("JwtUserDetailsService loadUserByUsername method is calling...");	
		  com.oldBookSell.model.UserDetails userDetail = userDetailRepository.findByEmail(username); 
		 
		  if (!userDetail.getEmail().equals(username)) { 
			  throw new UsernameNotFoundException("User not found with username: " + username); 
		  }
		
		  return new
		  org.springframework.security.core.userdetails.User(userDetail.getEmail(),
		  userDetail.getPassword(), new ArrayList<>());
	}

}

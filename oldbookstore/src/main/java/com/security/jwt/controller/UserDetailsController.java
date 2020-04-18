package com.security.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.jwt.model.UserDetails;
import com.security.jwt.service.UserDetailServices;

@RestController
@RequestMapping
@CrossOrigin
public class UserDetailsController {

	@Autowired
	private UserDetailServices userDetailsService;
	
	@GetMapping("/hello")
	public  String hello() {
		return "hello fundtion excute sucessfully";
	}
	
	@RequestMapping("/register")
	public UserDetails createUser(@RequestBody UserDetails userDetails) {
		return  userDetailsService.createUser(userDetails);
	}
}

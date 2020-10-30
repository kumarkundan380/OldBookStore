package com.oldBookSell.serviceImpl;

import java.util.ArrayList;	
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.oldBookSell.dto.OldBookSellDTO;
import com.oldBookSell.model.Address;
import com.oldBookSell.model.UserDetails;
import com.oldBookSell.repository.UserDetailRepository;
import com.oldBookSell.service.OldBookSellServices;

/**
 * This is OldBookSellServiceImpl implements an application that
 * simply calls OldBookSellService interface methods
 * @author Kundan
 * @version 1.0
 * @since 2020-05-18
*/

@Service
public class OldBookSellServiceImpl implements OldBookSellServices{

	private static final Logger LOGGER=LoggerFactory.getLogger(OldBookSellServiceImpl.class);
	
	@Autowired
	private UserDetailRepository userDetailRepository;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Autowired
    private JavaMailSender sender;
	
	/**
	   * This method is used to register user. 
	   * @param oldbookSellDTO This is the parameter to createUser method
	   * @return OldBookSellDTO This returns user details.
	 */

	@Override
	public OldBookSellDTO createUser(OldBookSellDTO odlBookSellDTO) {
		
		LOGGER.info("OldBookSellService createUser method is calling.....");
		
		UserDetails userDetails=new UserDetails();
		
		userDetails.setFirstName(odlBookSellDTO.getFirstName().trim());
		userDetails.setLastName(odlBookSellDTO.getLastName().trim());
		userDetails.setMobileNumber(odlBookSellDTO.getMobileNumber().trim());
		userDetails.setEmail(odlBookSellDTO.getEmail().trim());
		userDetails.setPassword(bcryptEncoder.encode(odlBookSellDTO.getPassword()));
		userDetails.setRole(odlBookSellDTO.getRole().trim());
		
		Address addressObj=new Address();
		
		addressObj.setAddress(odlBookSellDTO.getAddress().trim());
		addressObj.setAddress2(odlBookSellDTO.getAddress2().trim());
		addressObj.setDistrict(odlBookSellDTO.getDistrict().trim());
		addressObj.setLocation(odlBookSellDTO.getLocation().trim());
		addressObj.setPostalCode(odlBookSellDTO.getPinCode().trim());
		addressObj.setState(odlBookSellDTO.getState().trim());
		
		List<Address> list=new ArrayList<>();
		list.add(addressObj);
		
		if(userDetailRepository.existsByEmail(odlBookSellDTO.getEmail().trim())) {
			UserDetails userObj = userDetailRepository.findByEmail(odlBookSellDTO.getEmail().trim());
			list.addAll(userObj.getAddress());
			userObj.setAddress(list);
			userDetailRepository.save(userObj);
		}else {
			userDetails.setAddress(list);
			userDetailRepository.save(userDetails);
		}
		String msg="Greetings :) Welcome in OldBookHouse :)" + " your UserName is your emial ";
		String result=sendMail(odlBookSellDTO.getEmail(),msg);
		LOGGER.info("In OldBOOKSelService result: "+result);
		
		return odlBookSellDTO;
	}
	
	/**
	   * This method is used to add existing user address. 
	   * @param address This is the parameter of addAddress method
	   * @return UserDetails This returns user details.
	*/

	@Override
	public UserDetails addAddress(OldBookSellDTO address) {
		
		LOGGER.info("OldBookSellService addAddress method is calling.....");
		Address addressObj=new Address();
		
		addressObj.setAddress(address.getAddress().trim());
		addressObj.setAddress2(address.getAddress2().trim());
		addressObj.setDistrict(address.getDistrict().trim());
		addressObj.setLocation(address.getLocation().trim());
		addressObj.setPostalCode(address.getPinCode().trim());
		addressObj.setState(address.getState().trim());
		
		List<Address> list=new ArrayList<>();
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		UserDetails userObj=	userDetailRepository.findByEmail(authentication.getName().trim());

		list.addAll(userObj.getAddress());
		list.add(addressObj);
		userObj.setAddress(list);
		
		userDetailRepository.save(userObj);
		LOGGER.info("Address is addedd in address table");
		return userDetailRepository.findByEmail(authentication.getName());
	}
	
	/**
	 * This method is used to get existing user address. 
	 * @return UserDetails This returns user details.
	 */
	
	@Override
	public UserDetails getAddress() {
		
		LOGGER.info("OldBookSellService getAddress method is calling.....");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return userDetailRepository.findByEmail(authentication.getName());
	}
	
	/**
	 *  This method is used to get List of existing user.
	 * @return Iterable<UserDetails> this returns user details
	 */
	
	@Override
	public Iterable<UserDetails> userList(){
		LOGGER.info("OldBookSellService userList method is calling...");
		return userDetailRepository.findAll();
		
	}
	
	/**
	 * This method is used to get a existing user details.
	 * @param id This is the parameter of getUserById method
	 * @return UserDetails This returns user details
	 */
	
	@Override
	public Optional<UserDetails> findById(int id) {
		LOGGER.info("OldBookSellService findById method is calling...");
		return userDetailRepository.findById(id);
	}
	
	/**
	 * This method is used to update existing user
	 * @param userDetails This is the parameter of updateUser method
	 * @return Optional<UserDetails> This returns user details
	 */

	@Override
	public Optional<UserDetails> updateUser(UserDetails userDetails){
		LOGGER.info("OldBookSellService updateUser method is calling...");
		
		UserDetails user=userDetailRepository.findById(userDetails.getUserId()).get();
		user.setFirstName(userDetails.getFirstName().trim());
		user.setLastName(userDetails.getLastName().trim());
		user.setEmail(userDetails.getEmail().trim());
		user.setMobileNumber(userDetails.getMobileNumber().trim());
		user.setRole(userDetails.getRole().trim());
		userDetailRepository.save(user);
		return Optional.of(user);
	}
	
	/**
	 * This method is used to delete existing user
	 * @param userId This is the parameter of deleteUser method
	 * @return int This returns 0
	 */
	
	@Override
	public int deleteUser(int id){
		LOGGER.info("OldBookSellService deleteUser method is calling...");
		userDetailRepository.deleteById(id);
		return 0;
	}
	
	/**
	 * This method is used to get the role of existing user
	 * @return String this returns user role
	 */

	@Override
	public String getRole() {
		LOGGER.info("OldBookSellService getRole method is calling.....");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();		
		return userDetailRepository.hasRole(authentication.getName());
	}

	/**
	 * This method is used to get deliver or pickup book request for a particular delivery person
	 * @return int this returns delivery person id
	 */
	
	@Override
	public int getDeliveryPerson() {
		LOGGER.info("OldBookSellService getDelivery method is calling.....");
		List<Integer> list= userDetailRepository.findAllByRole("deliveryPerson");
		int deliveryPersonId=(int) (Math.random()*list.size());
		LOGGER.info("In OldBookSellService getDeliveryPersion List="+list);
		LOGGER.info("In OldBookSellService getDeliveryPersion i="+deliveryPersonId);
		return list.get(deliveryPersonId);
	}

	/**
	 * This method is used to get delivery person Id
	 * @return int this returns delivery person id
	 */
	
	@Override
	public int getDeliveryPersonId() {
		LOGGER.info("OldBookSellService getDeliveryPersonId method is calling.....");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return userDetailRepository.getDevileryPersonId(authentication.getName());
	}
	
	/**
	 * This method is used to send mail during registration
	 * @param email this is the first parameter of sendMail method
	 * @param msg this is the second parameter of sendMail method
	 * @return String this return a message mail successfully send or not
	 */
	
	@Override
	public String sendMail(String email,String msg) {
		LOGGER.info("OldBookSellService sendMail method is calling.....");
		MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo(email);
            helper.setText(msg);
            helper.setSubject("OldBookHouse");
        } catch (MessagingException exception) {
            exception.printStackTrace();
            return "Error while sending mail ..";
        }
        sender.send(message);
        return "Mail Sent Success!";
		
	}
	
	/**
	   * This method is used to forget password. 
	   * @param userName This is the parameter of changePassword method
	 */
	
	@Override
	public void changePassword(String userName) {
		LOGGER.info("OldBookSellService changePassword method is calling.....");
		Random rand=new Random();
		String password=rand.nextInt(99999)+"";
		boolean result=userDetailRepository.existsByEmail(userName);
		if(result) {
			LOGGER.info(" In OldBookSellService sendMail method password is "+password);
			String msg="Greetings :) your password changed sucessfully 😄 "
					+ " your Password is "+password;
			sendMail(userName, msg);
			
			UserDetails userObj=	userDetailRepository.findByEmail(userName);
			userObj.setPassword(bcryptEncoder.encode(password));
			userDetailRepository.save(userObj);
		}
	}

}

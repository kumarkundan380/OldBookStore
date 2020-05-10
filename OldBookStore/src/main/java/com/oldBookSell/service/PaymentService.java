package com.oldBookSell.service;
import com.oldBookSell.model.Payment;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;

public interface PaymentService {
	
	
	public Payment savePayment(Payment payment);
	
	public Charge chargeCreditCard(String token, double amount) throws InvalidRequestException, AuthenticationException, APIConnectionException, CardException, APIException;
}
	
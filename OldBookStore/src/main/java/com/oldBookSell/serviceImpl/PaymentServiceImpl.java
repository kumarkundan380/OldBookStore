package com.oldBookSell.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oldBookSell.model.Payment;
import com.oldBookSell.repository.PaymentRepository;
import com.oldBookSell.service.PaymentService;
import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	public static final Logger LOGGER=LoggerFactory.getLogger(PaymentServiceImpl.class);
	
	
	@Autowired
    PaymentServiceImpl() {
        Stripe.apiKey = "sk_test_OR5dYaeZb1Lp7jVUXZfCDyPF00z2hG0s7j";
    }
	
	@Autowired
	PaymentRepository paymentRepository;
	
	@Override
	public Charge chargeCreditCard(String token, double amount) throws InvalidRequestException, AuthenticationException, APIConnectionException, CardException, APIException {
		LOGGER.info("PaymentServiceImpl createUser method is calling.....");
		Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", (int)(amount * 100));
        chargeParams.put("currency", "inr");
        chargeParams.put("source", token);
        Charge charge = Charge.create(chargeParams);
        return charge;
    }
	
	@Override
	public  Payment savePayment(Payment payment) {
		LOGGER.info("PaymentServiceImpl savePayment method is calling.....");
		return paymentRepository.save(payment);
	}

}

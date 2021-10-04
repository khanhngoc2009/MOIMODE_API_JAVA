package com.it15306.services;

import java.util.List;

import com.it15306.entities.Payment;
//import com.it15306.entities.Category;
import com.it15306.entities.User;

public interface PaymentService {
	List<Payment> getAllPayment();
	Payment getById(String payment_id);
	Payment savePaymnet(Payment payment);
	void delete(String payment_id);
}

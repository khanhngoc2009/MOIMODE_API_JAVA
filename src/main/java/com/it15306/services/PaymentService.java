package com.it15306.services;

import java.util.List;

import com.it15306.entities.Payment;
//import com.it15306.entities.Category;
import com.it15306.entities.User;

public interface PaymentService {
	List<Payment> getAllPayment();
	Payment getById(String payment_id);
	Payment getByName(String name);
	Payment getByType(Integer type);
	Payment getByStatus(Integer status);
	Payment savePaymnet(Payment payment);
	void delete(Integer payment_id);
}

package com.it15306.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.it15306.entities.Payment;
import com.it15306.repository.PaymentRepository;
import com.it15306.services.PaymentService;

public class PaymentServiceImpl implements PaymentService{
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Override
	public List<Payment> getAllPayment() {
		// TODO Auto-generated method stub
		return paymentRepository.findAll();
	}

	@Override
	public Payment getById(String payment_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Payment savePaymnet(Payment payment) {
		// TODO Auto-generated method stub
		return paymentRepository.save(payment);
	}

	@Override
	public void delete(Integer payment_id) {
		// TODO Auto-generated method stub
		paymentRepository.deleteById(payment_id);
	}

	@Override
	public Payment getByName(String name) {
		// TODO Auto-generated method stub
		return paymentRepository.findByName(name);
	}

	@Override
	public Payment getByType(Integer type) {
		// TODO Auto-generated method stub
		return paymentRepository.findByType(type);
	}

	@Override
	public Payment getByStatus(Integer status) {
		// TODO Auto-generated method stub
		return paymentRepository.findByStatus(status);
	}

}

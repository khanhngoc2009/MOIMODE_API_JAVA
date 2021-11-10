package com.it15306.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.it15306.dto.payment.PaymentDTO;
import com.it15306.dto.payment.RequestPaymentPage;
import com.it15306.entities.Payment;
@Service
public interface PaymentService {
	List<PaymentDTO> getAllPayment(RequestPaymentPage data);
	PaymentDTO getById(String payment_id);
	PaymentDTO getByName(String name);
	PaymentDTO getByType(Integer type);
	PaymentDTO getByStatus(Integer status);
	PaymentDTO savePaymnet(PaymentDTO payment);
	Integer delete(Integer payment_id);
	PaymentDTO update(PaymentDTO data);
}

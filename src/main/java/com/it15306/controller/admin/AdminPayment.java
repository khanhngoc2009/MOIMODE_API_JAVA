package com.it15306.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.it15306.dto.payment.PaymentDTO;
import com.it15306.dto.payment.RequestPaymentPage;
import com.it15306.services.PaymentService;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200","http://35.198.241.56" })
@RestController
@RequestMapping("/miemode_api/v1")
public class AdminPayment {
	@Autowired
	PaymentService paymentService;

	@RequestMapping(value = "/admin/payment/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<PaymentDTO>> listPayment(@RequestBody RequestPaymentPage data) {
		try {
			List<PaymentDTO> list=paymentService.getAllPayment(data);
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	@RequestMapping(value = "/admin/payment/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<PaymentDTO> create(@RequestBody PaymentDTO data) {
		
		try {
			PaymentDTO dto=paymentService.savePaymnet(data);
			if(dto != null) {
				return ResponseEntity.ok(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.badRequest().build();
	}
	@RequestMapping(value = "/admin/payment/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Integer> create(@PathVariable("id") Integer id) {
		
		try {
			Integer dto=paymentService.delete(id);
			if(dto != null) {
				return ResponseEntity.ok(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.badRequest().build();
	}
	@RequestMapping(value = "/admin/payment/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<PaymentDTO> update(@RequestBody PaymentDTO data) {
		
		try {
			PaymentDTO dto=paymentService.update(data);
			if(dto != null) {
				return ResponseEntity.ok(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.badRequest().build();
	}
}

package com.it15306.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.it15306.dto.Voucherdto;
import com.it15306.services.VoucherService;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
@RequestMapping("/miemode_api/v1")
public class CustomerVoucher {
	
	@Autowired
	VoucherService voucherService;
	
	@GetMapping("/listVoucher")
	@ResponseBody
	public ResponseEntity< List<Voucherdto>> getAllVouchers() {
		List<Voucherdto> list= voucherService.getAllVouchers();
		if(list.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
				
		return ResponseEntity.ok(list);
	}
	
	
	@GetMapping("/voucher/detail/{id}")
	@ResponseBody
	public ResponseEntity< Voucherdto> getByIdVoucher(@PathVariable("id") Integer id) {
		Voucherdto voucher= voucherService.getByIdVoucher(id);
		if(id.equals(null) || voucher == null) {
			return ResponseEntity.noContent().build();
		}				
		return ResponseEntity.ok(voucher);
	}

}

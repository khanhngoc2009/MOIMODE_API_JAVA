package com.it15306.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.it15306.dto.Voucherdto;
import com.it15306.services.VoucherService;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
@RequestMapping("/miemode_api/v1")
public class AdminVoucher {
	@Autowired
	VoucherService voucherService;
	
	@PostMapping("/admim/createvoucher")
	@ResponseBody
	public ResponseEntity<Voucherdto>  create(@RequestBody Voucherdto data) {
		
		Voucherdto vo = 	voucherService.create(data);
		if(vo == null || data.equals(null)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(vo);
	}
	
	@PutMapping("/admim/updatevoucher")
	@ResponseBody
	public ResponseEntity<Voucherdto> update(@RequestBody Voucherdto data) {
		
		Voucherdto vo = 	voucherService.update(data);
		if(vo == null || data.equals(null)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(vo);
		
	}
	@DeleteMapping("/admim/deletevoucher/{id}")
	@ResponseBody
	public ResponseEntity<Integer> update(@PathVariable("id") Integer id) {
		
		Integer id_voucher = 	voucherService.delete(id);
		if(id_voucher == null || id.equals(null)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(id_voucher);
	}
}

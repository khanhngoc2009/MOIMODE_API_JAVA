package com.it15306.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.it15306.config.DataResponseList;
import com.it15306.dto.PageDto;
import com.it15306.dto.Voucherdto;
import com.it15306.services.VoucherService;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
@RequestMapping("/miemode_api/v1")
public class CustomerVoucher {
	
	@Autowired
	VoucherService voucherService;
	
	@PostMapping("/listVoucher")
	@ResponseBody
	public ResponseEntity< DataResponseList<Voucherdto>> getAllVouchers(@RequestBody PageDto data) {
		DataResponseList<Voucherdto> rp=new DataResponseList<Voucherdto>();
		List<Voucherdto> list= voucherService.getAllVouchers(data);
		if(list.isEmpty()) {
			return ResponseEntity.noContent().build();
		}else {
			rp.setMessage("Success");
		rp.setListData(list);
		rp.setCount(voucherService.count());
		}	
		return ResponseEntity.ok(rp);
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

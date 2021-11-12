package com.it15306.controller.admin;

import java.util.List;

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

import com.it15306.config.DataResponseList;
import com.it15306.dto.PageDto;
import com.it15306.dto.voucher.RequetVoucher;
import com.it15306.dto.voucher.ResponBodyVoucher;
import com.it15306.dto.voucher.Voucherdto;
import com.it15306.services.VoucherService;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200","http://35.198.241.56" })
@RestController
@RequestMapping("/miemode_api/v1")
public class AdminVoucher {
	@Autowired
	VoucherService voucherService;
	
	@PostMapping("/admin/voucher/create")
	@ResponseBody
	public ResponseEntity<Voucherdto>  create(@RequestBody RequetVoucher data) {
		
		Voucherdto vo = 	voucherService.create(data);
		if(vo == null || data.equals(null)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(vo);
	}
	
	@PutMapping("/admin/voucher/update")
	@ResponseBody
	public ResponseEntity<Voucherdto> update(@RequestBody Voucherdto data) {
		
		Voucherdto vo = 	voucherService.update(data);
		if(vo == null || data.equals(null)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(vo);
		
	}
	@DeleteMapping("/admin/voucher/delete/{id}")
	@ResponseBody
	public ResponseEntity<Integer> update(@PathVariable("id") Integer id) {
		
		Integer id_voucher = 	voucherService.delete(id);
		if(id_voucher == null || id.equals(null)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(id_voucher);
	}
	@PostMapping("/admin/voucher/list")
	@ResponseBody
	public ResponseEntity< DataResponseList<Voucherdto>> getAllVouchers(@RequestBody ResponBodyVoucher data) {
		DataResponseList<Voucherdto> rp=new DataResponseList<Voucherdto>();
		List<Voucherdto> list= voucherService.getAllVouchers(data);
		Long n=voucherService.totalement();
		Integer sobanghi=n.intValue();
		if(list.isEmpty()) {
			return ResponseEntity.noContent().build();
		}else {
			rp.setMessage("Success");
		rp.setListData(list);
		rp.setCount(sobanghi);
		}	
		return ResponseEntity.ok(rp);
	}
}

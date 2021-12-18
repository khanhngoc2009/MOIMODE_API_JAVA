package com.it15306.controller.admin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
import com.it15306.dto.idBody;
import com.it15306.dto.voucher.RequetVoucher;
import com.it15306.dto.voucher.ResponBodyVoucher;
import com.it15306.dto.voucher.ResponVoucher;
import com.it15306.dto.voucher.Voucherdto;
import com.it15306.repository.OrderRepository;
import com.it15306.repository.VoucherRepository;
import com.it15306.services.VoucherService;
import com.it15306.servicesImpl.thongKeServiceImpl;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200","http://35.198.241.56" })
@RestController
@RequestMapping("/miemode_api/v1")
public class AdminVoucher {
	@Autowired
	VoucherService voucherService;
	
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	thongKeServiceImpl thongKeServiceImpl;
	
	
	@PostMapping("/admin/voucher/create")
	@ResponseBody
	public ResponseEntity<Voucherdto>  create(@Validated @RequestBody RequetVoucher data, BindingResult bindingResult) {
		boolean check = bindingResult.hasErrors();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		try {
		Long soday =	thongKeServiceImpl.daysBetween2Dates(dateFormat.format(data.getStart_time()),dateFormat.format(data.getEnd_time()));
			if(!check && soday.intValue() > 0) {
				Voucherdto vo = 	voucherService.create(data);
				if(vo == null || data.equals(null)) {
					return ResponseEntity.noContent().build();
				}
				return ResponseEntity.ok(vo);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 	ResponseEntity.badRequest().build();
	}
	
	@PostMapping("/admin/voucher/update")
	@ResponseBody
	public ResponseEntity<Voucherdto> update(@Validated @RequestBody Voucherdto data, BindingResult bindingResult) {
		boolean check = bindingResult.hasErrors();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Long soday =	thongKeServiceImpl.daysBetween2Dates(dateFormat.format(data.getStart_time()),dateFormat.format(data.getEnd_time()));
			if(!check && soday.intValue() > 0) {
			Voucherdto vo = 	voucherService.update(data);
			if(vo == null || data.equals(null)) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(vo);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 	ResponseEntity.badRequest().build();
		
		
	}
	@PostMapping("/admin/voucher/delete")
	@ResponseBody
	public ResponseEntity<Integer> delete(@RequestBody idBody data) {
		
		Integer id_voucher = 	voucherService.delete(data.getId());
		if(id_voucher == null || data.getId().equals(null)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(id_voucher);
	}
	@PostMapping("/admin/voucher/list")
	@ResponseBody
	public ResponseEntity< DataResponseList<ResponVoucher>> getAllVouchers(@RequestBody ResponBodyVoucher data) {
		DataResponseList<ResponVoucher> rp=new DataResponseList<ResponVoucher>();
		List<Voucherdto> list= voucherService.getAllVouchers(data);
		List<ResponVoucher> listnew= new ArrayList<ResponVoucher>();
		list.forEach(vc ->{
			ResponVoucher vou=new ResponVoucher();
			vou.setId(vc.getId());
			vou.setCodeVoucher(vc.getCodeVoucher());
			vou.setCountVoucherUsed(orderRepository.COUNT_VOUCHER_USE(String.valueOf(vc.getId())));
			vou.setCreate_time(vc.getCreate_time());
			vou.setDescription(vc.getDescription());
			vou.setDiscount(vc.getDiscount());
			vou.setEnd_time(vc.getEnd_time());
			vou.setStart_time(vc.getStart_time());
			vou.setTitle(vc.getTitle());
			vou.setStatus(vc.getStatus());
			vou.setType_discount(vc.getType_discount());
			vou.setUrl(vc.getUrl());
			listnew.add(vou);
		});
		Long n=voucherService.totalement();
		Integer sobanghi=n.intValue();
		if(list.isEmpty()) {
			return ResponseEntity.noContent().build();
		}else {
			rp.setMessage("Success");
		rp.setListData(listnew);
		rp.setCount(sobanghi);
		}	
		return ResponseEntity.ok(rp);
	}
}

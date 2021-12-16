package com.it15306.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.it15306.dto.thongKeBody;
import com.it15306.dto.dashboard.BienDoDHang;
import com.it15306.dto.dashboard.BienDoDThu;
import com.it15306.dto.dashboard.Year;
import com.it15306.dto.dashboard.YearBody;
import com.it15306.services.thongKeService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200","http://35.198.241.56" })
@RestController
@RequestMapping("/miemode_api/v1")
public class AdminThongKe {
	
	@Autowired
	thongKeService keService;
	
	@ApiOperation(value = "API thống kê theo doanh thu")
	@PostMapping("/admin/thong-ke/doanh-thu-nam")
	@ResponseBody
	public ResponseEntity<BienDoDThu>  thongKeDoanhThuTheoThang(@Validated @RequestBody YearBody data, BindingResult bindingResult) {
	boolean check=	bindingResult.hasErrors();
	if(!check) {
		BienDoDThu bd =	keService.thongKetBienDoanhThu(data);
		return ResponseEntity.ok(bd);
	}
		
		return ResponseEntity.badRequest().build();
	}
	
	@ApiOperation(value = "API thống kê đơn hàng thành công")
	@PostMapping("/admin/thong-ke/don-hang")
	@ResponseBody
	public ResponseEntity<BienDoDHang>  thongKeDonHangTheoThang(@Validated @RequestBody YearBody data, BindingResult bindingResult) {
	boolean check=	bindingResult.hasErrors();
	if(!check) {
		BienDoDHang bd =	keService.thongKetBienDonHang(data);
		return ResponseEntity.ok(bd);
	}
		
		return ResponseEntity.badRequest().build();
	}
	
	@ApiOperation(value = "API thống kê năm")
	@PostMapping("/admin/thong-ke/nam")
	@ResponseBody
	public ResponseEntity<List<Year>>  thongKenam() {
		
		try {
			return ResponseEntity.ok(keService.listYears());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.badRequest().build();
	}

}

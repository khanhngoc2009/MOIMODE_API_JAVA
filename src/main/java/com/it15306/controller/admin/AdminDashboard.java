package com.it15306.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.it15306.services.thongKeService;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200","http://35.198.241.56" })
@RestController
@RequestMapping("/miemode_api/v1")
public class AdminDashboard {
	@Autowired
	thongKeService keService;
//	Thống kê doanh thu
	@PostMapping("/admin/dashboard/doanh-thu/sum")
	@ResponseBody
	public ResponseEntity<Float> thongKeDoanhThu() {
		Float sum =keService.thongKeDoanhThu();
		if(sum != null) {
			return  ResponseEntity.ok(sum);
		}
		 return ResponseEntity.badRequest().build();
	}
	
//	Thống kê đơn hàng
	@PostMapping("/admin/dashboard/order/count")
	@ResponseBody
	public ResponseEntity<Integer>  thongKeDonHang() {
		Integer count =keService.thongKeDonHang();
		if(count != null) {
			return  ResponseEntity.ok(count);
		}
		 return ResponseEntity.badRequest().build();
	}
	
//	Thống kê khách hàng
	@PostMapping("/admin/dashboard/user/count")
	@ResponseBody
	public Integer thongKeKhachHang() {
		 return keService.thongKeKhachHang();
	}
//
//	Thống kê sản phẩm
	@PostMapping("/admin/dashboard/product/count")
	@ResponseBody
	public ResponseEntity<Integer> thongKeSanPham() {
		Integer count =keService.thongKeSanPham();
		if(count != null) {
			return  ResponseEntity.ok(count);
		}
		 return ResponseEntity.badRequest().build();
	}
//	Tổng hợp đơn hàng
//	Biên độ doanh thu
//	Biên độ đơn hàng
//	Thêm một mục
	

}

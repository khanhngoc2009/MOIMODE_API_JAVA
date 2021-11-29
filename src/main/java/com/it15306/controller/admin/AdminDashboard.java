package com.it15306.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.it15306.config.DataResponseList;
import com.it15306.dto.dashboard.BienDoDHang;
import com.it15306.dto.dashboard.BienDoDThu;
import com.it15306.dto.dashboard.ThongKeBody;
import com.it15306.dto.dashboard.TongHopDonHang;
import com.it15306.dto.order.OrderDto;
import com.it15306.services.thongKeService;

import io.swagger.annotations.ApiOperation;

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
	@ApiOperation(value = "API dếm tổng số lượng đơn hàng theo trạng thái")
	@PostMapping("/admin/dashboard/tong-hop/don-hang")
	@ResponseBody
	public ResponseEntity<TongHopDonHang> tonghopdonhang() {
		TongHopDonHang thop =keService.thongKetTongHopDonHang();
		if(thop != null) {
			return  ResponseEntity.ok(thop);
		}
		 return ResponseEntity.badRequest().build();
	}
	
	//	Biên độ doanh thu
	@ApiOperation(value = "API bien độ doanh thu đơn hàng theo tuần")
	@PostMapping("/admin/dashboard/bien-do/don-hang")
	@ResponseBody
	public ResponseEntity<BienDoDThu> biendodoanhthudh() {
		try {
			BienDoDThu bDo= keService.thongKetBienDoDoanhThu();
			if(bDo != null) {
				return ResponseEntity.ok(bDo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return	ResponseEntity.badRequest().build();
		}
		
		 return ResponseEntity.noContent().build();
	}
	//	Biên độ đơn hàng
	@ApiOperation(value = "API bien độ đơn hàng theo tuần")
	@PostMapping("/admin/dashboard/bien-do/doanh-thu")
	@ResponseBody
	public ResponseEntity<BienDoDHang> biendodonhang() {
		try {
			BienDoDHang bDo= keService.thongKetBienDoDonHang();
			if(bDo != null) {
				return ResponseEntity.ok(bDo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return	ResponseEntity.badRequest().build();
		}
		
		 return ResponseEntity.noContent().build();
	}
	//	Thêm một mục
	
	@ApiOperation(value = "API thống kê theo doanh thu")
	@PostMapping("/admin/dashboard/doanh-thu")
	@ResponseBody
	public ResponseEntity<DataResponseList<OrderDto>>  thongKeDoanhThus(@RequestBody ThongKeBody data) {
		DataResponseList<OrderDto> datarp=new DataResponseList<OrderDto>();
		try {
				List<OrderDto> list=keService.thongKeDoanhThu(data);
				datarp.setListData(list);
				datarp.setCount(keService.countTotalElement());
				return ResponseEntity.ok(datarp);
		
		} catch (Exception e) {
			e.printStackTrace();
			
		}		
		 return ResponseEntity.badRequest().build();
	}
	
	@ApiOperation(value = "API thống kê tổng doanh thu")
	@PostMapping("/admin/dashboard/sum/doanh-thu")
	@ResponseBody
	public ResponseEntity<Float>  thongKeTongDoanhThus(@RequestBody ThongKeBody data) {
		
		try {
				Float tong=keService.sumDoanhThu(data);
				
				return ResponseEntity.ok(tong);
		
		} catch (Exception e) {
			e.printStackTrace();
			
		}		
		 return ResponseEntity.badRequest().build();
	}
	
	@ApiOperation(value = "API thống kê count đơn hàng")
	@PostMapping("/admin/dashboard/count/don-hang")
	@ResponseBody
	public ResponseEntity<Integer>  countDonHang(@RequestBody ThongKeBody data) {
		
		try {
				return ResponseEntity.ok(keService.countDonHang(data));
		
		} catch (Exception e) {
			e.printStackTrace();
			
		}		
		 return ResponseEntity.badRequest().build();
	}
	
	@ApiOperation(value = "API thống kê list đơn hàng")
	@PostMapping("/admin/dashboard/don-hang/list")
	@ResponseBody
	public ResponseEntity<DataResponseList<OrderDto>>  thongKeDonHang(@RequestBody ThongKeBody data) {
		DataResponseList<OrderDto> datarp=new DataResponseList<OrderDto>();
		try {
				List<OrderDto> list=keService.thongKeDonHang(data);
				datarp.setListData(list);
				datarp.setCount(keService.countTotalElement());
				return ResponseEntity.ok(datarp);
		
		} catch (Exception e) {
			e.printStackTrace();
			
		}		
		 return ResponseEntity.badRequest().build();
	}
}

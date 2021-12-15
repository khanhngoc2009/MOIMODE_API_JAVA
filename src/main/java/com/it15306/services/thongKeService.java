package com.it15306.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.it15306.dto.thongKeBody;
import com.it15306.dto.dashboard.BienDoDHang;
import com.it15306.dto.dashboard.BienDoDThu;
import com.it15306.dto.dashboard.ThongKeBody;
import com.it15306.dto.dashboard.TongHopDonHang;
import com.it15306.dto.dashboard.YearBody;
import com.it15306.dto.order.OrderDto;

@Service
public interface thongKeService {
//	Thống kê doanh thu
	public Float thongKeDoanhThu();
//	Thống kê đơn hàng
	public Integer thongKeDonHang();
//	Thống kê khách hàng
	public Integer thongKeKhachHang();
//
//	Thống kê sản phẩm
	public Integer thongKeSanPham();
//	Tổng hợp đơn hàng
	public TongHopDonHang thongKetTongHopDonHang();
//	Biên độ doanh thu
	public BienDoDThu thongKetBienDoDoanhThu();
//	Biên độ đơn hàng
	public BienDoDHang thongKetBienDoDonHang();
//	thống kê doanh thu
	public List<OrderDto> thongKeDoanhThu(ThongKeBody data);
	public Float  sumDoanhThu(ThongKeBody data);
// thống kê đơn hàng
	public List<OrderDto> thongKeDonHang(ThongKeBody data);
	public Integer  countDonHang(ThongKeBody data);
	public Integer countTotalElement();
	// thống kê đơn hàng
	public List<Integer> listYears();
	///
	
	public BienDoDThu thongKetBienDoanhThu(YearBody data);
	
	public BienDoDHang thongKetBienDonHang(YearBody data);
}

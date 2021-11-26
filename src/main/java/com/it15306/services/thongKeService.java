package com.it15306.services;

import org.springframework.stereotype.Service;

import com.it15306.dto.dashboard.BienDoDHang;
import com.it15306.dto.dashboard.BienDoDThu;
import com.it15306.dto.dashboard.TongHopDonHang;

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
//	Thêm một mục
	
}

package com.it15306.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it15306.dto.dashboard.TongHopDonHang;
import com.it15306.repository.CategoryRepository;
import com.it15306.repository.OrderRepository;
import com.it15306.repository.ProductRepository;
import com.it15306.repository.UserRepository;
import com.it15306.services.thongKeService;
@Service
public class thongKeServiceImpl implements thongKeService{
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	OrderRepository  orderRepository;
	@Override
	public Float thongKeDoanhThu() {
		
		return orderRepository.thongKeOrderDoanhThu(4);
	}

	@Override
	public Integer thongKeDonHang() {
		Long soluong=orderRepository.count();
		Integer soluongI=soluong.intValue();
		return soluongI;
	}

	@Override
	public Integer thongKeKhachHang() {
		
		return  userRepository.thongKeCountUser(1);
	}

	@Override
	public Integer thongKeSanPham() {
		
		return productRepository.thongKeCountProduct(1);
	}

	@Override
	public TongHopDonHang thongKetTongHopDonHang() {
		TongHopDonHang donHang =new TongHopDonHang();
		Integer choXH= orderRepository.thongKeOrderCount(1);
		Integer daXN= orderRepository.thongKeOrderCount(2);
		Integer dangVChuyen= orderRepository.thongKeOrderCount(3);
		Integer hoanThanh= orderRepository.thongKeOrderCount(4);
		Integer khachHuy= orderRepository.thongKeOrderCount(5);
		Integer cuaHangHuy= orderRepository.thongKeOrderCount(6);
		donHang.setChoXacNhan(choXH != null ? choXH:0);
		donHang.setDaXacNhan(daXN !=null ?daXN:0);
		donHang.setDangVanChuyen(dangVChuyen !=null ?dangVChuyen:0);
		donHang.setHoanThanh(hoanThanh !=null ?hoanThanh:0);
		donHang.setKhachHuy(khachHuy !=null ?khachHuy:0);
		donHang.setCuaHangHuy(cuaHangHuy !=null ?cuaHangHuy:0);
		return donHang;
	}

}

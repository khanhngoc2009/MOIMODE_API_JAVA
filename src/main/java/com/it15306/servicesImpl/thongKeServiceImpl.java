package com.it15306.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}

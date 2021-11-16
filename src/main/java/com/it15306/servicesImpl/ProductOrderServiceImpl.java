package com.it15306.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.it15306.entities.Order;
import com.it15306.entities.ProductOrder;
import com.it15306.repository.OrderRepository;
import com.it15306.repository.ProductOrderRepository;
import com.it15306.services.OrderService;

public class ProductOrderServiceImpl {
	
	@Autowired
	private ProductOrderRepository productOrderRepository;
	
	public List<ProductOrder> saveList(List<ProductOrder> list) {
		List<ProductOrder> listRes =  productOrderRepository.saveAll(list);
		return listRes;
	}
	
	public ProductOrder save(ProductOrder productOrder) {
		return productOrderRepository.save(productOrder);
	}
	
	public List<ProductOrder> getListByOrderId(Integer order_id) {
		return productOrderRepository.getByOrderId(order_id);
	}
	

}

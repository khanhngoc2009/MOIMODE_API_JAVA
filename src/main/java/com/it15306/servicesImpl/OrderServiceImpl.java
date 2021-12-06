package com.it15306.servicesImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.it15306.entities.Order;
import com.it15306.entities.ProductOrder;
import com.it15306.repository.OrderRepository;
import com.it15306.repository.ProductOrderRepository;
import com.it15306.services.OrderService;

@Service("OrderServiceImpl")
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired 
	private ProductOrderRepository productOrderRepository; 
	
	@Override
	public List<Order> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order saveUser(Order Order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}
	
	public Order getByOrderId(Integer order_id) {
		return orderRepository.getOne(order_id);
	}
	
	public Order saveOrder(Order order) {
		return orderRepository.save(order);
	}
	
	public ProductOrder saveProductOrder (ProductOrder product_order) {
		
		return productOrderRepository.save(product_order);
	}
	
	public List<Order> getListOrders(int page,int take,String status,Integer user_id,String start_date,String end_date) {
		Pageable paging =  PageRequest.of(page, take); 
        Page<Order> pagedResult = orderRepository.getListOrders(status,user_id,start_date,end_date,paging);
       
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
        	return new ArrayList<Order>();
		}
	}
	public Integer countOrderClient(String status,Integer user_id,String start_date,String end_date) {
		return orderRepository.getCountClient(status, user_id,start_date,end_date);
	}
	
	public List<Order> getListOrdersAdmin(int page,int take,String status,String email, String user_name, String phone, String start_date,String end_date ) {
		Pageable paging =  PageRequest.of(page, take); 
        Page<Order> pagedResult = orderRepository.getOrdersAdmin(status, email, user_name, phone, start_date, end_date,paging);
        List<Order> list =  pagedResult.getContent();
        for(int i = 0;i <list.size();i++) {
        	System.out.print(list.get(i).getCreate_date());
        }
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
        	return new ArrayList<Order>();
		}
	}
	public Integer countOrderAdmin(String status,String email, String user_name, String phone, String start_date,String end_date ) {
		return orderRepository.getCountAdmin(status, email, user_name, phone, start_date, end_date);
	}
	
	public Order getDetailById(Integer order_id) {
		return orderRepository.getDetailOrderId(order_id);
	}
	
}

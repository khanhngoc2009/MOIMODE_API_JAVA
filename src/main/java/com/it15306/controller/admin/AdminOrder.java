package com.it15306.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.it15306.config.DataResponse;
import com.it15306.config.DataResponseList;
import com.it15306.dto.AddressOrderDTO;
import com.it15306.dto.DistrictDTO;
import com.it15306.dto.ProvinceDTO;
import com.it15306.dto.WardDTO;
import com.it15306.dto.order.CreateOrderDto;
import com.it15306.dto.order.DataChangeStatusDto;
import com.it15306.dto.order.DataChangeTypePaymentDto;
import com.it15306.dto.order.DataListOrderAdminDto;
import com.it15306.dto.order.DataListOrderDto;
import com.it15306.dto.order.OrderDto;
import com.it15306.dto.order.ProductOrderDto;
import com.it15306.dto.payment.PaymentDTO;
import com.it15306.dto.voucher.Voucherdto;
import com.it15306.entities.AddressOrder;
import com.it15306.entities.CartProduct;
import com.it15306.entities.Order;
import com.it15306.entities.ProductOrder;
import com.it15306.entities.Product_Sku;
import com.it15306.entities.User;
import com.it15306.entities.Voucher;
import com.it15306.jwt.JwtTokenProvider;
import com.it15306.services.CartService;
import com.it15306.services.UserService;
import com.it15306.services.VoucherService;
import com.it15306.servicesImpl.MailServiceImpl;
import com.it15306.servicesImpl.OrderServiceImpl;
import com.it15306.servicesImpl.ProductServiceImpl;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200","http://35.198.241.56" })
@RestController
@RequestMapping("/miemode_api/v1")
public class AdminOrder {
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	private UserService userservice;
	
	@Autowired 
	private OrderServiceImpl orderServiceImpl;
	
	@Autowired 
	private CartService cartService;
	
	@Autowired 
	private ProductServiceImpl productServiceImpl;
	
	@Autowired 
	private VoucherService voucherService;
	
	@Autowired
	ModelMapper modelMapper;

	@Autowired 
	private MailServiceImpl mailServiceImpl;
	@RequestMapping(value = "/order-admin/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<DataResponseList<OrderDto>> listOrder(@RequestBody DataListOrderAdminDto dto,HttpServletRequest httpServletRequest) {
		DataResponseList<OrderDto> data = new DataResponseList<OrderDto>();
//		try {
				// lay danh dach order (phan trang)
				List<Order> list_order = orderServiceImpl.getListOrdersAdmin(dto.getPage(), dto.getTake(), 
						dto.getStatus()!=null ?dto.getStatus() : null,
						dto.getEmail()!=null ?dto.getEmail() : "",
						dto.getUser_name() !=null ?dto.getEmail() : "",
						dto.getPhone()!=null ?dto.getPhone() : "",
						dto.getStart_date()!=null ?dto.getStart_date() : "2000-01-01",
						dto.getEnd_date()!=null ? dto.getEnd_date() : "2099-01-01");
				int size= list_order.size();
				System.out.print(size);
				data.setCount(orderServiceImpl.countOrderAdmin(dto.getStatus()!=null ?dto.getStatus() : null,
						dto.getEmail()!=null ?dto.getEmail() : "",
						dto.getUser_name() !=null ?dto.getEmail() : "",
						dto.getPhone()!=null ?dto.getPhone() : "",
						dto.getStart_date()!=null ?dto.getStart_date() : "2000-01-01",
						dto.getEnd_date()!=null ? dto.getEnd_date() : "2099-01-01"));
				List<OrderDto> listOrders= new ArrayList<OrderDto>();
				for(int i= 0;i< size;i++) {
					Order order = list_order.get(i);
					OrderDto orderDto = modelMapper.map(order, OrderDto.class);
					List<ProductOrder> listProductOrders =  order.getProduct_orders();
					List<ProductOrderDto> list_pro_o_dtos = new ArrayList<ProductOrderDto>(); 
					int size_p_os = listProductOrders.size();
					for(int j=0;j<size_p_os;j++) {
						list_pro_o_dtos.add(modelMapper.map(listProductOrders.get(j), ProductOrderDto.class));
					}
					orderDto.setListProduct(list_pro_o_dtos);
					AddressOrder ad = order.getAddress();
					AddressOrderDTO ad_dto = modelMapper.map(ad, AddressOrderDTO.class);
					ad_dto.setProvincedto(modelMapper.map(ad.getProvince(), ProvinceDTO.class));
					ad_dto.setDistrictdto(modelMapper.map(ad.getDistrict(), DistrictDTO.class));
					ad_dto.setWarddto(modelMapper.map(ad.getWard(), WardDTO.class));
					orderDto.setAddressOrder(ad_dto);
					orderDto.setVoucher(modelMapper.map(order.getVoucher(), Voucherdto.class));
					orderDto.setPaymentType(modelMapper.map(order.getPayment(), PaymentDTO.class));
					listOrders.add(orderDto);
					data.setListData(listOrders);
				}
				data.setCode(HttpStatus.OK.value());
				data.setListData(listOrders);
				data.setMessage("SUCCESS");
				return new ResponseEntity<>(data,HttpStatus.OK);
//		} catch (Exception e) {
//			data.setCode(HttpStatus.FAILED_DEPENDENCY.value());
//			data.setMessage("Fail");
//			return new ResponseEntity<>(data,HttpStatus.FAILED_DEPENDENCY);
//		}
	}
	@RequestMapping(value = "/admin/order/change-status", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> changeStatusOrder(@RequestBody DataChangeStatusDto dto,HttpServletRequest httpServletRequest) {
		DataResponse<OrderDto> data = new DataResponse<OrderDto>();
		try {
				Order order =  orderServiceImpl.getByOrderId(dto.getOrder_id());
				order.setStatus(dto.getStatus());
				mailServiceImpl.sendMailOrder(order.getUser().getEmail(), dto.getStatus());
				Order order_after_update = orderServiceImpl.saveOrder(order);
				data.setData(modelMapper.map(order_after_update, OrderDto.class));
				data.setCode(HttpStatus.OK.value());
				data.setMessage("SUCCESS");
				return new ResponseEntity<>(data,HttpStatus.OK);
			
		} catch (Exception e) {
			data.setCode(HttpStatus.FAILED_DEPENDENCY.value());
			data.setMessage("Fail");
			
			return new ResponseEntity<>(data,HttpStatus.FAILED_DEPENDENCY);
		}
	}

	
	@RequestMapping(value = "/admin/order/change-type-payment", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> changeTypePayment(@RequestBody DataChangeTypePaymentDto dto,HttpServletRequest httpServletRequest) {
		DataResponse<OrderDto> data = new DataResponse<OrderDto>(); 
		try {
			Order order =  orderServiceImpl.getByOrderId(dto.getOrder_id());
			order.setStatus(dto.getType());
			Order order_after_update = orderServiceImpl.saveOrder(order);
			data.setData(modelMapper.map(order_after_update, OrderDto.class));
			data.setCode(HttpStatus.OK.value());
			data.setMessage("SUCCESS");
			return new ResponseEntity<>(data,HttpStatus.OK);
		} catch (Exception e) {
			data.setCode(HttpStatus.FAILED_DEPENDENCY.value());
			data.setMessage("Fail");
			return new ResponseEntity<>(data,HttpStatus.FAILED_DEPENDENCY);
		}
	}
}

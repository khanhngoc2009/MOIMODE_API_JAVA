package com.it15306.controller.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
import com.it15306.dto.order.DataDetailDto;
import com.it15306.dto.order.DataListOrderDto;
import com.it15306.dto.order.OrderDetailDto;
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
import com.it15306.servicesImpl.CartServiceImpl;
import com.it15306.servicesImpl.MailServiceImpl;
import com.it15306.servicesImpl.OrderServiceImpl;
import com.it15306.servicesImpl.ProductServiceImpl;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200","http://35.198.241.56" })
@RestController
@RequestMapping("/miemode_api/v1")
public class CustomerOrder {
	
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
	@RequestMapping(value = "/order/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> createOrder(@RequestBody CreateOrderDto dto,HttpServletRequest httpServletRequest) {
		DataResponse<String> data = new DataResponse<String>(); 
		try {
			// ly thong tin user
			System.out.print("khoong laas dc ," + httpServletRequest.getHeader("Authorization"));
			String token = httpServletRequest.getHeader("Authorization").substring(7);
			String username = tokenProvider.getUserNameFromJWT(token);
			User user = userservice.getByUsername(username);
			
			if(username!= null && dto.getAddress_id()!=null && dto.getPayment_id()!=null) {
				int size = dto.getListCartId().size();
				double voucher_discount = 0;
				double total_order = 0;
				List<Product_Sku> list_product_sku =  new ArrayList<Product_Sku>();
				List<CartProduct> list_cart_product =  new ArrayList<CartProduct>();
					if(size>0) {
						for(int i=0;i<size;i++) {
							CartProduct cart_product = cartService.getByCartProductId(dto.getListCartId().get(i));
							Product_Sku product_sku =  cart_product.getProductSkus();
							total_order =  total_order + (product_sku.getPrice() * cart_product.getQuantity());
							list_product_sku.add(product_sku);
							list_cart_product.add(cart_product);
						}
						
						if(dto.getVoucher_id()!= null && dto.getVoucher_id()!= 0) {
							Voucherdto voucher = voucherService.getByIdVoucher(dto.getVoucher_id());
							int type_discount =  voucher.getType_discount();
//							
							if(type_discount == 1 ) {
								voucher_discount = voucher.getDiscount();
							}else if(type_discount == 2) {
								voucher_discount = voucher.getDiscount()/total_order * 100;
							}
						}
						AddressOrder addressOrder = new AddressOrder();
						addressOrder.setid(dto.getAddress_id());
						dto.getAddress_id();
						Order order = new Order();
						order.setCreate_date(new Date());
						order.setStatus(1); // chờ xác nhan
						order.setUser(user); 
						order.setAddress(addressOrder);
						Voucher voucher  = new Voucher();
						voucher.setId(dto.getVoucher_id()!=null ? dto.getVoucher_id(): null );
						order.setVoucher(voucher);
						order.setTotal_price(total_order - voucher_discount + 30000);
						
						Order  order_after_save =  orderServiceImpl.saveOrder(order);
						for(int i=0;i<size;i++) {
							Product_Sku p_sku = list_product_sku.get(i);
							ProductOrder product_order = new ProductOrder();
							product_order.setCreate_date(new Date());
							product_order.setImage(p_sku.getUrl_media());
							product_order.setOrder(order_after_save);
							product_order.setPrice(p_sku.getPrice());
							product_order.setProperties(dto.getNote());
							product_order.setStatus(1);
							product_order.setQuantity(list_cart_product.get(i).getId()); // lay so luong cua san pham
							product_order.setProduct_name(p_sku.getProduct().getProduct_name()); // lay ten cua product
							orderServiceImpl.saveProductOrder(product_order);
							cartService.deleteCartProductByID(list_cart_product.get(i).getId());
						}
						data.setCode(200);
						data.setMessage("Success");
						return new ResponseEntity<>(data,HttpStatus.OK);
				
				}else {
					data.setCode(HttpStatus.NOT_FOUND.value());
					data.setMessage("NOT_FOUND");
					return new ResponseEntity<>(data,HttpStatus.NOT_FOUND);
				}
			}else {
				data.setCode(HttpStatus.UNAUTHORIZED.value());
				data.setMessage("AUTHEN");
				return new ResponseEntity<>(data,HttpStatus.UNAUTHORIZED);
			}
			
		} catch (Exception e) {
			data.setCode(HttpStatus.FAILED_DEPENDENCY.value());
			data.setMessage("Fail");
			
			return new ResponseEntity<>(data,HttpStatus.FAILED_DEPENDENCY);
		}
		
	}
	@RequestMapping(value = "/order/change-status", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> changeStatusOrder(@RequestBody DataChangeStatusDto dto,HttpServletRequest httpServletRequest) {
		DataResponse<OrderDto> data = new DataResponse<OrderDto>();
		
		String token = httpServletRequest.getHeader("Authorization").substring(7);
		String username = tokenProvider.getUserNameFromJWT(token);
		User user = userservice.getByUsername(username);
		try {
			if(user != null) {
				Order order =  orderServiceImpl.getByOrderId(dto.getOrder_id());
				order.setStatus(dto.getStatus());
				mailServiceImpl.sendMailOrder(user.getEmail(), dto.getStatus());
				Order order_after_update = orderServiceImpl.saveOrder(order);
				data.setData(modelMapper.map(order_after_update, OrderDto.class));
				data.setCode(HttpStatus.OK.value());
				data.setMessage("SUCCESS");
				return new ResponseEntity<>(data,HttpStatus.OK);
			}else {
				data.setCode(HttpStatus.UNAUTHORIZED.value());
				data.setMessage("Fail");
				return new ResponseEntity<>(data,HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			data.setCode(HttpStatus.FAILED_DEPENDENCY.value());
			data.setMessage("Fail");
			
			return new ResponseEntity<>(data,HttpStatus.FAILED_DEPENDENCY);
		}
	}
	
	@RequestMapping(value = "/order/change-type-payment", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
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
	
	
	@RequestMapping(value = "/order/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<DataResponseList<OrderDto>> listOrder(@RequestBody DataListOrderDto dto,HttpServletRequest httpServletRequest) {
		DataResponseList<OrderDto> data = new DataResponseList<OrderDto>();
		try {
			// ly thong tin user
			String token = httpServletRequest.getHeader("Authorization").substring(7);
			String username = tokenProvider.getUserNameFromJWT(token);
			User user = userservice.getByUsername(username);
			if( user != null) {
				// lay danh dach order (phan trang) 
				List<Order> list_order = orderServiceImpl.getListOrders(dto.getPage(), dto.getTake(), dto.getStatus(),user.getId());
				int size= list_order.size();
				data.setCount(orderServiceImpl.countOrderClient(dto.getStatus(),user.getId()));
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
				data.setMessage("SUCCESS");
				return new ResponseEntity<>(data,HttpStatus.OK);
			}else {
				data.setCode(HttpStatus.UNAUTHORIZED.value());
				data.setMessage("AUTHEN");
				return new ResponseEntity<>(data,HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			data.setCode(HttpStatus.FAILED_DEPENDENCY.value());
			data.setMessage("Fail");
			return new ResponseEntity<>(data,HttpStatus.FAILED_DEPENDENCY);
		}
	}
	
	@RequestMapping(value = "/order/detail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<DataResponse<OrderDto>> orderDetail(@RequestBody DataDetailDto dto) {
		DataResponse<OrderDto> data = new DataResponse<OrderDto>(); 
		try {
				Order order = orderServiceImpl.getDetailById(dto.getId());
				OrderDto detailDto = modelMapper.map(order, OrderDto.class);
				List<ProductOrderDto> list_product = new ArrayList<ProductOrderDto>();
				for(int i= 0;i< order.getProduct_orders().size();i++) {
					list_product.add(modelMapper.map(order.getProduct_orders().get(i), ProductOrderDto.class));
				}
				AddressOrder ad = order.getAddress();
				AddressOrderDTO ad_dto = modelMapper.map(ad, AddressOrderDTO.class);
				ad_dto.setProvincedto(modelMapper.map(ad.getProvince(), ProvinceDTO.class));
				ad_dto.setDistrictdto(modelMapper.map(ad.getDistrict(), DistrictDTO.class));
				ad_dto.setWarddto(modelMapper.map(ad.getWard(), WardDTO.class));
				detailDto.setAddressOrder(ad_dto);
				detailDto.setVoucher(modelMapper.map(order.getVoucher(), Voucherdto.class));
				detailDto.setPaymentType(modelMapper.map(order.getPayment(), PaymentDTO.class));
				data.setData(detailDto);
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

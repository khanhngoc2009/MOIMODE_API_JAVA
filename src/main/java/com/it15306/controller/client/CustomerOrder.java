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
import com.it15306.dto.order.DataCancelOrderDto;
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
import com.it15306.entities.Payment;
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
//			User user = userservice.getById("32");
//			String username = "khanh";
			if(username!= null && dto.getAddress_id()!=null && dto.getPayment_id()!=null) {
				int size = dto.getListCartId().size();
				double voucher_discount = 0;
				double total_order = 0;
				List<Product_Sku> list_product_sku =  new ArrayList<Product_Sku>();
				List<CartProduct> list_cart_product =  new ArrayList<CartProduct>();
					if(size>0) {
						for(int i=0;i<size;i++) {
							CartProduct cart_product = cartService.getByCartProductId(dto.getListCartId().get(i).getId());
							Product_Sku product_sku =  cart_product.getProductSkus();
							total_order =  total_order + (product_sku.getPrice() * cart_product.getQuantity());
							list_product_sku.add(product_sku);
							list_cart_product.add(cart_product);
						}
						AddressOrder addressOrder = new AddressOrder();
						addressOrder.setid(dto.getAddress_id());
						dto.getAddress_id();
						Order order = new Order();
						order.setCreate_date(new Date());
						order.setStatus(1); // chờ xác nhan
						order.setUser(user);
						Payment payment = new Payment();
						payment.setPayment_id(dto.getPayment_id());
						order.setPayment(payment);
						order.setAddress(addressOrder);
						order.setType_payment(0);
						Voucher vou  = new Voucher();
						if(dto.getVoucher_id()!= null && dto.getVoucher_id()!= 0) {
							
							Voucherdto voucher = voucherService.getByIdVoucher(dto.getVoucher_id());
							vou.setId(voucher.getId());
							int type_discount =  voucher.getType_discount();
//							
							if(type_discount == 1 ) {
								voucher_discount = voucher.getDiscount();
							}else if(type_discount == 2) {
								voucher_discount = voucher.getDiscount()/total_order * 100;
							}
							order.setVoucher(vou);
						}else {
							vou.setId(10000);
							order.setVoucher(vou);
						}
						double total_payment = total_order - voucher_discount + 30000;
						order.setTotal_price(total_payment>=0 ? total_payment:0);
//						order.set
						Order  order_after_save =  orderServiceImpl.saveOrder(order);
						for(int i=0;i<size;i++) {
							Product_Sku p_sku = list_product_sku.get(i);
							ProductOrder product_order = new ProductOrder();
							product_order.setCreate_date(new Date());
							product_order.setImage(p_sku.getUrl_media());
							product_order.setOrder(order_after_save);
							product_order.setPrice(p_sku.getPrice());
							List<Object> obj = productServiceImpl.getSkuOption(p_sku.getProduct_sku_id());
							String optionValueProducts = "";
							for(int k =  0;k<obj.size();k++) {
								Object[] row = (Object[]) obj.get(k);
								optionValueProducts = optionValueProducts + (k != 0 ? ", " : "") + (String) row[0];
							}
							product_order.setProperties(optionValueProducts);
							product_order.setStatus(1);
							product_order.setQuantity(list_cart_product.get(i).getQuantity()); // lay so luong cua san pham
							product_order.setProduct_name(p_sku.getProduct().getProduct_name()); // lay ten cua product
							orderServiceImpl.saveProductOrder(product_order);
							cartService.deleteCartProductByID(list_cart_product.get(i).getId());
						}
						data.setCode(200);
						data.setMessage("Success");
						data.setData("Success");
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
	@RequestMapping(value = "/order/cancel-order", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> changeCancelOrder(@RequestBody DataCancelOrderDto dto,HttpServletRequest httpServletRequest) {
		DataResponse<OrderDto> data = new DataResponse<OrderDto>();
		
		String token = httpServletRequest.getHeader("Authorization").substring(7);
		String username = tokenProvider.getUserNameFromJWT(token);
		User user = userservice.getByUsername(username);
		try {
			if(user != null) {
				Order order =  orderServiceImpl.getByOrderId(dto.getOrder_id());
				order.setStatus(1);
				mailServiceImpl.sendMailOrder(user.getEmail(), 1);
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
	@RequestMapping(value = "/order/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<DataResponseList<OrderDto>> listOrder(@RequestBody DataListOrderDto dto,HttpServletRequest httpServletRequest) {
		DataResponseList<OrderDto> data = new DataResponseList<OrderDto>();
		try {
//			 ly thong tin user
			String token = httpServletRequest.getHeader("Authorization").substring(7);
			String username = tokenProvider.getUserNameFromJWT(token);
			User u = userservice.getByUsername(username);
//			User u = userservice.getById(String.valueOf(32));
			if( u != null) {
				
				String Statusteam;
				if(dto.getStatus() == null || dto.getStatus().equals("")) {
					Statusteam="";
				}else {
					Statusteam = String.valueOf(dto.getStatus());
				}						
				// lay danh dach order (phan trang) 
				List<Order> list_order = orderServiceImpl.getListOrders(
						dto.getPage(), 
						dto.getTake(), 
						 Statusteam ,
						u.getId(),
						dto.getStart_date()!=null && dto.getStart_date().length()>0?dto.getStart_date() : "2000-01-01",
						dto.getEnd_date()!=null && dto.getEnd_date().length()>0? dto.getEnd_date() : "2099-01-01");
				
				int size= list_order.size();
				data.setCount(orderServiceImpl.countOrderClient(
						Statusteam,
						u.getId(),
						dto.getStart_date()!=null && dto.getStart_date().length()>0?dto.getStart_date() : "2000-01-01",
						dto.getEnd_date()!=null && dto.getEnd_date().length()>0 ? dto.getEnd_date() : "2099-01-01"));
				
				List<OrderDto> listOrders= new ArrayList<OrderDto>();
				for(int i= 0;i< size;i++) {
					Order order = list_order.get(i);
					OrderDto orderDto = modelMapper.map(order, OrderDto.class);
					List<ProductOrder> listProductOrders =  order.getProduct_orders();
					List<ProductOrderDto> list_pro_o_dtos = new ArrayList<ProductOrderDto>(); 
					int size_p_os = listProductOrders.size();
					for(int j=0;j<size_p_os;j++) {
						ProductOrderDto pro_o =  modelMapper.map(listProductOrders.get(j), ProductOrderDto.class);
						pro_o.setProductName(listProductOrders.get(j).getProduct_name());
						pro_o.setCreateDate(listProductOrders.get(j).getCreate_date());
						list_pro_o_dtos.add(pro_o);
					}
					orderDto.setCreateDate(order.getCreate_date());
					orderDto.setId(order.getOrder_id());
					orderDto.setTotalPrice(order.getTotal_price());
					orderDto.setPaymentStatus(order.getType_payment());
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
			}else {
				data.setCode(HttpStatus.UNAUTHORIZED.value());
				data.setMessage("AUTHEN");
				return new ResponseEntity<>(data,HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			e.printStackTrace();
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
			Order order_after_update = orderServiceImpl.getDetailById(dto.getId());
			OrderDto orderDto = modelMapper.map(order_after_update, OrderDto.class);
			List<ProductOrderDto> list_product = new ArrayList<ProductOrderDto>();
			for(int i= 0;i< order_after_update.getProduct_orders().size();i++) {
				ProductOrderDto pro_o =  modelMapper.map(order_after_update.getProduct_orders().get(i), ProductOrderDto.class);
				pro_o.setProductName(order_after_update.getProduct_orders().get(i).getProduct_name());
				pro_o.setCreateDate(order_after_update.getProduct_orders().get(i).getCreate_date());
				list_product.add(pro_o);
			}
			orderDto.setCreateDate(order_after_update.getCreate_date());
			orderDto.setId(order_after_update.getOrder_id());
			orderDto.setTotalPrice(order_after_update.getTotal_price());
			orderDto.setPaymentStatus(order_after_update.getType_payment());
			orderDto.setListProduct(list_product);
			AddressOrder ad = order_after_update.getAddress();
			AddressOrderDTO ad_dto = modelMapper.map(ad, AddressOrderDTO.class);
			ad_dto.setProvincedto(modelMapper.map(ad.getProvince(), ProvinceDTO.class));
			ad_dto.setDistrictdto(modelMapper.map(ad.getDistrict(), DistrictDTO.class));
			ad_dto.setWarddto(modelMapper.map(ad.getWard(), WardDTO.class));
			orderDto.setAddressOrder(ad_dto);
			orderDto.setVoucher(modelMapper.map(order_after_update.getVoucher(), Voucherdto.class));
			orderDto.setPaymentType(modelMapper.map(order_after_update.getPayment(), PaymentDTO.class));
			data.setData(orderDto);
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

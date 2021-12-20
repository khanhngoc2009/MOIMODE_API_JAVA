package com.it15306.controller.admin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.thymeleaf.util.DateUtils;

import com.it15306.config.ConfigDefine;
import com.it15306.config.ConfigOrder;
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
import com.it15306.dto.order.DataListOrderAdminDto;
import com.it15306.dto.order.DataListOrderDto;
import com.it15306.dto.order.OrderDto;
import com.it15306.dto.order.PayloadCreateOrderAdmin;
import com.it15306.dto.order.ProductOrderDto;
import com.it15306.dto.order.ProductSkuPayloadOrder;
import com.it15306.dto.payment.PaymentDTO;
import com.it15306.dto.product.ProductSkuDto;
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
import com.it15306.servicesImpl.MailServiceImpl;
import com.it15306.servicesImpl.OrderServiceImpl;
import com.it15306.servicesImpl.ProductServiceImpl;
import com.it15306.utils.Const;

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
	
	@RequestMapping(value = "/admin/order/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<DataResponseList<OrderDto>> listOrder(@RequestBody DataListOrderAdminDto dto,HttpServletRequest httpServletRequest) {
		DataResponseList<OrderDto> data = new DataResponseList<OrderDto>();
		Const constFig = new Const();
		try {
//			String startDate =  constFig.getDate(dto.getStartTime());
//			String endDate = constFig.getDate(dto.getEndTime());
//			System.out.println(startDate + " ./ " + endDate);
				// lay danh dach order (phan trang)
				List<Order> list_order = orderServiceImpl.getListOrdersAdmin(
						dto.getPage(), 
						dto.getTake(), 
						dto.getStatus()!=null && dto.getStatus()!= 0 ? String.valueOf(dto.getStatus()) : "",
						dto.getEmail()!=null && dto.getEmail().length()>0 ?dto.getEmail() : "",
						dto.getUserName() !=null && dto.getUserName().length()>0  ?dto.getUserName() : "",
						dto.getPhone()!=null && dto.getPhone().length()> 0?dto.getPhone() : "",
						dto.getStartTime()!=null&& dto.getStartTime().length()>0 ?dto.getStartTime() : "2000-01-01",
						dto.getEndTime()!=null && dto.getEndTime().length()>0 ? constFig.getDate(dto.getEndTime()) : "2099-01-01",
						dto.getId()!=null && dto.getId().length()>0 ? dto.getId(): "");
				int size= list_order.size();
				System.out.print(size);
				data.setCount(orderServiceImpl.countOrderAdmin(
					dto.getStatus()!=null ?String.valueOf(dto.getStatus()) : "",
					dto.getEmail()!=null && dto.getEmail().length()>0 ?dto.getEmail() : "",
					dto.getUserName() !=null && dto.getUserName().length()>0  ?dto.getUserName() : "",
					dto.getPhone()!=null && dto.getPhone().length()> 0?dto.getPhone() : "",
					dto.getStartTime()!=null&& dto.getStartTime().length()>0 ?dto.getStartTime() : "2000-01-01",
					dto.getEndTime()!=null && dto.getEndTime().length()>0 ? constFig.getDate(dto.getEndTime()) : "2099-01-01",
					dto.getId()!=null && dto.getId().length()>0 ? dto.getId(): ""
					));
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
					if(order.getPhone_guest()!= null 
							&& order.getName_guest()!=null 
							&& order.getName_guest().length()>0 
							&& order.getPhone_guest().length()>0) {
							ad_dto.setPhone_persion(order.getPhone_guest());
							ad_dto.setName_persion(order.getName_guest());
						}
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
		} catch (Exception e) {
			data.setCode(HttpStatus.FAILED_DEPENDENCY.value());
			data.setMessage("Fail");
			return new ResponseEntity<>(data,HttpStatus.FAILED_DEPENDENCY);
		}
	}
	@RequestMapping(value = "/admin/order/change-status", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> changeStatusOrder(@RequestBody DataChangeStatusDto dto,HttpServletRequest httpServletRequest) {
		DataResponse<OrderDto> data = new DataResponse<OrderDto>();
		ConfigDefine config= new ConfigDefine();
		try {
				Order order =  orderServiceImpl.getByOrderId(dto.getOrder_id());
				order.setStatus(dto.getStatus());
				if(dto.getStatus() == config.CANCEL || dto.getStatus() == config.DENY) {
					if(dto.getReason() == null || dto.getReason().length()==0) {
						data.setCode(HttpStatus.FAILED_DEPENDENCY.value());
						data.setMessage("Ban can nhap ly do");
						return new ResponseEntity<>(data,HttpStatus.FAILED_DEPENDENCY);
					}else {
						order.setReason(dto.getReason());
					}
				}
				  if(order.getUser().getId() != 0) {
					  mailServiceImpl.sendMailOrder(order.getUser().getEmail(), dto.getStatus());
				  }
				Order order_after_update = orderServiceImpl.saveOrder(order);
				
				OrderDto orderDto = modelMapper.map(order_after_update, OrderDto.class);
				List<ProductOrderDto> list_pro_o_dtos = new ArrayList<ProductOrderDto>(); 
				int size_p_os = order_after_update.getProduct_orders().size();
				for(int j=0;j<size_p_os;j++) {
					ProductOrderDto pro_o =  modelMapper.map(order_after_update.getProduct_orders().get(j), ProductOrderDto.class);
					Product_Sku p_sku = productServiceImpl.getProductSkuById(pro_o.getSku_id());
					p_sku.setQuantity_total(p_sku.getQuantity_total() + pro_o.getQuantity());
					p_sku.setQuantiy_rest(p_sku.getQuantiy_rest() - pro_o.getQuantity());
					productServiceImpl.saveProductSku(p_sku);
					pro_o.setProductName(order_after_update.getProduct_orders().get(j).getProduct_name());
					pro_o.setCreateDate(order_after_update.getProduct_orders().get(j).getCreate_date());
					list_pro_o_dtos.add(pro_o);
				}
				orderDto.setCreateDate(order_after_update.getCreate_date());
				orderDto.setId(order_after_update.getOrder_id());
				orderDto.setTotalPrice(order_after_update.getTotal_price());
				orderDto.setPaymentStatus(order_after_update.getType_payment());
				orderDto.setListProduct(list_pro_o_dtos);
				if(dto.getStatus() == config.CANCEL || dto.getStatus() == config.DENY) {
					if(dto.getReason() == null || dto.getReason().length()==0) {
						data.setCode(HttpStatus.FAILED_DEPENDENCY.value());
						data.setMessage("Ban can nhap ly do");
						return new ResponseEntity<>(data,HttpStatus.FAILED_DEPENDENCY);
					}else {
						orderDto.setReason(dto.getReason());
					}
				}
				AddressOrder ad = order_after_update.getAddress();
				AddressOrderDTO ad_dto = modelMapper.map(ad, AddressOrderDTO.class);
				if(order_after_update.getPhone_guest()!= null 
						&& order_after_update.getName_guest()!=null 
						&& order_after_update.getName_guest().length()>0 
						&& order_after_update.getPhone_guest().length()>0) {
						ad_dto.setPhone_persion(order_after_update.getPhone_guest());
						ad_dto.setName_persion(order_after_update.getName_guest());
					}
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

	
	@RequestMapping(value = "/admin/order/change-status-payment", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> changeTypePayment(@RequestBody DataChangeTypePaymentDto dto,HttpServletRequest httpServletRequest) {
		DataResponse<OrderDto> data = new DataResponse<OrderDto>(); 
		try {
			Order order =  orderServiceImpl.getByOrderId(dto.getOrder_id());
			order.setStatus(dto.getType());
			Order order_after_update = orderServiceImpl.saveOrder(order);
			OrderDto orderDto = modelMapper.map(order_after_update, OrderDto.class);
			List<ProductOrderDto> list_pro_o_dtos = new ArrayList<ProductOrderDto>(); 
			int size_p_os = order_after_update.getProduct_orders().size();
			for(int j=0;j<size_p_os;j++) {
				ProductOrderDto pro_o =  modelMapper.map(order_after_update.getProduct_orders().get(j), ProductOrderDto.class);
				pro_o.setProductName(order_after_update.getProduct_orders().get(j).getProduct_name());
				pro_o.setCreateDate(order_after_update.getProduct_orders().get(j).getCreate_date());
				list_pro_o_dtos.add(pro_o);
			}
			orderDto.setCreateDate(order_after_update.getCreate_date());
			orderDto.setId(order_after_update.getOrder_id());
			orderDto.setTotalPrice(order_after_update.getTotal_price());
			orderDto.setPaymentStatus(order_after_update.getType_payment());
			orderDto.setListProduct(list_pro_o_dtos);
			AddressOrder ad = order_after_update.getAddress();
			AddressOrderDTO ad_dto = modelMapper.map(ad, AddressOrderDTO.class);
			ad_dto.setProvincedto(modelMapper.map(ad.getProvince(), ProvinceDTO.class));
			ad_dto.setDistrictdto(modelMapper.map(ad.getDistrict(), DistrictDTO.class));
			ad_dto.setWarddto(modelMapper.map(ad.getWard(), WardDTO.class));
			if(order_after_update.getPhone_guest()!= null 
					&& order_after_update.getName_guest()!=null 
					&& order_after_update.getName_guest().length()>0 
					&& order_after_update.getPhone_guest().length()>0) {
					ad_dto.setPhone_persion(order_after_update.getPhone_guest());
					ad_dto.setName_persion(order_after_update.getName_guest());
				}
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
	@RequestMapping(value = "/admin/order/detail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
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
				if(order_after_update.getPhone_guest()!= null 
					&& order_after_update.getName_guest()!=null 
					&& order_after_update.getName_guest().length()>0 
					&& order_after_update.getPhone_guest().length()>0) {
					ad_dto.setPhone_persion(order_after_update.getPhone_guest());
					ad_dto.setName_persion(order_after_update.getName_guest());
				}
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
	
	/// tạo mới đơn hang admin
	@RequestMapping(value = "/admin/order/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> createOrder(@RequestBody PayloadCreateOrderAdmin dto,HttpServletRequest httpServletRequest) {
		DataResponse<String> data = new DataResponse<String>(); 
		ConfigDefine config = new ConfigDefine();
		try {
			
			if(dto.getName()!=null && dto.getPhone()!=null && dto.getPayment_id()!=null) {
				User user = userservice.getById("0");
				int size = dto.getListProductSku().size();
				double voucher_discount = 0;
				double total_order = 0;
					if(size>0) {
						for(int i=0;i<size;i++) {
							ProductSkuDto product_sku =  dto.getListProductSku().get(i).getSku();
							if(dto.getListProductSku().get(i).getSku().getQuantity_total()==0){
								data.setCode(HttpStatus.EXPECTATION_FAILED.value());
								data.setMessage("Số lượng không đủ, vui lòng kiểm tra lại giỏ hàng!");
								return new ResponseEntity<>(data,HttpStatus.EXPECTATION_FAILED);
							}
							total_order =  total_order + (product_sku.getPrice() * dto.getListProductSku().get(i).getQuantity());
						}
						AddressOrder addressOrder = new AddressOrder();
						addressOrder.setid(0);
//						dto.getAddress_id();
						Order order = new Order();
						order.setCreate_date(new Date());
						order.setStatus(config.SUCCESS); // chuyen thanh don thanh cong luon
						order.setUser(user);
						Payment payment = new Payment();
						payment.setPayment_id(dto.getPayment_id());
						order.setPayment(payment);
						order.setAddress(addressOrder);
						order.setType_payment(0);
						order.setPhone_guest(dto.getPhone());
						order.setName_guest(dto.getName());
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
							vou.setId(0);
							order.setVoucher(vou);
						}
						double total_payment = total_order - voucher_discount + 0;
						order.setTotal_price(total_payment>=0 ? total_payment:0);
						order.setNote(dto.getNote());
						order.setIsEvaluate(0);
						order.setReason("");
						Order  order_after_save =  orderServiceImpl.saveOrder(order);
						for(int i=0;i<size;i++) {
							ProductSkuDto p_sku = dto.getListProductSku().get(i).getSku();
							ProductOrder product_order = new ProductOrder();
							product_order.setCreate_date(new Date());
							product_order.setImage(p_sku.getUrl_media());
							product_order.setOrder(order_after_save);
							product_order.setPrice(p_sku.getPrice());
							product_order.setProduct_id(p_sku.getProduct().getId());
							product_order.setSku_id(p_sku.getProduct_sku_id());
							List<Object> obj = productServiceImpl.getSkuOption(p_sku.getProduct_sku_id());
							String optionValueProducts = "";
							for(int k =  0;k<obj.size();k++) {
								Object[] row = (Object[]) obj.get(k);
								optionValueProducts = optionValueProducts + (k != 0 ? ", " : "") + (String) row[0];
							}
							product_order.setProperties(optionValueProducts);
							product_order.setStatus(1);
							product_order.setQuantity(dto.getListProductSku().get(i).getQuantity()); // lay so luong cua san pham
							product_order.setProduct_name(p_sku.getProduct().getProduct_name()); // lay ten cua product
							orderServiceImpl.saveProductOrder(product_order);
							Product_Sku productSku =  productServiceImpl.getProductSkuById(dto.getListProductSku().get(i).getSku().getProduct_sku_id());
							int rest =productSku.getQuantiy_rest() + dto.getListProductSku().get(i).getQuantity();
							int total =p_sku.getQuantity_total() - dto.getListProductSku().get(i).getQuantity();
							productSku.setQuantiy_rest(rest>=0?rest:0);
							productSku.setQuantity_total(total>=0?total:0);
							productServiceImpl.saveProductSku(productSku);
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
	
}

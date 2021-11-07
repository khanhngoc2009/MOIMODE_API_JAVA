package com.it15306.controller.admin;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
import com.it15306.dto.PageDto;
import com.it15306.dto.option.OptionDTO;
import com.it15306.dto.product.DataBodyListProductDto;
import com.it15306.dto.product.DataCreateProductDtos;
import com.it15306.dto.product.ListSkuCreateDto;
import com.it15306.dto.product.ProductDTO;
import com.it15306.dto.product.ProductResponseAdminDto;
import com.it15306.dto.product.ProductSkuDto;
import com.it15306.dto.product.ProductSkuGetBodyDto;
import com.it15306.entities.Category;
import com.it15306.entities.OptionValue;
import com.it15306.entities.Option_Product;
import com.it15306.entities.Option_Sku_Value;
import com.it15306.entities.Options;
import com.it15306.entities.Product;
import com.it15306.entities.Product_Sku;
import com.it15306.entities.Sku;
import com.it15306.servicesImpl.OptionValueServiceImpl;
import com.it15306.servicesImpl.OptionsProductsServiceImpl;
import com.it15306.servicesImpl.OptionsServiceImpl;
import com.it15306.servicesImpl.ProductServiceImpl;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
@RequestMapping("/miemode_api/v1")
public class AdminProduct {

	@Autowired
	private ProductServiceImpl productServiceImpl;
	
	@Autowired
	private OptionsProductsServiceImpl optionsProductsServiceImpl;

	@Autowired
	private OptionsServiceImpl optionsServiceImpl;
	
	@Autowired
	private OptionValueServiceImpl optionValueServiceImpl;
	

	@RequestMapping(value = "/admin/product/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> createProduct(@RequestBody DataCreateProductDtos body) {
		DataResponse<Integer> res=  new DataResponse<Integer>();
		Option_Product op_pr = new Option_Product();
		Product product = new Product();
		Category category = new Category();
		
		try {
			 // them san pham
			product.setCreate_date(new Date());
			product.setDescription(body.getProduct().getDescription());
			product.setImage(body.getProduct().getImage());
			product.setStatus(1);
			product.setType(1);
			category.setId(body.getProduct().getCategory_id());
			product.setCategory(category);
			product.setProduct_name(body.getProduct().getProduct_name());
			Product p =  productServiceImpl.saveProduct(product);
			// them vao ban option_product
			int sizeListOption =  body.getListOption().size();
			if(sizeListOption>0) {
				for(int i=0;i<sizeListOption;i++) {
					optionsProductsServiceImpl.save_value(body.getListOption().get(i).getId(), p.getId());
				}
			}
			res.setCode(200);
			res.setMessage("Thanh cong");
			res.setData(p.getId());
			return new ResponseEntity<>(res,HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			res.setCode(HttpStatus.FAILED_DEPENDENCY.value());
			res.setMessage("THAT BAI");
			return new ResponseEntity<>(res,HttpStatus.FAILED_DEPENDENCY);
		}
	}

	@RequestMapping(value = "/admin/product-sku/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> getListSku(@RequestBody ListSkuCreateDto body) {
		DataResponseList<ProductSkuGetBodyDto> res=  new DataResponseList<ProductSkuGetBodyDto>();
		List<ProductSkuGetBodyDto> listProductDtos = new ArrayList<ProductSkuGetBodyDto>();
		ModelMapper modelMapper = new ModelMapper();
		try {
			int size = body.getList_sku().size();
			for(int i=0;i<size;i++) {
				System.out.print(i + "\n");
				Product_Sku p_u=new Product_Sku();
				p_u.setCreate_date(new Date());
				p_u.setPrice(0);
				p_u.setStatus(1);
				Product p = productServiceImpl.getById(body.getProduct_id());
				p_u.setProduct(p);
				p_u.setQuantity_remain(0);
				p_u.setQuantiy_rest(0);
				p_u.setQuantity_total(0);
				p_u.setValue_sku(body.getList_sku().get(i));
				Product_Sku pro_s = productServiceImpl.saveProductSku(p_u);
				ProductSkuGetBodyDto product_sku_dto = (modelMapper.map(pro_s, ProductSkuGetBodyDto.class));
				product_sku_dto.setProduct((modelMapper.map(p, ProductDTO.class)));
				listProductDtos.add(product_sku_dto);
			}
			for(int i=0;i<body.getList_id() .size();i++) {
				int idSkuProduct =  listProductDtos.get(i).getProduct_sku_id();
				
				String[] listRes = body.getList_id().get(i).split("/");
				for(int j=0;j<listRes.length;j++) {
					System.out.print(idSkuProduct + "\n");
					System.out.print(j + "\n");
					productServiceImpl.saveSku(idSkuProduct,Integer.parseInt(listRes[j]));
				}
			}
			res.setCode(200);
			res.setMessage("Thanh cong");
			res.setListData(listProductDtos);
			return new ResponseEntity<>(res,HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			res.setCode(HttpStatus.FAILED_DEPENDENCY.value());
			res.setMessage("Thanh cong");
			return new ResponseEntity<>(res,HttpStatus.FAILED_DEPENDENCY);
		}
	}
	
	
	@RequestMapping(value = "/admin/product/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> getAllProducts(@RequestBody DataBodyListProductDto dto) throws ParseException {
		ModelMapper modelMapper = new ModelMapper();
		DataResponseList<ProductResponseAdminDto> data = new DataResponseList<ProductResponseAdminDto>();
		long count = (long) this.productServiceImpl.getCountAdmin();
	    String category_id = dto.getCategory_id()!=null ? dto.getCategory_id().toString() : "" ;
		String name = dto.getName()!= null && dto.getName().length() > 0 ? dto.getName() : "";
		String start_date = dto.getStart_date() !=null && dto.getStart_date().length() > 0 ? dto.getStart_date() : "2000-01-01"; 
		String end_start = dto.getEnd_date() !=null && dto.getEnd_date().length() > 0 ? dto.getEnd_date() : "2099-01-01"; 
		String status = dto.getStatus() !=null ? dto.getStatus().toString() : "";
		List<Object> obj = this.productServiceImpl.getAllProductsAdmin(dto.getPage(), dto.getTake(),category_id,start_date,end_start,name,status);
		List<Product> prs = new ArrayList<Product>();
		System.out.print(obj.size());
		for (int i=0; i<obj.size(); i++){
			   Object[] row = (Object[]) obj.get(i);
			   Product pr = new Product();
			   pr.setId((Integer) ((BigInteger) row[0]).intValue());
			   pr.setProduct_name((String) row[1]);
			   pr.setCreate_date((Date) row[2]);
			   pr.setDescription((String) row[3]);
			   pr.setStatus((Integer) row[5]);
			   pr.setType((Integer) row[6]);
			   pr.setImage((String) row[7]);
			   prs.add(pr);
		}
		List<ProductResponseAdminDto> productDTOs =new ArrayList<ProductResponseAdminDto>();
		if (prs.size() > 0) {
			for (int i = 0; i < prs.size(); i++) {
				ProductResponseAdminDto prDto = (modelMapper.map(prs.get(i), ProductResponseAdminDto.class));
				prDto.setCategory_name(prs.get(i).getCategory().getName());
				productDTOs.add(prDto);
			}
		}
		data.setCode(200);
		data.setCount(Integer.parseInt(String.valueOf(count)));
		data.setListData(productDTOs);
		data.setMessage("Success");
		return new ResponseEntity<>(data,HttpStatus.OK);
//		try {
//			Category category = new Category();
//			category.setId(dto.getCategory_id()!=null ? dto.getCategory_id() : null);
//			String name = dto.getName()!= null && dto.getName().length() > 0 ? dto.getName() : "";
//			String start_date = dto.getStart_date() !=null && dto.getStart_date().length() > 0 ? dto.getStart_date() : "2000-01-01"; 
//			String end_start = dto.getEnd_date() !=null && dto.getEnd_date().length() > 0 ? dto.getEnd_date() : "2021-12-31";
//			String status = dto.getStatus() !=null ? dto.getStatus().toString() : "";
//			List<Product> prs = this.productServiceImpl.getAllProductsAdmin(dto.getPage(), dto.getTake(),category,start_date,end_start,name,status);
//			
//			List<ProductResponseAdminDto> productDTOs =new ArrayList<ProductResponseAdminDto>();
//			if (prs.size() > 0) {
//				for (int i = 0; i < prs.size(); i++) {
//					ProductResponseAdminDto prDto = (modelMapper.map(prs.get(i), ProductResponseAdminDto.class));
//					prDto.setCategory_name(prs.get(i).getCategory().getName());
//					productDTOs.add(prDto);
//				}
//			}
//			data.setCode(200);
//			data.setCount(Integer.parseInt(String.valueOf(count)));
//			data.setListData(productDTOs);
//			data.setMessage("Success");
//			return new ResponseEntity<>(data,HttpStatus.OK);
//		} catch (Exception e) {
//			data.setCode(HttpStatus.FAILED_DEPENDENCY.value());
//			data.setMessage("Fail");
//			return new ResponseEntity<>(data,HttpStatus.FAILED_DEPENDENCY);
//		}
		
	}
	
	@RequestMapping(value = "/admin/product-sku/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> updateListSku(@RequestBody List<ProductSkuDto> dto) {
		ModelMapper modelMapper = new ModelMapper();
		DataResponseList<String> data = new DataResponseList<String>();
		long count = (long) this.productServiceImpl.getCountAdmin();
		try {
			int size = dto.size();
			for(int i=0;i<size;i++) {
				Product_Sku p_sku=modelMapper.map(dto.get(i), Product_Sku.class); 
				productServiceImpl.saveProductSku(p_sku);
			}
			data.setCount(Integer.parseInt(String.valueOf(count)));
			data.setMessage("Success");
			return new ResponseEntity<>(data,HttpStatus.OK);
		} catch (Exception e) {
			data.setCode(HttpStatus.FAILED_DEPENDENCY.value());
			data.setMessage("Fail");
			return new ResponseEntity<>(data,HttpStatus.FAILED_DEPENDENCY);
		}
		
	}
	
	
	
	
//	public List<String> mergeToArray(List<String> array1,List<String> array2) {
//		List<String> finalRes = new ArrayList<String>();
//		for(int i=0;i<array1.size();i++) {
//			for(int j=0;j<array2.size();j++) {
//				finalRes.add(array1.get(i)+ array2.get(j));
//			}
//		}
//		return finalRes;
//	}
//	
//	public List<?> postArray(List<Options> array){
//		List<?> list = new ArrayList<>();
//		
//		for(int i =0;i<array.size();i++) {
//			List<OptionValue> listOp = array.get(i).getOption_values();
//			List<String> list = array.
//			for(int j=0;j<listOp.size();j++) {
//				
//			}
//			
//		}
//		return list;
//	}
	
	
	
	
	
}

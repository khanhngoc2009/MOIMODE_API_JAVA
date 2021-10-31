package com.it15306.controller.admin;

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
import com.it15306.dto.product.DataCreateProductDtos;
import com.it15306.dto.product.ListSkuCreateDto;
import com.it15306.dto.product.ProductDTO;
import com.it15306.dto.product.ProductResponseAdminDto;
import com.it15306.dto.product.ProductSkuDto;
import com.it15306.dto.product.ProductSkuGetBodyDto;
import com.it15306.entities.Category;
import com.it15306.entities.OptionValue;
import com.it15306.entities.Option_Product;
import com.it15306.entities.Options;
import com.it15306.entities.Product;
import com.it15306.entities.Product_Sku;
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
	

	@RequestMapping(value = "/admin/createProduct", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
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
			Integer sizeListOption =  body.getListOption().size();
			if(sizeListOption>0) {
				for(int i=0;i<sizeListOption;i++) {
					Options op = optionsServiceImpl.getById(body.getListOption().get(i).getId());
					op_pr.setProduct(p);
					op_pr.setOption(op);
					optionsProductsServiceImpl.save(op_pr);
				}
			}
			res.setCode(200);
			res.setMessage("Thanh cong");
			res.setData(p.getId());
			return new ResponseEntity<>(res,HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(res,HttpStatus.FAILED_DEPENDENCY);
		}
	}

	@RequestMapping(value = "/admin/getListSku", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> getListSku(@RequestBody ListSkuCreateDto body) {
		DataResponseList<ProductSkuGetBodyDto> res=  new DataResponseList<ProductSkuGetBodyDto>();
		List<ProductSkuGetBodyDto> listProductDtos = new ArrayList<ProductSkuGetBodyDto>();
		ModelMapper modelMapper = new ModelMapper();
		try {
			int size = body.getList_sku().size();
			for(int i=0;i<size;i++) {
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
	
	
	
	@RequestMapping(value = "/admin/getAllProducts", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> getAllProducts(@RequestBody PageDto dto) {
		ModelMapper modelMapper = new ModelMapper();
		DataResponseList<ProductResponseAdminDto> data = new DataResponseList<ProductResponseAdminDto>();
		long count = (long) this.productServiceImpl.getCountAdmin();
		try {
			List<Product> prs = this.productServiceImpl.getAllProductsAdmin(dto.getPage(), dto.getTake());
			
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

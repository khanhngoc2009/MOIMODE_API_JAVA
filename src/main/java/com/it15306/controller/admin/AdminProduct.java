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

import com.it15306.config.DataResponseList;
import com.it15306.dto.PageDto;
import com.it15306.dto.option.OptionDTO;
import com.it15306.dto.product.DataCreateProductDtos;
import com.it15306.dto.product.ProductDTO;
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
		DataResponseList<String> listRes=  new DataResponseList<String>();
		Option_Product op_pr = new Option_Product();
		Product product = new Product();
		Category category = new Category(); 
		List<Options> listOption = new ArrayList<Options>();
		
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
			String[] listName = body.getProduct().getProduct_name().split(" ");
			String name = "";
			for(int i=0;i<listName.length;i++) {
				name = name +  listName[i].charAt(0);
			}
//			productServiceImpl.saveProduct(product);
			// them vao ban option_product
			Integer sizeListOption =  body.getListOption().size();
			if(sizeListOption>0) {
				for(int i=0;i<sizeListOption;i++) {
					Options op = optionsServiceImpl.getById(body.getListOption().get(i));
					List<OptionValue> listOPV =  op.getOption_values();
					for(int j=0;j< listOPV.size();j++) {
						Product_Sku p_sku = new Product_Sku();
						
						
					}
					op_pr.setProduct(product);
					op_pr.setOption(op);
//					optionsProductsServiceImpl.save(op_pr);
				}
			}
			listRes.setCode(200);
			listRes.setMessage("Thanh cong");
			listRes.setCount(0);
			return new ResponseEntity<>(listRes,HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<>(listRes,HttpStatus.FAILED_DEPENDENCY);
		}
		
	}
	
	@RequestMapping(value = "/admin/getAllProducts", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> getAllProducts(@RequestBody PageDto dto) {
		ModelMapper modelMapper = new ModelMapper();
		DataResponseList<ProductDTO> data = new DataResponseList<ProductDTO>();
		long count = (long) this.productServiceImpl.getCountClient();
		try {
			List<Product> prs = this.productServiceImpl.getAllProductsAdmin(dto.getPage(), dto.getTake());
			
			List<ProductDTO> productDTOs =new ArrayList<ProductDTO>();
			if (prs.size() > 0) {
				for (int i = 0; i < prs.size(); i++) {
					ProductDTO prDto = (modelMapper.map(prs.get(i), ProductDTO.class));
					prDto.setCategory_id(prs.get(i).getCategory().getId());
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

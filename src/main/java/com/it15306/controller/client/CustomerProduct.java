package com.it15306.controller.client;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.it15306.dto.CategoryDTO;
import com.it15306.dto.DistrictDTO;
import com.it15306.dto.OptionClientDto;
import com.it15306.dto.OptionDTO;
import com.it15306.dto.OptionValueClientDto;
import com.it15306.dto.OptionValueDTO;
import com.it15306.dto.ProductDTO;
import com.it15306.dto.ProductSkuValueDto;
import com.it15306.dto.ProvinceDTO;
import com.it15306.dto.UserDTO;
import com.it15306.dto.product.DataBodyFindSkuDto;
import com.it15306.dto.product.ProductDetailDto;
import com.it15306.entities.Category;
import com.it15306.entities.District;
import com.it15306.entities.Options;
import com.it15306.entities.Product;
import com.it15306.entities.Province;
import com.it15306.entities.User;
import com.it15306.servicesImpl.ProductServiceImpl;
import com.it15306.servicesImpl.ProvinceServiceImpl;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
@RequestMapping("/miemode_api/v1")
public class CustomerProduct {
	@Autowired
	private ProductServiceImpl productServiceImpl;
	

	@RequestMapping(value = "/getListProducts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ProductDTO> getListProducts() {
		ModelMapper modelMapper = new ModelMapper();
		List<Object> list = this.productServiceImpl.getAllProducts();
		List<Double> minPrice = new ArrayList<Double>();
		List<Double> maxPrice = new ArrayList<Double>();
		List<Product> prs = new ArrayList<Product>();
		for (int i=0; i<list.size(); i++){
			   Object[] row = (Object[]) list.get(i);
			   Product pr = (Product) row[0];
			   minPrice.add((Double) row[1]);
			   maxPrice.add((Double) row[2]);
			   prs.add(pr);
		}
		List<ProductDTO> productDTOs =new ArrayList<ProductDTO>();
		if (prs.size() > 0) {
			for (int i = 0; i < prs.size(); i++) {
				ProductDTO prDto = (modelMapper.map(prs.get(i), ProductDTO.class));
				prDto.setMin_price(minPrice.get(i));
				prDto.setMax_price(maxPrice.get(i));
				prDto.setCategory_id(prs.get(i).getCategory().getCategory_id());
				productDTOs.add(prDto);
			}
			return productDTOs;
		}
		return productDTOs;
	}
	@RequestMapping(value = "/getListProductByCategory/{category_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ProductDTO> getListProductByCategory(@PathVariable Integer category_id) {
		ModelMapper modelMapper = new ModelMapper();
		Category category = new Category();
		category.setCategory_id(category_id);
		List<Object> list =  this.productServiceImpl.getProductByCategory(category);
		List<Double> minPrice = new ArrayList<Double>();
		List<Double> maxPrice = new ArrayList<Double>();
		List<Product> prs = new ArrayList<Product>();
		for (int i=0; i<list.size(); i++){
			   Object[] row = (Object[]) list.get(i);
			   Product pr = (Product) row[0];
			   minPrice.add((Double) row[1]);
			   maxPrice.add((Double) row[2]);
			   prs.add(pr);
		}
		List<ProductDTO> productDTOs =new ArrayList<ProductDTO>();
		if (prs.size() > 0) {
			for (int i = 0; i < prs.size(); i++) {
				ProductDTO prDto = (modelMapper.map(prs.get(i), ProductDTO.class));
				prDto.setMin_price(minPrice.get(i));
				prDto.setMax_price(maxPrice.get(i));
				prDto.setCategory_id(prs.get(i).getCategory().getCategory_id());
				productDTOs.add(prDto);
			}
			return productDTOs;
		}
		return productDTOs;
	}
//	
//	@RequestMapping(value = "/getProductDetail/{product_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public ProductDetailDto getProductDetail(@PathVariable Integer product_id) {
//		Object obj= this.productServiceImpl.getByIdProduct(product_id);
//		Object[] row = (Object[]) obj;
//		
//		Product product = (Product) row[0];
//	
//		ProductDetailDto pr = new ProductDetailDto();
//		ModelMapper modelMapper = new ModelMapper();
//		List<OptionClientDto> optionDTOs =new ArrayList<OptionClientDto>();
//		List<ProductSkuValueDto> productSkuDTOs = new ArrayList<ProductSkuValueDto>();
//		if (product.getOptions().size() > 0) {
//			for (int i = 0; i < product.getOptions().size(); i++) {
//				OptionProduct option = product.getOptions().get(i);				
//				List<OptionValueClientDto> optionValueDTOs =new ArrayList<OptionValueClientDto>();
//			
//				
//				for (int j = 0; j < option.getOption_values().size(); j++) {
//					optionValueDTOs.add(modelMapper.map(option.getOption_values().get(j), OptionValueClientDto.class));
//				}
//				OptionClientDto opV = modelMapper.map(product.getOptions().get(i), OptionClientDto.class);
//				opV.setListOptionValue(optionValueDTOs);
//				optionDTOs.add(opV);
//			}
//		}
//		if (product.getProduct_sku_values().size() > 0) {
//			for (int i = 0; i < product.getProduct_sku_values().size(); i++) {
//				ProductSkuValues productSku = product.getProduct_sku_values().get(i);			
//				productSkuDTOs.add(modelMapper.map(productSku,ProductSkuValueDto.class));
//			}
//		}
//		ProductDTO productDTO  = modelMapper.map(product, ProductDTO.class);
//		productDTO.setMin_price((Double) row[1]);
//		productDTO.setMax_price((Double) row[2]);
//		pr.setListOption(optionDTOs);
//		pr.setProduct(productDTO);
//		pr.setListProductSku(productSkuDTOs);
//		return pr;
//	}
//	@RequestMapping(value = "/getProductSkuPrice/{product_id}/{sku_value_id_1}/{sku_value_id_2}/{sku_value_id_3}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public ProductSkuValueDto getProductSkuPrice(@PathVariable Integer product_id,@PathVariable Integer sku_value_id_1,@PathVariable Integer sku_value_id_2,@PathVariable Integer sku_value_id_3) {
//		ModelMapper modelMapper = new ModelMapper();
//		Product product = new Product();
//		product.setProduct_id(product_id);
//		ProductSkuValues productSku = productSkuValueServiceImpl.
//		findProductSkuValues(
//				sku_value_id_1,
//				sku_value_id_2,
//				sku_value_id_3,
//				product);
//		ProductSkuValueDto productSkuDto  = modelMapper.map(productSku, ProductSkuValueDto.class);
//		
//		return productSkuDto;
//	}
//}
}

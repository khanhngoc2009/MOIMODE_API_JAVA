package com.it15306.controller.client;

import java.security.cert.PKIXRevocationChecker.Option;
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
import com.it15306.dto.OptionProductDto;
import com.it15306.dto.OptionValueClientDto;
import com.it15306.dto.OptionValueDTO;
import com.it15306.dto.ProductDTO;
import com.it15306.dto.ProductSkuDto;
import com.it15306.dto.ProductSkuValueDto;
import com.it15306.dto.ProvinceDTO;
import com.it15306.dto.UserDTO;
import com.it15306.dto.product.DataBodyFindSkuDto;
import com.it15306.dto.product.ProductDetailDto;
import com.it15306.entities.Category;
import com.it15306.entities.District;
import com.it15306.entities.OptionValue;
import com.it15306.entities.Option_Product;
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
	@RequestMapping(value = "/getProductDetail/{product_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ProductDetailDto getProductDetail(@PathVariable Integer product_id) {
		Product product= this.productServiceImpl.getByIdProduct(product_id);
		ProductDetailDto pr = new ProductDetailDto();
		
		ModelMapper modelMapper = new ModelMapper();
		List<OptionProductDto> optionProductDTOs =new ArrayList<OptionProductDto>();
		List<ProductSkuDto> productSkuDTOs = new ArrayList<ProductSkuDto>();
		int size = product.getOptions_products().size();
		System.out.print(size);
		if(size > 0) {
			
			for(int i=0;i<size;i++) {
				Option_Product ot_pr = product.getOptions_products().get(i);
				OptionProductDto op = modelMapper.map(ot_pr, OptionProductDto.class);
				int s = ot_pr.getOption().getOption_values().size();
				OptionDTO oDto = modelMapper.map(ot_pr.getOption(), OptionDTO.class);
				List<OptionValueClientDto> listOtvClient = new ArrayList<OptionValueClientDto>();
				for(int j=0;j<s;j++) {	
					OptionValue otvClient = ot_pr.getOption().getOption_values().get(j);
					listOtvClient.add(modelMapper.map(otvClient, OptionValueClientDto.class));
				}
				oDto.setOption_values(listOtvClient);
				op.setOption(oDto);
				optionProductDTOs.add(op);
			}
		}
		
		ProductDTO productDTO  = modelMapper.map(product, ProductDTO.class);
		pr.setOptions_products(optionProductDTOs);
		pr.setCategory(modelMapper.map(product.getCategory(), CategoryDTO.class));
		pr.setProduct(productDTO);
		pr.setProduct_sku(productSkuDTOs);
		return pr;
	}
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

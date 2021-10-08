package com.it15306.controller.client;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.it15306.dto.DistrictDTO;
import com.it15306.dto.ProductDTO;
import com.it15306.dto.ProvinceDTO;
import com.it15306.dto.UserDTO;
import com.it15306.entities.Category;
import com.it15306.entities.District;
import com.it15306.entities.Product;
import com.it15306.entities.Province;
import com.it15306.entities.User;
import com.it15306.servicesImpl.ProductServiceImpl;
import com.it15306.servicesImpl.ProvinceServiceImpl;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
@RequestMapping("/api/v1")
public class ClientProduct {
	@Autowired
	private ProductServiceImpl productServiceImpl;

	@RequestMapping(value = "/getListProducts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ProductDTO> getListProducts() {
		ModelMapper modelMapper = new ModelMapper();
		List<Product> products = this.productServiceImpl.getAllProduct();
		List<ProductDTO> productDTOs =new ArrayList<ProductDTO>();
		if (products.size() > 0) {
			for (int i = 0; i < products.size(); i++) {
				productDTOs.add(modelMapper.map(products.get(i), ProductDTO.class));
			}
			return productDTOs;
		}
		return productDTOs;
	}
	@RequestMapping(value = "/getListProductByCategory/{category_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ProductDTO> getListProductByCategory(@PathVariable Integer category_id) {
		ModelMapper modelMapper = new ModelMapper();
		List<ProductDTO> ProductDTOs =new ArrayList<ProductDTO>();
		Category category = new Category();
		category.setCategory_id(category_id);
		List<Product> entitis =  this.productServiceImpl.getProductByCategory(category);
				if (entitis.size() > 0) {
			
			for (int i = 0; i < entitis.size(); i++) {
				ProductDTOs.add(modelMapper.map(entitis.get(i), ProductDTO.class));
			}
			return ProductDTOs;             
		}
		return ProductDTOs;
	}
}

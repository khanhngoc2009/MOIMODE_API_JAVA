package com.it15306.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.it15306.dto.OptionDTO;
import com.it15306.dto.OptionValueDTO;
import com.it15306.dto.ProductDTO;
import com.it15306.dto.product.DataCreateProductDtos;
import com.it15306.entities.Options;
import com.it15306.entities.OptionValue;
import com.it15306.entities.Product;
import com.it15306.servicesImpl.OptionProductServiceImpl;
import com.it15306.servicesImpl.OptionValueServiceImpl;
import com.it15306.servicesImpl.ProductServiceImpl;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
@RequestMapping("/miemode_api/v1")
public class AdminProduct {

	@Autowired
	private ProductServiceImpl productServiceImpl;
//	@Autowired
//	private OptionProductServiceImpl optionProductServiceImpl;
//	@Autowired
//	private OptionValueServiceImpl optionValueServiceImpl;
	
	@RequestMapping(value = "/admin/createProduct", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<OptionDTO> createProduct(@RequestBody DataCreateProductDtos body) {
		
		
		return body.getOptions();
	}
	
	@RequestMapping(value = "/admin/getAllProducts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ProductDTO> getAllProducts() {
		ModelMapper modelMapper = new ModelMapper();
		
		List<Product> prs = this.productServiceImpl.getAllProductsAdmin();
		
		List<ProductDTO> productDTOs =new ArrayList<ProductDTO>();
		if (prs.size() > 0) {
			for (int i = 0; i < prs.size(); i++) {
				ProductDTO prDto = (modelMapper.map(prs.get(i), ProductDTO.class));
				prDto.setCategory_id(prs.get(i).getCategory().getCategory_id());
				productDTOs.add(prDto);
			}
			return productDTOs;
		}
		
		return null;
	}
}

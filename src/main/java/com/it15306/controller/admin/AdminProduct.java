package com.it15306.controller.admin;

import java.util.ArrayList;
import java.util.Date;
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

import com.it15306.config.DataResponseList;
import com.it15306.dto.category.CategoryDTO;
import com.it15306.dto.option.OptionDTO;
import com.it15306.dto.option.OptionValueDTO;
import com.it15306.dto.product.DataCreateProductDtos;
import com.it15306.dto.product.ProductDTO;
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
	
	@RequestMapping(value = "/admin/getAllProducts/{page}/{take}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public DataResponseList<ProductDTO> getAllProducts(@PathVariable int page,@PathVariable int take) {
		ModelMapper modelMapper = new ModelMapper();
		DataResponseList<ProductDTO> data = new DataResponseList<ProductDTO>();
		try {
			List<Product> prs = this.productServiceImpl.getAllProductsAdmin(page, take);
			
			List<ProductDTO> productDTOs =new ArrayList<ProductDTO>();
			if (prs.size() > 0) {
				for (int i = 0; i < prs.size(); i++) {
					ProductDTO prDto = (modelMapper.map(prs.get(i), ProductDTO.class));
					prDto.setCategory_id(prs.get(i).getCategory().getId());
					productDTOs.add(prDto);
				}
			}
			data.setCode(200);
			data.setCount(prs.size());
			data.setListData(productDTOs);
			data.setMessage("Success");
		} catch (Exception e) {
			data.setCode(500);
			data.setMessage("Fail");
		}
		return data;
	}
}

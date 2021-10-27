package com.it15306.controller.admin;

import java.util.ArrayList;
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
import com.it15306.entities.Product;
import com.it15306.servicesImpl.ProductServiceImpl;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
@RequestMapping("/miemode_api/v1")
public class AdminProduct {

	@Autowired
	private ProductServiceImpl productServiceImpl;

	
	@RequestMapping(value = "/admin/createProduct", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<OptionDTO> createProduct(@RequestBody DataCreateProductDtos body) {
		
		return body.getOptions();
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
	
	
}

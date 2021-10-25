package com.it15306.controller.client;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.it15306.config.DataResponseList;
import com.it15306.dto.CategoryDTO;
import com.it15306.dto.ProvinceDTO;
import com.it15306.dto.UserDTO;
import com.it15306.entities.Category;
import com.it15306.entities.Province;
import com.it15306.entities.User;
import com.it15306.servicesImpl.CategoryProductServiceImpl;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
@RequestMapping("/miemode_api/v1")
public class CustomerCategory {
	@Autowired
	private CategoryProductServiceImpl categoryProductServiceImpl;
	
	@RequestMapping(value = "/getListCategoryParent/{page}/{take}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public DataResponseList<CategoryDTO> getListCategoryParent(@PathVariable Integer page,@PathVariable Integer take ) {
		ModelMapper modelMapper = new ModelMapper();
		DataResponseList<CategoryDTO> data = new DataResponseList<CategoryDTO>();
		try {
			List<Category> Category = this.categoryProductServiceImpl.getAllCategoryByType(1,page,take);
			List<CategoryDTO> categoryDTOs =new ArrayList<CategoryDTO>();
			if (Category.size() > 0) {
				
				for (int i = 0; i < Category.size(); i++) {
					categoryDTOs.add(modelMapper.map(Category.get(i), CategoryDTO.class));
				}
			}
			data.setCode(200);
			data.setCount(Category.size());
			data.setListData(categoryDTOs);
			data.setMessage("Success");
		} catch (Exception e) {
			// TODO: handle exception
			data.setCode(500);
			data.setMessage("Fail");
		}
		return data;
	}
	
	@RequestMapping(value = "/getListCategoryChildren/{category_parent}/{page}/{take}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public DataResponseList<CategoryDTO> getListCategoryChildren(@PathVariable Integer category_parent,@PathVariable Integer page,@PathVariable Integer take) {
		ModelMapper modelMapper = new ModelMapper();
		DataResponseList<CategoryDTO> data = new DataResponseList<CategoryDTO>();
		try {
			List<Category> Category = this.categoryProductServiceImpl.getAllCategoryByCategory(category_parent,1,page,take);
			List<CategoryDTO> categoryDTOs =new ArrayList<CategoryDTO>();
			if (Category.size() > 0) {
				
				for (int i = 0; i < Category.size(); i++) {
					categoryDTOs.add(modelMapper.map(Category.get(i), CategoryDTO.class));
				}
			}
			data.setCode(200);
			data.setCount(Category.size());
			data.setListData(categoryDTOs);
			data.setMessage("Success");
		} catch (Exception e) {
			// TODO: handle exception
			data.setCode(500);
			data.setMessage("Fail");
		}
		return data;
	}
}

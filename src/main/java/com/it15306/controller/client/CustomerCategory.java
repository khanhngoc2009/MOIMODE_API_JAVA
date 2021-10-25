package com.it15306.controller.client;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.it15306.config.DataResponseList;
import com.it15306.dto.CategoryDTO;
import com.it15306.dto.PageDto;
import com.it15306.dto.ProvinceDTO;
import com.it15306.dto.UserDTO;
import com.it15306.dto.category.PageCategoryParent;
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
	
	@RequestMapping(value = "/getListCategoryParent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public DataResponseList<CategoryDTO> getListCategoryParent(@RequestBody PageDto dto) {
		ModelMapper modelMapper = new ModelMapper();
		DataResponseList<CategoryDTO> data = new DataResponseList<CategoryDTO>();
		try {
			List<Category> Category = this.categoryProductServiceImpl.getAllCategoryByType(1,dto.getPage(),dto.getTake());
			List<CategoryDTO> categoryDTOs =new ArrayList<CategoryDTO>();
			if (Category.size() > 0) {
				
				for (int i = 0; i < Category.size(); i++) {
					CategoryDTO caDto= modelMapper.map(Category.get(i), CategoryDTO.class);
					caDto.setId(Category.get(i).getCategory_id());
					caDto.setImage(Category.get(i).getUrl_image());
					categoryDTOs.add(caDto);
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
	
	@RequestMapping(value = "/getListCategoryChildren", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public DataResponseList<CategoryDTO> getListCategoryChildren(@RequestBody PageCategoryParent dto) {
		ModelMapper modelMapper = new ModelMapper();
		DataResponseList<CategoryDTO> data = new DataResponseList<CategoryDTO>();
		try {
			List<Category> Category = this.categoryProductServiceImpl.getAllCategoryByCategory(dto.getCategory_id(),2,dto.getPage(),dto.getTake());
			List<CategoryDTO> categoryDTOs =new ArrayList<CategoryDTO>();
			if (Category.size() > 0) {
				for (int i = 0; i < Category.size(); i++) {
					CategoryDTO caDto= modelMapper.map(Category.get(i), CategoryDTO.class);
					caDto.setId(Category.get(i).getCategory_id());
					caDto.setImage(Category.get(i).getUrl_image());
					categoryDTOs.add(caDto);
				}
			}
			data.setCode(200);
			data.setCount(Category.size());
			data.setListData(categoryDTOs);
			data.setMessage("Success");
		} catch (Exception e) {
			data.setCode(500);
			data.setMessage("Fail");
		}
		return data;
	}
}

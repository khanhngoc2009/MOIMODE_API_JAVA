package com.it15306.controller.client;

import com.it15306.config.DataResponseList;
import com.it15306.dto.PageDto;
import com.it15306.dto.category.CategoryDTO;
import com.it15306.dto.category.PageCategoryParent;
import com.it15306.entities.Category;
import com.it15306.servicesImpl.CategoryProductServiceImpl;
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



@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200","http://35.198.241.56" })
@RestController
@RequestMapping("/miemode_api/v1")
public class CustomerCategory {
	@Autowired
	private CategoryProductServiceImpl categoryProductServiceImpl;
	
	@RequestMapping(value = "/category-parent/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> getListCategoryParent(@RequestBody PageDto dto) {
		ModelMapper modelMapper = new ModelMapper();
		DataResponseList<CategoryDTO> data = new DataResponseList<CategoryDTO>();
		try {
			List<Category> Category = this.categoryProductServiceImpl.getAllCategoryByType(1,dto.getPage(),dto.getTake());
			List<CategoryDTO> categoryDTOs =new ArrayList<CategoryDTO>();
			if (Category.size() > 0) {
				
				for (int i = 0; i < Category.size(); i++) {
					CategoryDTO caDto= modelMapper.map(Category.get(i), CategoryDTO.class);
					caDto.setId(Category.get(i).getId());
					caDto.setImage(Category.get(i).getImage());
					categoryDTOs.add(caDto);
				}
			}
			data.setCode(200);
			data.setCount(Category.size());
			data.setListData(categoryDTOs);
			data.setMessage("Success");
			return new ResponseEntity<>(data,HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			data.setCode(HttpStatus.FAILED_DEPENDENCY.value());
			data.setMessage("Fail");
			return new ResponseEntity<>(data,HttpStatus.FAILED_DEPENDENCY);
		}
	}
	
	@RequestMapping(value = "/category-children/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> getListCategoryChildren(@RequestBody PageCategoryParent dto) {
		ModelMapper modelMapper = new ModelMapper();
		DataResponseList<CategoryDTO> data = new DataResponseList<CategoryDTO>();
		try {
			List<Category> Category = this.categoryProductServiceImpl.getAllCategoryByCategory(dto.getCategory_id(),2,dto.getPage(),dto.getTake());
			List<CategoryDTO> categoryDTOs =new ArrayList<CategoryDTO>();
			if (Category.size() > 0) {
				for (int i = 0; i < Category.size(); i++) {
					CategoryDTO caDto= modelMapper.map(Category.get(i), CategoryDTO.class);
					caDto.setId(Category.get(i).getId());
					caDto.setImage(Category.get(i).getImage());
					categoryDTOs.add(caDto);
				}
			}
			data.setCode(200);
			data.setCount(Category.size());
			data.setListData(categoryDTOs);
			data.setMessage("Success");
			return new ResponseEntity<>(data,HttpStatus.OK);
		} catch (Exception e) {
			data.setCode(HttpStatus.FAILED_DEPENDENCY.value());
			data.setMessage("Fail");
			return new ResponseEntity<>(data,HttpStatus.FAILED_DEPENDENCY);
		}
		
	}
}

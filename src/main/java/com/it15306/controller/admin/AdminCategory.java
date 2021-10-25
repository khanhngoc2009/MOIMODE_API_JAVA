package com.it15306.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.it15306.config.DataResponse;
import com.it15306.dto.CategoryDTO;
import com.it15306.services.CategoryService;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
@RequestMapping("/miemode_api/v1")
public class AdminCategory {
	
	@Autowired 
	CategoryService categoryService;
	
	
	@RequestMapping(value = "/admin/createCategory", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public DataResponse<CategoryDTO> createCategory(@RequestBody CategoryDTO body) {
		DataResponse<CategoryDTO> rp=  new DataResponse<CategoryDTO>();
		try {
			
			rp.setCode(200);
			rp.setMessage("Success");	
			rp.setData(categoryService.CreateCategory(body));
			return rp;
		} catch (Exception e) {
			rp.setCode(500);
			rp.setMessage("Fail");	
			return rp;
		}
		
	}
	
	@RequestMapping(value = "/admin/updateCategory", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public DataResponse<CategoryDTO> updateCategory(@RequestBody CategoryDTO body) {
		DataResponse<CategoryDTO> rp=  new DataResponse<CategoryDTO>();
		try {
			
			rp.setCode(200);
			rp.setMessage("Success");	
			rp.setData(categoryService.updateCategory(body));
			return rp;
		} catch (Exception e) {
			rp.setCode(500);
			rp.setMessage("Fail");	
			return rp;
		}
		
	}
}

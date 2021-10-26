package com.it15306.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.it15306.config.DataResponse;
import com.it15306.config.DataResponseList;
import com.it15306.dto.category.CategoryDTO;
import com.it15306.dto.category.PageCategoryDTO;
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
			System.out.println("-----------createCategory1----------");
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
			System.out.println("-----------updateCategory1----------");
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
	
	@DeleteMapping("/admin/category/{id}")
	@ResponseBody
	public DataResponse<Integer> delete(@PathVariable("id") Integer id) {
		DataResponse<Integer> rp=  new DataResponse<Integer>();
		Integer idrp =categoryService.delete(id);
		try {
			if(idrp != null) {
			rp.setCode(200);
			rp.setMessage("Success");
			rp.setData(idrp);
			}else {
				rp.setCode(404);
				rp.setMessage("Fail");
			}
			return rp;
		} catch (Exception e) {
			rp.setCode(500);
			rp.setMessage("Fail");	
			return rp;
		}
	}
	
	
	@RequestMapping(value = "/admin/ListCategoryParent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public DataResponseList<CategoryDTO> getListCategoryParent(@RequestBody PageCategoryDTO pagedata) {
		DataResponseList<CategoryDTO> data = new DataResponseList<CategoryDTO>();
		List<CategoryDTO> lis=categoryService.getAllCategoryParent();
		try {
			if(lis.size() > 0) {
				data.setCode(200);
				data.setMessage("Success");
				data.setListData(lis);
				}else {
					data.setCode(500);
					data.setMessage("Fail");
				}
				return data;
		
		} catch (Exception e) {

			data.setCode(500);
			data.setMessage("Fail");
			return data;
		}
	}
	
	@RequestMapping(value = "/admin/ListCategoryChildent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public DataResponseList<CategoryDTO> ListCategoryChildent(@RequestBody PageCategoryDTO pagedata) {
		DataResponseList<CategoryDTO> data = new DataResponseList<CategoryDTO>();
		List<CategoryDTO> lis=categoryService.getAllCategoryChildent();
		try {
			if(lis.size() > 0) {
				data.setCode(200);
				data.setMessage("Success");
				data.setListData(lis);
				}else {
					data.setCode(500);
					data.setMessage("Fail");
				}
				return data;
		
		} catch (Exception e) {

			data.setCode(500);
			data.setMessage("Fail");
			return data;
		}
	}
}

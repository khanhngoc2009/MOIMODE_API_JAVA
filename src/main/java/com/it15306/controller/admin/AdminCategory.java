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
import com.it15306.dto.category.ResponCategoryParent;
import com.it15306.services.CategoryService;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
@RequestMapping("/miemode_api/v1")
public class AdminCategory {
	
	@Autowired 
	CategoryService categoryService;
	
	
	@RequestMapping(value = "/admin/createCategory", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<DataResponse<CategoryDTO>> createCategory(@RequestBody CategoryDTO body) {
		DataResponse<CategoryDTO> rp=  new DataResponse<CategoryDTO>();
		try {

			rp.setMessage("Success");	
			rp.setData(categoryService.CreateCategory(body));
			return ResponseEntity.ok(rp);
		} catch (Exception e) {

			rp.setMessage("Fail");	
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	@RequestMapping(value = "/admin/updateCategory", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public DataResponse<CategoryDTO> updateCategory(@RequestBody CategoryDTO body) {
		DataResponse<CategoryDTO> rp=  new DataResponse<CategoryDTO>();
		try {
			rp.setMessage("Success");	
			rp.setData(categoryService.updateCategory(body));
			return rp;
		} catch (Exception e) {
			rp.setMessage("Fail");	
			return rp;
		}
		
	}
	
	@DeleteMapping("/admin/category/{id}")
	@ResponseBody
	public ResponseEntity<DataResponse<Integer>> delete(@PathVariable("id") Integer id) {
		DataResponse<Integer> rp=  new DataResponse<Integer>();
		Integer idrp =categoryService.delete(id);
		try {
			if(idrp != null) {
			rp.setMessage("Success");
			rp.setData(idrp);
			return ResponseEntity.ok(rp);
			}
		} catch (Exception e) {
			rp.setMessage("Fail");	
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.noContent().build();
	}
	
	
	@RequestMapping(value = "/admin/ListCategoryParent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<DataResponseList<ResponCategoryParent>> getListCategoryParent(@RequestBody PageCategoryDTO pagedata) {
		DataResponseList<ResponCategoryParent> data = new DataResponseList<ResponCategoryParent>();
		List<CategoryDTO> lis=categoryService.getAllCategoryParent();
		List<ResponCategoryParent> lis2=new ArrayList<ResponCategoryParent>();
		lis.forEach(ct ->{
			ResponCategoryParent parent =new ResponCategoryParent();
			parent.setId(ct.getId());
			parent.setName(ct.getName());
			parent.setCreate_date(ct.getCreate_date());
			parent.setStatus(ct.getStatus());
			parent.setType(ct.getType());
			parent.setDescription(ct.getDescription());
			parent.setNumberOfSubcategories(categoryService.countCategoryParentById(ct.getId()));
			lis2.add(parent);
		});
		try {
			if(lis.size() > 0) {
				data.setCount(categoryService.countCategoryParent());
				data.setMessage("Success");
				data.setListData(lis2);
				return  ResponseEntity.ok(data);
				}else {
					data.setMessage("Fail");
					return  ResponseEntity.noContent().build();
				}
	
		
		} catch (Exception e) {

			data.setCode(500);
			data.setMessage("Fail");
			return  ResponseEntity.badRequest().build();
		}
	}
	
	@RequestMapping(value = "/admin/ListCategoryChildent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<DataResponseList<CategoryDTO>> ListCategoryChildent(@RequestBody PageCategoryDTO pagedata) {
		DataResponseList<CategoryDTO> data = new DataResponseList<CategoryDTO>();
		List<CategoryDTO> lis=categoryService.getAllCategoryChildent();
		try {
			if(lis.size() > 0) {
				data.setCount(categoryService.countCategoryChildent());
				data.setMessage("Success");
				data.setListData(lis);
				return ResponseEntity.ok(data);
				}else {
					data.setMessage("Fail");
					return ResponseEntity.noContent().build();
				}
				
		
		} catch (Exception e) {

			data.setMessage("Fail");
			return ResponseEntity.badRequest().build();
		}
	}
	
	
	
}

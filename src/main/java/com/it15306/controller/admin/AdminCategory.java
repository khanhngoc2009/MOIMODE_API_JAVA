package com.it15306.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.it15306.config.DataResponse;
import com.it15306.config.DataResponseList;
import com.it15306.dto.idBody;
import com.it15306.dto.category.CategoryDTO;
import com.it15306.dto.category.PageCategoryDTO;
import com.it15306.dto.category.ResponCategoryChildent;
import com.it15306.dto.category.ResponCategoryParent;
import com.it15306.dto.category.categoryParent;
import com.it15306.repository.ProductRepository;
import com.it15306.services.CategoryService;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200","http://35.198.241.56" })
@RestController
@RequestMapping("/miemode_api/v1")
public class AdminCategory {
	
	@Autowired 
	CategoryService categoryService;
	
	@Autowired
	ProductRepository  productRepository;
	
	@Autowired
	ModelMapper modelMapper;
	@RequestMapping(value = "/admin/category/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<DataResponse<CategoryDTO>> createCategory(@Validated  @RequestBody CategoryDTO body, BindingResult bindingResult) {
		DataResponse<CategoryDTO> rp=  new DataResponse<CategoryDTO>();
		boolean check = bindingResult.hasErrors();
		try {
			if(!check) {
			System.out.print("-------"+body.getName());
			CategoryDTO dto =categoryService.CreateCategory(body);
			if(!dto.equals(null)) {
			rp.setMessage("Success");	
			rp.setData(dto);
			return ResponseEntity.ok(rp);
			}
		}
		} catch (Exception e) {

			rp.setMessage("Fail");	
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.badRequest().build();
		
	}
	
	@RequestMapping(value = "/admin/category/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<DataResponse<CategoryDTO>> updateCategory( @Validated  @RequestBody CategoryDTO body , BindingResult bindingResult) {
		DataResponse<CategoryDTO> rp=  new DataResponse<CategoryDTO>();
		try {
			boolean check = bindingResult.hasErrors();
			
			if(!check) {
				if(body.getId() != null) {
				rp.setMessage("Success");	
				rp.setData(categoryService.updateCategory(body));
				return ResponseEntity.ok(rp);
				}
			}
			
		} catch (Exception e) {
			rp.setMessage("Fail");	
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.badRequest().build();
	}
	
	@PostMapping("/admin/category/delete")
	@ResponseBody
	public ResponseEntity<DataResponse<Integer>> delete(@RequestBody idBody data) {
		System.out.println(data.getId());
		DataResponse<Integer> rp=  new DataResponse<Integer>();
		Integer idrp =categoryService.delete(data.getId());
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
	
	
	@RequestMapping(value = "/admin/category-parent/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<DataResponseList<ResponCategoryParent>> getListCategoryParent(@RequestBody PageCategoryDTO pagedata) {
		DataResponseList<ResponCategoryParent> data = new DataResponseList<ResponCategoryParent>();
		List<CategoryDTO> lis = new ArrayList<CategoryDTO>();
//		if(pagedata.getEndTime() == "" || pagedata.getStartTime().equals("")) {
//			lis=categoryService.getAllCategoryParent(pagedata);
//		}else {
			lis=categoryService.getAllCategoryPage(pagedata, 1);
	//	}
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
				Long n=categoryService.totalement();
				Integer sobanghi=n.intValue();
				data.setCount(sobanghi);
				data.setMessage("Success");
				data.setListData(lis2);
				return  ResponseEntity.ok(data);
				}else {

					 return  ResponseEntity.ok(data);
				}
	
		
		} catch (Exception e) {

			data.setCode(500);
			data.setMessage("Fail");
			return  ResponseEntity.badRequest().build();
		}
	}
	
	@RequestMapping(value = "/admin/category-children/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<DataResponseList<ResponCategoryChildent>> ListCategoryChildent(@RequestBody PageCategoryDTO pagedata) {
		DataResponseList<ResponCategoryChildent> data = new DataResponseList<ResponCategoryChildent>();
		List<CategoryDTO> lisdata=new ArrayList<CategoryDTO>();
		List<ResponCategoryChildent> lisdatarespon=new ArrayList<ResponCategoryChildent>();
//		if(pagedata.getEndTime() == "" || pagedata.getStartTime().equals("")|| pagedata.getStartTime().equals(null)) {
//			lisdata=categoryService.getAllCategoryChildent(pagedata);
//		}else {
			lisdata=categoryService.getAllCategoryPage(pagedata, 2);
		//}
		
		try {
			lisdata.forEach(ct->{
				ResponCategoryChildent cd=new ResponCategoryChildent();
				//categoryParent parent=	modelMapper.map(categoryService.getCategoryByID(ct.getCategory_parent_id()), categoryParent.class);
				cd.setId(ct.getId());
				cd.setName(ct.getName());
				cd.setCreate_date(ct.getCreate_date());
				cd.setStatus(ct.getStatus());
				cd.setType(ct.getType());
				cd.setDescription(ct.getDescription());
				try {
					cd.setNumberOfProduct(productRepository.countProductByCategory(ct.getId()));
					cd.setCategoryParent(categoryService.getCategoryByID(ct.getCategory_parent_id()));
				} catch (Exception e) {
					System.out.println("-------id chet-------"+ct.getCategory_parent_id());
					e.printStackTrace();
				}
				
				lisdatarespon.add(cd);
			});
			if(lisdatarespon.size() > 0) {
				Long n=categoryService.totalement();
				Integer sobanghi=n.intValue();
				data.setCount(sobanghi);
				data.setMessage("Success");
				data.setListData(lisdatarespon);
				return ResponseEntity.ok(data);
				}else {
					
					return  ResponseEntity.ok(data);
				}
				
		
		} catch (Exception e) {

			data.setMessage("Fail");
			return ResponseEntity.badRequest().build();
		}
	}
	
	
	
}

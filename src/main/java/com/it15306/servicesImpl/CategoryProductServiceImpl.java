package com.it15306.servicesImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.bcel.Const;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.it15306.dto.WarehouseDTO;
import com.it15306.dto.category.CategoryDTO;
import com.it15306.dto.category.PageCategoryDTO;
import com.it15306.dto.category.categoryParent;
import com.it15306.entities.Category;
import com.it15306.entities.Product;
import com.it15306.entities.Warehouse;
import com.it15306.repository.CategoryRepository;
import com.it15306.repository.ProductRepository;
import com.it15306.services.CategoryService;



@Service("CategoryProductServiceImpl")
public class CategoryProductServiceImpl implements CategoryService{
	private static Long totalElement;
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	ProductRepository productRepository;
	
	@Override
	public List<Category> getAllCategoryByType(int type,int page,int take) {
		Pageable paging =  PageRequest.of(page, take);
		 
        Page<Category> pagedResult = categoryRepository.findByTypePage(type,paging);
        System.out.print(pagedResult.getContent().size());
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
        	return new ArrayList<Category>();
		}
	}

	@Override
	public List<Category> getAllCategoryByCategory(int category_parent_id, int type,int page,int take) {
		// TODO Auto-generated method stub
		Pageable paging =  PageRequest.of(page, take);
		 
        Page<Category> pagedResult = categoryRepository.findByCategoryParentId(category_parent_id, type,paging);
        System.out.print(pagedResult.getContent().size());
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
        	return new ArrayList<Category>();
		}
		
	}

	@Override
	public List<Product> getListProductByCategory(String category_id, int type) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public Category saveCategory(Category category) {
		// TODO Auto-generated method stub
		return categoryRepository.save(category);
	}

	

	@Override
	public List<Category> getAllCategoryByCreateDate(Date ngay_bat_dau, Date ngay_ket_thuc) {
		// TODO Auto-generated method stub
		return categoryRepository.findByCreateDate(ngay_bat_dau, ngay_ket_thuc);
	}
	
//	public List<Category> getListCategory() {
//		return categoryRepository.findAll();
//		
//	}

	
	@Override
	public List<CategoryDTO> getAllCategoryParent(PageCategoryDTO data) {
		if(data.getPage() == null) {
			data.setPage(0);
		}
		if(data.getTake() == null || data.getTake() == 0) {
			data.setTake(10);
		}
		if(data.getStartTime()== null) {
			data.setStartTime(startDate());
		}
		if(data.getEndTime()== null) {
			data.setEndTime(endDate());
		}
		Pageable paging =  PageRequest.of(data.getPage(), data.getTake());
		
		Page<Category> enti= categoryRepository.selectAllCategoryParent(paging);

		List<CategoryDTO> listdto=new ArrayList<CategoryDTO>();
		if(enti.getContent().size() > 0) {
			enti.getContent().forEach(d -> {
				CategoryDTO n=mapToModel(d, null);				
				listdto.add(n);
			});
		}
		return listdto;
	}

	@Override
	public List<CategoryDTO> getAllCategoryChildent(PageCategoryDTO data) {
		if(data.getPage() == null) {
			data.setPage(0);
		}
		if(data.getTake() == null || data.getTake() == 0) {
			data.setTake(10);
		}
		if(data.getStartTime()== null) {
			data.setStartTime(startDate());
		}
		if(data.getEndTime()== null) {
			data.setEndTime(endDate());
		}
		System.out.println("check date"+startDate());
		System.out.println("check date"+endDate());
		Pageable paging =  PageRequest.of(data.getPage(), data.getTake());
		Page<Category> enti= categoryRepository.findByType(2,paging);
		List<CategoryDTO> listdto=new ArrayList<CategoryDTO>();
		if(enti.getContent().size() > 0) {
			enti.forEach(d -> {
				CategoryDTO n=mapToModel(d, null);				
				listdto.add(n);
			});
		}
		return listdto;
	}

	@Override
	public CategoryDTO CreateCategory(CategoryDTO data) {
		System.out.println("-----------CreateCategory2----------");
		String URI="http://34.87.157.20:8089/storages/";
		try {
			if(checkcate(data)) {
				if(data.getCategory_parent_id() != null) {
					if(data.getType()==1) {
						data.setCategory_parent_id(null);
					}else {
						data.setType(2);
					}

				}else {				
					data.setCategory_parent_id(null);			
					data.setType(1);
				}
				if(data.getImage() != null) {
					data.setImage(data.getImage());
				}
				if(data.getType() == 1) data.setCategory_parent_id(null);
				Category enti = modelMapper.map(data, Category.class);
				categoryRepository.save(enti);
				data.setId(enti.getId());
				return data;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	private Boolean checkcate(CategoryDTO data) {
		System.out.println("check ---");
		if(data.getType() == 2) {
			Category 	 category = categoryRepository.SELECT_CATEGORY_BY_ID_AND_TYPE(data.getCategory_parent_id(), 1);
			if(category == null ||  !(category.getType() == 1)) return false;
			System.out.println("check co cha false");
		}
		return true;
	}
	@Override
	public CategoryDTO updateCategory(CategoryDTO data) {
		System.out.println("-----------updateCategory2----------");
		Optional<Category> optional= categoryRepository.findById(data.getId());
		try {
			if(optional.isPresent()) {
				Category enti=optional.get();
				Category ct=mapToEnyities(data, enti);
				categoryRepository.save(ct);
			}
			return data;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public CategoryDTO mapToModel(Category enti, CategoryDTO model){
		if (model == null)
			model = new CategoryDTO();
		model = modelMapper.map(enti, CategoryDTO.class);
		return model;
	}

	public Category mapToEnyities(CategoryDTO model, Category enti){
		if (enti == null)
			enti = new Category();
		enti = modelMapper.map(model, Category.class);
		return enti;
	}

	@Override
	public Integer delete(Integer id) {
				
		Optional<Category> optional= categoryRepository.findById(id);
		if(optional.isPresent()) {
		//lay ra category theo id	
			Category enti=optional.get();
		if(checkDelete(id, enti.getCategory_parent_id())) {		
			Category category=optional.get();
			categoryRepository.delete(category);
			return category.getId();
		}
		}
		return null;
	}
	public Boolean checkDelete(Integer idParent,Integer idChildren) {
		System.out.println("checkkkkkk");
		List<Product> lpro =	productRepository.findByCategoryIds(idChildren);
		System.out.println("checkkkkkk2222");
		//check theo san pham
		if(lpro.size() > 0) {
			return false;
		}else{
			Integer count =	this.countCategoryParentById(Integer.valueOf(idParent));
			if(count > 0) {
				return false;
			}else {
				return true;
			}			
		}
	}

	@Override
	public Integer countCategoryParent() {
		
		return categoryRepository.countByTypes(1);
	}

	@Override
	public Integer countCategoryChildent() {
		return categoryRepository.countByTypes(2);
	}

	@Override
	public Integer countCategoryParentById(Integer category_parent_id) {

		return categoryRepository.countCategoryParentByID(category_parent_id);
	}

	@Override
	public List<CategoryDTO> getAllCategoryPage(PageCategoryDTO data,Integer type) {
		try {
			String status;
			String parentId;
		
		if(data.getStatus() == null) {
			data.setStatus(null);
		}
		if(data.getPage() == null) {
			data.setPage(0);
		}
		if(data.getTake() == null || data.getTake() == 0) {
			data.setTake(10);
		}
		if(data.getStartTime() == null || data.getStartTime() == "") {
			data.setStartTime(startDate());
		}
		if(data.getEndTime()== null || data.getEndTime()== "") {
			data.setEndTime(endDate());
		}
		System.out.println(data.getName());
		Pageable paging =  PageRequest.of(data.getPage(), data.getTake());
		
		if(data.getName()== null) data.setName("");
		if(data.getParentId()== null) {
			parentId="";
		}else{
			parentId=String.valueOf(data.getParentId());
		};
		if(data.getStatus()== null || data.getStatus().equals("")) { 
			status="";
		}else {
			status=String.valueOf(data.getStatus());
		}
		Page<Category> pc2;
		if(data.getParentId() == null || type==1) {
			System.out.println("111111");
			 pc2 = categoryRepository.selectAllCategoryParentPage(data.getName(),  data.getStartTime(), data.getEndTime(),status , type, paging);
		}else {
			System.out.println("222222222");
			pc2 = categoryRepository.selectAllCategoryParentchidentPage(data.getName(),parentId,  data.getStartTime(), data.getEndTime(),status , type, paging);
		}
		
				
		List<Category> lst2=pc2.getContent();
		List<CategoryDTO> listdto=new ArrayList<CategoryDTO>();
		if(lst2.size() > 0) {
			lst2.forEach(d -> {
				CategoryDTO n=mapToModel(d, null);				
				listdto.add(n);
			});
		}
		totalElement=pc2.getTotalElements();
		return listdto;
		
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	@Override
	public categoryParent getCategoryByID(Integer category_id) {
		//categoryRepository.SelectCategoryParentByID(category_id);
		
		return modelMapper.map(categoryRepository.SelectCategoryParentByID(category_id), categoryParent.class);
	}
	public String startDate() {
		return categoryRepository.START_DATE();
	}
	public String endDate() {
		return categoryRepository.END_DATE();
	}
	
	
	
	public Category getCayegoryChildrenById(Integer id) {
		
		return categoryRepository.findById(id).get();
	}
	public Long getTototelement(Long sobanghi) {
		return sobanghi;
	}

	@Override
	public Long totalement() {
		return totalElement;
	}
}

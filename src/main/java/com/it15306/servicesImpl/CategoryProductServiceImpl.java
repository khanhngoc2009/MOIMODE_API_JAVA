package com.it15306.servicesImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.it15306.dto.CategoryDTO;
import com.it15306.dto.WarehouseDTO;
import com.it15306.entities.Category;
import com.it15306.entities.Product;
import com.it15306.entities.Warehouse;
import com.it15306.repository.CategoryRepository;
import com.it15306.services.CategoryService;



@Service("CategoryProductServiceImpl")
public class CategoryProductServiceImpl implements CategoryService{
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	
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
	public List<CategoryDTO> getAllCategoryParent() {
		List<Category> enti= categoryRepository.selectAllCategoryParent();
		List<CategoryDTO> listdto=new ArrayList<CategoryDTO>();
		if(enti.size() > 0) {
			enti.forEach(d -> {
				CategoryDTO n=mapToModel(d, null);				
				listdto.add(n);
			});
		}
		return listdto;
	}

	@Override
	public List<CategoryDTO> getAllCategoryChildent() {
		List<Category> enti= categoryRepository.findByType(2);
		List<CategoryDTO> listdto=new ArrayList<CategoryDTO>();
		if(enti.size() > 0) {
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
		try {
			Category enti = modelMapper.map(data, Category.class);
			categoryRepository.save(enti);
			data.setId(enti.getId());
			return data;
		} catch (Exception e) {
			return null;
		}
		
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
			Category category=optional.get();
			categoryRepository.delete(category);
			return category.getId();
		}
		return null;
	}

	@Override
	public Integer countCategoryParent() {
		
		return categoryRepository.countByTypes(1);
	}

	@Override
	public Integer countCategoryChildent() {
		return categoryRepository.countByTypes(2);
	}
}

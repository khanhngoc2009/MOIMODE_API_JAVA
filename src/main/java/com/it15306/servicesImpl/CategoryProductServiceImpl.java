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
	public void delete(Category category) {
		// TODO Auto-generated method stub
		categoryRepository.delete(category);
		
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
		
		return null;
	}

	@Override
	public List<CategoryDTO> getAllCategoryChildent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CategoryDTO CreateCategory(CategoryDTO data) {
		Category enti = modelMapper.map(data, Category.class);
		categoryRepository.save(enti);
		data.setCategory_id(enti.getCategory_id());
		return data;
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO data) {
		Optional<Category> optional= categoryRepository.findById(data.getCategory_id());
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
	
	public CategoryDTO mapToModel(Category enti, CategoryDTO model)
			throws IllegalAccessException, InvocationTargetException {
		if (model == null)
			model = new CategoryDTO();
		model = modelMapper.map(enti, CategoryDTO.class);
		return model;
	}

	public Category mapToEnyities(CategoryDTO model, Category enti)
			throws IllegalAccessException, InvocationTargetException {
		if (enti == null)
			enti = new Category();
		enti = modelMapper.map(model, Category.class);
		return enti;
	}
}

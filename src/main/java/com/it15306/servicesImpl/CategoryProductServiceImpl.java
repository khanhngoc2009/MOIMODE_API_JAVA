package com.it15306.servicesImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.it15306.entities.Category;
import com.it15306.entities.Product;
import com.it15306.repository.CategoryRepository;
import com.it15306.services.CategoryService;



@Service("CategoryProductServiceImpl")
public class CategoryProductServiceImpl implements CategoryService{
	@Autowired
	private CategoryRepository categoryRepository;
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

}

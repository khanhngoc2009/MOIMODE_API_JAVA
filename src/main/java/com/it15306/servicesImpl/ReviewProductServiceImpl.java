package com.it15306.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.it15306.entities.ReviewProduct;
import com.it15306.repository.ReviewProductRepository;
import com.it15306.services.ReviewProductService;

public class ReviewProductServiceImpl implements ReviewProductService{
	
	@Autowired
	private ReviewProductRepository reviewProductRepository;
	
	@Override
	public List<ReviewProduct> getAllReviewProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReviewProduct getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReviewProduct saveReviewProduct(ReviewProduct ReviewProduct) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

}

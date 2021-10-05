package com.it15306.services;

import java.util.List;

import com.it15306.entities.ReviewProduct;
//import com.it15306.entities.Category;

public interface ReviewProductService {
	List<ReviewProduct> getAllReviewProducts();
	ReviewProduct getById(String id);
	ReviewProduct saveReviewProduct(ReviewProduct ReviewProduct);
	void delete(String id);
}

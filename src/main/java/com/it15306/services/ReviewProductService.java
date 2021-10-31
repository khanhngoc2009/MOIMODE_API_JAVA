package com.it15306.services;

import java.util.List;

import com.it15306.dto.reviewProduct.ReviewProductDTO;
import com.it15306.entities.ReviewProduct;
//import com.it15306.entities.Category;

public interface ReviewProductService {
	List<ReviewProductDTO> getAllReviewProducts();
	ReviewProductDTO getById(String id);
	ReviewProductDTO saveReviewProduct(ReviewProductDTO ReviewProduct);
	Integer delete(Integer id);
}

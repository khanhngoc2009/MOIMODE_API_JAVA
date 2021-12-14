package com.it15306.services;

import java.util.List;

import com.it15306.dto.reviewProduct.BodyReviewProduct;
import com.it15306.dto.reviewProduct.ResponReviewProduct;
import com.it15306.dto.reviewProduct.ReviewProductDTO;

public interface ReviewProductService {
	List<ReviewProductDTO> getAllReviewProducts(BodyReviewProduct data);
	ReviewProductDTO getById(String id);
	ReviewProductDTO saveReviewProduct(ReviewProductDTO ReviewProduct);
	Integer delete(Integer id);
	List<ReviewProductDTO> getAllReviewProductsByProductId(String id);
	ReviewProductDTO create(ResponReviewProduct data);
	
	Integer totalement();
	
	ReviewProductDTO updateTrangThai(String id);
	
	Double getAvgStar(Integer product_id);
}

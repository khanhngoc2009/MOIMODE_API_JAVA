package com.it15306.controller.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.it15306.dto.reviewProduct.ResponReviewProduct;
import com.it15306.dto.reviewProduct.ReviewProductDTO;
import com.it15306.services.ReviewProductService;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200","http://35.198.241.56" })
@RestController
@RequestMapping("/miemode_api/v1")
public class CustomerReviewProduct {
	
	@Autowired
	ReviewProductService reviewProductService;


	@RequestMapping(value = "/review-product/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ReviewProductDTO> createReviewProduct(@RequestBody ResponReviewProduct data) {
		try {
			ReviewProductDTO dto=reviewProductService.create(data);
			return ResponseEntity.ok(dto);
		} catch (Exception e) {
			e.printStackTrace();
			ResponseEntity.badRequest().build();
		}
		
		return ResponseEntity.badRequest().build();	
	}
	
	@RequestMapping(value = "/review-product/list/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<ReviewProductDTO>> listreview(@PathVariable("id") String idProduct) {
		try {
			List<ReviewProductDTO> list=reviewProductService.getAllReviewProductsByProductId(idProduct);
			
				return ResponseEntity.ok(list);					
		} catch (Exception e) {
			e.printStackTrace();
			ResponseEntity.badRequest().build();
		}
		
		
		return ResponseEntity.badRequest().build();	
	}
}

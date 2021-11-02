package com.it15306.controller.admin;

import java.util.ArrayList;
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

import com.it15306.config.DataResponseList;
import com.it15306.dto.reviewProduct.ReviewProductDTO;
import com.it15306.services.ReviewProductService;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
@RequestMapping("/miemode_api/v1")
public class AdminReviewProduct {
	@Autowired
	ReviewProductService reviewProductService;
	
	@RequestMapping(value = "/admin/review-product/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<DataResponseList<ReviewProductDTO>> getListReviewProduct() {
		DataResponseList<ReviewProductDTO> list=new DataResponseList<ReviewProductDTO>();
		List<ReviewProductDTO> list2 = reviewProductService.getAllReviewProducts();
		try {
			if(list2.size() > 0) {
			list.setMessage("ok");
			list.setListData(list2);
			return ResponseEntity.ok(list);
			}
		} catch (Exception e) {
			list.setMessage("no ok");
		}
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/admin/review-product/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Integer> deletereviewProduct(@PathVariable("id") Integer id) {		
		try {
			Integer idrs =	reviewProductService.delete(id);
			if(idrs == null) {
				
			return ResponseEntity.ok(idrs);
			
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.notFound().build();
	}
}

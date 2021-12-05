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
import com.it15306.dto.PageDto;
import com.it15306.dto.idBody;
import com.it15306.dto.reviewProduct.BodyReviewProduct;
import com.it15306.dto.reviewProduct.ReviewProductDTO;
import com.it15306.services.ReviewProductService;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200","http://35.198.241.56" })
@RestController
@RequestMapping("/miemode_api/v1")
public class AdminReviewProduct {
	@Autowired
	ReviewProductService reviewProductService;
	
	@RequestMapping(value = "/admin/review-product/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<DataResponseList<ReviewProductDTO>> getListReviewProduct(@RequestBody BodyReviewProduct data) {
		DataResponseList<ReviewProductDTO> list=new DataResponseList<ReviewProductDTO>();
		List<ReviewProductDTO> list2 = reviewProductService.getAllReviewProducts(data);
		try {
			if(list2.size() > 0) {
			list.setMessage("ok");
			list.setListData(list2);
			list.setCount(reviewProductService.totalement());
			return ResponseEntity.ok(list);
			}
		} catch (Exception e) {
			list.setMessage("no ok");
		}
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/admin/review-product/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Integer> deletereviewProduct(@RequestBody idBody data) {		
		try {
			Integer idrs =	reviewProductService.delete(data.getId());
			if(idrs != null) {
				
			return ResponseEntity.ok(idrs);
			
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@RequestMapping(value = "/admin/review-product/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ReviewProductDTO> updateTrangThai(@RequestBody idBody data) {		
		try {
			if(data.getId() != null) {
			ReviewProductDTO rv =	reviewProductService.updateTrangThai(data.getId()+"");
			if(rv != null) {
				
			return ResponseEntity.ok(rv);
			
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.notFound().build();
	}
}

package com.it15306.servicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it15306.dto.reviewProduct.ReviewProductDTO;
import com.it15306.entities.ReviewProduct;
import com.it15306.repository.ReviewProductRepository;
import com.it15306.services.ReviewProductService;
@Service
public class ReviewProductServiceImpl implements ReviewProductService{
	
	@Autowired
	private ReviewProductRepository reviewProductRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public List<ReviewProductDTO> getAllReviewProducts() {
		List<ReviewProduct> list =	reviewProductRepository.findAll();
		List<ReviewProductDTO> listdto =new ArrayList<ReviewProductDTO>();
		list.forEach(l->{
			ReviewProductDTO rv=new ReviewProductDTO();
			rv = modelMapper.map(l, ReviewProductDTO.class);
			listdto.add(rv);
		});
		
		return listdto;
	}

	@Override
	public ReviewProductDTO getById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReviewProductDTO saveReviewProduct(ReviewProductDTO ReviewProduct) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(Integer id) {
		Optional<ReviewProduct> optional= reviewProductRepository.findById(id);
		
		if(optional.isPresent()) {
			ReviewProduct entity=optional.get();
			reviewProductRepository.delete(entity);
			return entity.getId();
		}
		return 0;
	}
	
	

}

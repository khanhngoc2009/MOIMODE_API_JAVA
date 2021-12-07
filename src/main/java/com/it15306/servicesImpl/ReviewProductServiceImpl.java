package com.it15306.servicesImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.it15306.dto.UserDTO;
import com.it15306.dto.product.ProductDTO;
import com.it15306.dto.reviewProduct.BodyReviewProduct;
import com.it15306.dto.reviewProduct.ResponReviewProduct;
import com.it15306.dto.reviewProduct.ReviewProductDTO;
import com.it15306.entities.Product;
import com.it15306.entities.ReviewProduct;
import com.it15306.entities.User;
import com.it15306.repository.ProductRepository;
import com.it15306.repository.ReviewProductRepository;
import com.it15306.repository.UserRepository;
import com.it15306.services.ReviewProductService;
@Service
public class ReviewProductServiceImpl implements ReviewProductService{
	private static Long totalElement;
	@Autowired
	private ReviewProductRepository reviewProductRepository;
	
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	ProductRepository  productRepository;
	
	@Autowired
	UserRepository userRepository;
	@Override
	public List<ReviewProductDTO> getAllReviewProducts(BodyReviewProduct data) {
		if(data.getPage() == null) {
			data.setPage(0);
		}
		if(data.getTake() == null || data.getTake() == 0) {
			data.setTake(10);
		}
		if(data.getStatus() == "" || data.getStatus() == null)
			data.setStatus("");
		if(data.getStartDate() == "" || data.getStartDate() == null)
			data.setStartDate(reviewProductRepository.START_DATE().substring(0, 10));
		if(data.getEndDate() == "" || data.getEndDate() == null)
			data.setEndDate(reviewProductRepository.END_DATE().substring(0, 10));
		System.out.println("check date: "+data.getStartDate());
		System.out.println("check date: "+data.getEndDate());
		Pageable paging =  PageRequest.of(data.getPage(), data.getTake());
		Page<ReviewProduct> page =	reviewProductRepository.locProduct(data.getStatus(), data.getStartDate()+" 00:00:00", data.getEndDate()+" 23:59:59", paging);
		try {
			List<ReviewProductDTO> listdto =new ArrayList<ReviewProductDTO>();
			
			page.getContent().forEach(l->{
				ReviewProductDTO rv=new ReviewProductDTO();
				ProductDTO pro=new ProductDTO();
				UserDTO us=new UserDTO();
				
				rv = modelMapper.map(l, ReviewProductDTO.class);
				us=modelMapper.map(l.getUser(), UserDTO.class);
				pro=modelMapper.map(l.getProducts(), ProductDTO.class);
				
				rv.setProductsdto(pro);
				us.setProvincedto(null);
				us.setDistrictdto(null);
				us.setWarddto(null);
				rv.setUserdto(us);
				listdto.add(rv);
				System.out.println("check rieview: "+l.getUser().getUsername());
				System.out.println("check rieview: "+l.getProducts().getProduct_name());
			});
			this.totalElement= page.getTotalElements();
			return listdto;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		
		
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
		try {
			Optional<ReviewProduct> optional= reviewProductRepository.findById(id);
			
			if(optional.isPresent()) {
				ReviewProduct entity=optional.get();
				reviewProductRepository.delete(entity);
				return entity.getId();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public ReviewProductDTO create(ResponReviewProduct data) {
		try {
			ReviewProduct  entity = new ReviewProduct();
			entity.setProducts(productRepository.findById(data.getProductId()).get());
			entity.setUser(userRepository.findById(data.getUserId()).get());
			entity.setStatus(0);
			entity.setComment(data.getComment());
			entity.setRating(data.getRating());
			entity.setCreateDate(new Date());
			reviewProductRepository.save(entity);
			data.setId(entity.getId());
			return modelMapper.map(entity, ReviewProductDTO.class);
		} catch (Exception e) {
			e.printStackTrace();			
		}
		
		return null;
	}

	@Override
	public List<ReviewProductDTO> getAllReviewProductsByProductId(String id) {
		List<ReviewProductDTO> listdto=new ArrayList<ReviewProductDTO>();
		List<ReviewProduct>  listenti= reviewProductRepository.findAllReviewProductsByProductId(Integer.valueOf(id));
		if(!listenti.isEmpty()) {
			System.out.println(listenti.size());
			listenti.forEach(l->{
				ReviewProductDTO rv=new ReviewProductDTO();
				UserDTO user=new UserDTO();
				user = modelMapper.map(l.getUser(), UserDTO.class);				
				rv = modelMapper.map(l, ReviewProductDTO.class);
				rv.setUserdto(user);
				listdto.add(rv);
				System.out.println("id: "+l.getId());
			});
		}
		return listdto;
	}

	@Override
	public Integer totalement() {
		Long n=this.totalElement;
		Integer sobanghi=n.intValue();		
		return sobanghi;
	}

	@Override
	public ReviewProductDTO updateTrangThai(String id) {
		Optional<ReviewProduct> optional=reviewProductRepository.findById(Integer.valueOf(id));
			if(optional.isPresent()) {
				ReviewProduct rv=optional.get();
				rv.setStatus(1);
				reviewProductRepository.save(rv);
				return modelMapper.map(rv, ReviewProductDTO.class);
			}
		return null;
	}
	
	

}

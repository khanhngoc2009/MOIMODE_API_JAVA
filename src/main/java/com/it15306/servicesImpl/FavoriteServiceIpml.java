package com.it15306.servicesImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.it15306.config.DataResponse;
import com.it15306.config.DataResponseList;
import com.it15306.dto.favorite.FavoriteDto;
import com.it15306.dto.favorite.PayloadFavorite;
import com.it15306.dto.favorite.PayloadList;
import com.it15306.dto.option.OptionDTO;
import com.it15306.dto.option.OptionProductDto;
import com.it15306.dto.option.OptionValueClientDto;
import com.it15306.dto.product.ProductDTO;
import com.it15306.dto.product.ProductDetailDto;
import com.it15306.dto.product.ProductSkuDto;
import com.it15306.entities.District;
import com.it15306.entities.Favorite;
import com.it15306.entities.OptionValue;
import com.it15306.entities.Option_Product;
import com.it15306.entities.Order;
import com.it15306.entities.Product;
import com.it15306.entities.User;
import com.it15306.entities.Ward;
import com.it15306.repository.FavoriteRepository;
import com.it15306.repository.WardRepository;
import com.it15306.services.FavoriteService;
import com.it15306.services.WardService;


@Service("FavoriteServiceIpml")
public class FavoriteServiceIpml implements FavoriteService{
	
	@Autowired
 	private FavoriteRepository favoriteRepository;
	
	@Autowired 
	private ProductServiceImpl productServiceImpl;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public ResponseEntity<DataResponseList<FavoriteDto>> listFavoriteByProduct(PayloadList payload,User user) {
		// TODO Auto-generated method stub
		DataResponseList<FavoriteDto> data = new DataResponseList<FavoriteDto>();
		try {
			Pageable paging =  PageRequest.of(payload.getPage(),payload.getTake()); 
	        Page<Favorite> pagedResult = favoriteRepository.listFavorite(paging,user.getId());
	       
	        // lấy thông tin product detail
	     
	        if(pagedResult.hasContent()) {
	            List<Favorite> list =  pagedResult.getContent();
	            List<FavoriteDto> listDto = new ArrayList<FavoriteDto>();
	            for(int k=0;k<list.size();k++) {
	            	Favorite  favorite = list.get(k);
	            	Object[] obj = (Object[]) productServiceImpl.getByIdProduct(favorite.getProduct().getId());
	            	
	            	ProductDTO productDTO = modelMapper.map( favorite.getProduct(), ProductDTO.class);
	            	if(obj != null) {
	            		System.out.print("\n" + obj + " khanh " + obj[2] );
	            		productDTO.setMin_price((Double) obj[1]);
		    			productDTO.setMax_price((Double) obj[2]);
		    			FavoriteDto favoriteDto = modelMapper.map(favorite,FavoriteDto.class);
		    			favoriteDto.setProduct(productDTO);
		            	listDto.add(favoriteDto);
	            	}else {
	            		productDTO.setMin_price((Double) 0.0);
		    			productDTO.setMax_price((Double) 0.0);
		    			FavoriteDto favoriteDto = modelMapper.map(favorite,FavoriteDto.class);
		    			favoriteDto.setProduct(productDTO);
		            	listDto.add(favoriteDto);
	            	}
	    			
	            }
	            data.setCode(200);
	        	data.setListData(listDto);
	        	data.setCount(list.size());
	        	data.setMessage("SUCCESS");
	        	return new ResponseEntity<DataResponseList<FavoriteDto>>(data,HttpStatus.OK);
	        } else {
	        	data.setCode(200);
	        	data.setListData(new ArrayList<FavoriteDto>());
	        	return new ResponseEntity<DataResponseList<FavoriteDto>>(data,HttpStatus.OK);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			data.setMessage("Loi");
			data.setCode(HttpStatus.FAILED_DEPENDENCY.value());
			return new ResponseEntity<DataResponseList<FavoriteDto>>(data,HttpStatus.FAILED_DEPENDENCY);
			
		}
	}

	@Override
	public ResponseEntity<DataResponse<String>> disableFavorite(PayloadFavorite payload) {
		// TODO Auto-generated method stub
		DataResponse<String> data = new DataResponse<String>();
		try {
			favoriteRepository.deleteById(payload.getId_product());
			data.setMessage(" Thanh cong");
			data.setCode(200);
			data.setData("Unfollow Thanh cong");
			return new ResponseEntity<DataResponse<String>>(data,HttpStatus.OK);
		} catch (Exception e) {
			data.setMessage("Loi");
			data.setCode(HttpStatus.FAILED_DEPENDENCY.value());
			data.setData("Loi");
			 return new ResponseEntity<DataResponse<String>>(data,HttpStatus.FAILED_DEPENDENCY);
		}
	}

	@Override
	public ResponseEntity<DataResponse<FavoriteDto>> enableFavorite(PayloadFavorite payload) {
		// TODO Auto-generated method stub
		DataResponse<FavoriteDto> data = new DataResponse<FavoriteDto>();
		try {
			Favorite f = favoriteRepository.checkFavorite(payload.getId_product(), payload.getUser().getId());
			if(f != null) {
				Favorite favorite = new Favorite();
				favorite.setCreate_time(new Date());
				favorite.setStatus(1);
				favorite.setProduct(productServiceImpl.getById(payload.getId_product()));
				favorite.setUser(payload.getUser());
				Favorite fa = favoriteRepository.save(favorite);
	        	Object[] obj = (Object[]) productServiceImpl.getByIdProduct(fa.getProduct().getId());
	        	ProductDTO productDTO = modelMapper.map( favorite.getProduct(), ProductDTO.class);
	        	FavoriteDto favoriteDto = modelMapper.map(favorite,FavoriteDto.class);
	        	if(obj != null) {
	        		System.out.print("\n" + obj + " khanh " + obj[2] );
	        		productDTO.setMin_price((Double) obj[1]);
	    			productDTO.setMax_price((Double) obj[2]);
	    			favoriteDto.setProduct(productDTO);
	        	}else {
	        		productDTO.setMin_price((Double) 0.0);
	    			productDTO.setMax_price((Double) 0.0);
	    			favoriteDto.setProduct(productDTO);
	        	}
				data.setMessage("Follow thanh cong");
				data.setCode(200);
				data.setData(favoriteDto);
				return new ResponseEntity<DataResponse<FavoriteDto>>(data,HttpStatus.OK);
			}else {
				try {
					favoriteRepository.deleteById(payload.getId_product());
					data.setMessage("Unfolow thanh cong");
					data.setCode(200);
					return new ResponseEntity<DataResponse<FavoriteDto>>(data,HttpStatus.OK);
				} catch (Exception e) {
					data.setMessage("Loi");
					data.setCode(HttpStatus.FAILED_DEPENDENCY.value());
					 return new ResponseEntity<DataResponse<FavoriteDto>>(data,HttpStatus.FAILED_DEPENDENCY);
				}
			}
		} catch (Exception e) {
			data.setMessage("Loi");
			data.setCode(HttpStatus.FAILED_DEPENDENCY.value());
			return new ResponseEntity<DataResponse<FavoriteDto>>(data,HttpStatus.FAILED_DEPENDENCY);
		}
	}

	@Override
	public FavoriteDto checkFavorite(PayloadFavorite payload) {
//		DataResponse<FavoriteDto> data= new DataResponse<FavoriteDto>();
		try {
			Favorite favorite = favoriteRepository.checkFavorite(payload.getId_product(), payload.getUser().getId());
			return modelMapper.map(favorite,FavoriteDto.class);
		} catch (Exception e) {
			return null;
		}
		
//		return null;
	}
	
	

}

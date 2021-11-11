package com.it15306.servicesImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it15306.dto.cart.CartDTO;
import com.it15306.dto.cart.CartProductDTO;
import com.it15306.dto.cart.dataBodyCart;
import com.it15306.dto.cart.dataDeleteCart;
import com.it15306.dto.product.ProductSkuDto;
import com.it15306.entities.Cart;
import com.it15306.entities.CartProduct;
import com.it15306.entities.Product_Sku;
import com.it15306.entities.User;
import com.it15306.repository.CartProductReponsitory;
import com.it15306.repository.CartRepository;
import com.it15306.repository.ProductSkuRepository;
import com.it15306.repository.UserRepository;
import com.it15306.services.CartService;
@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	UserRepository  userRepository;
	
	@Autowired
	ProductSkuRepository productSkuRepository;
	
	@Autowired
	CartProductReponsitory cartProductReponsitory;
	
	@Override
	public CartDTO getCartByUser(Integer user_id) {
		System.out.println("-------------------------------------------------1");
		List<CartProduct> list=new ArrayList<CartProduct>();
		List<CartDTO> listCart=new ArrayList<CartDTO>();
		List<CartProductDTO> listdto=new ArrayList<CartProductDTO>();
		CartDTO entity=new CartDTO();
		Cart cvo = cartRepository.findUserID(user_id);
		System.out.println("-------------------------------------------------2: "+cvo.getUser().getId());
		System.out.println("-------------------------------------------------2: "+cvo.getCartproduct().size());

		if(cvo != null) {
			System.out.println("-------------------------------------------------3");
			entity=modelMapper.map(cvo, CartDTO.class);
			list=cvo.getCartproduct();
			cvo.getCartproduct().forEach(l -> {
				CartProductDTO dto=new CartProductDTO();
				dto=modelMapper.map(l, CartProductDTO.class);
				dto.setProductSkuDTOs(modelMapper.map(l.getProductSkus(), ProductSkuDto.class));
				listdto.add(dto);
				});
			entity.setCartProductsDTO(listdto);
			entity.setUser(null);
		}
		return entity;
	}

	@Override
	public Cart getById(String cart_id, String user_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cart saveUser(Cart cart) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(dataDeleteCart data) {
		try {
			
	
		Cart cvo = cartRepository.findUserID(data.getUserId());

		if(cvo != null) {
			System.out.println("cho xoas");

			for (int i = 0; i < data.getProductSKU().size(); i++) {
				System.out.println("-----1--------");
				cartProductReponsitory.deleteLists(cvo.getId(),Integer.parseInt(data.getProductSKU().get(i)));
				
				//Thread.sleep(100);
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public CartProductDTO insertProductToCart(dataBodyCart data) {
		Cart cart=new Cart();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date();
		if(data.getQuantity()==0) {
			data.setQuantity(1);
		}
		User user =	userRepository.getOne(data.getUserId());
		Product_Sku productSKU= productSkuRepository.findProductSKUById(data.getProductSKUId());
		Cart cvo = cartRepository.findUserID(data.getUserId());
		CartProduct carpro=new CartProduct();
		CartProduct cartproduct =new CartProduct();
		if(cvo == null){
			
			//luu cart
			cart.setUser(user);
			cart.setCreate_date(date);
			cart.setStatus(1);
			cartRepository.save(cart);
			//luu cart product
			cart.setId(cart.getId());;
			carpro.setCarts(cart);
			carpro.setProductSkus(productSKU);
			carpro.setQuantity(data.getQuantity());
			cartProductReponsitory.save(carpro);
			carpro.setId(carpro.getId());
			CartProductDTO resp= modelMapper.map(carpro, CartProductDTO.class);
			resp.setProductSkuDTOs(modelMapper.map(productSKU, ProductSkuDto.class));
			System.out.println(resp);
			return resp;
		}else {
			//check update			
		cartproduct =	cartProductReponsitory.selectCheck(cvo.getId(), data.getProductSKUId());
		if(cartproduct == null) {
			//luu cart product	 new		
			carpro.setCarts(cvo);
			carpro.setProductSkus(productSKU);
			carpro.setQuantity(data.getQuantity());
			cartProductReponsitory.save(carpro);
			carpro.setId(carpro.getId());
			CartProductDTO resp = modelMapper.map(carpro, CartProductDTO.class);
			resp.setProductSkuDTOs(modelMapper.map(productSKU, ProductSkuDto.class));
			return resp;
		}else {
			// update quantity
			Integer soluong=cartproduct.getQuantity()+data.getQuantity();
			cartProductReponsitory.updateQuantitys(soluong,cvo.getId(), data.getProductSKUId());
			CartProductDTO resp= modelMapper.map(carpro, CartProductDTO.class);
			resp.setQuantity(soluong);
			resp.setProductSkuDTOs(modelMapper.map(productSKU, ProductSkuDto.class));
			return resp;
		}
		}
	}

	@Override
	public CartProductDTO updateProductToCart(dataBodyCart data) {
		User user =	userRepository.getOne(data.getUserId());
		Product_Sku productSKU= productSkuRepository.findProductSKUById(data.getProductSKUId());
		Cart cvo = cartRepository.findUserID(data.getUserId());
		CartProduct cartproduct =new CartProduct();
		CartProduct carpro=new CartProduct();
		if(cvo != null) {
			cartproduct =	cartProductReponsitory.selectCheck(cvo.getId(), data.getProductSKUId());
			if(cartproduct != null) {
				Integer soluong=cartproduct.getQuantity()+data.getQuantity();
				cartProductReponsitory.updateQuantitys(soluong,cvo.getId(), data.getProductSKUId());
				CartProductDTO resp= modelMapper.map(carpro, CartProductDTO.class);
				resp.setQuantity(soluong);
				resp.setProductSkuDTOs(modelMapper.map(productSKU, ProductSkuDto.class));
				return resp;
			}
			
		}
		
		return null;
	}

	

}

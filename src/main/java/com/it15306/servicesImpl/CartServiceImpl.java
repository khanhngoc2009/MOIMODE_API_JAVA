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
import com.it15306.repository.SkuRepository;
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
	
	@Autowired
	SkuRepository skuRepository;
	
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
				ProductSkuDto p_dto = modelMapper.map(l.getProductSkus(), ProductSkuDto.class);
				List<Object> obj =  skuRepository.getInfoBySku(p_dto.getProduct_sku_id());
				String optionValueProducts = "";
				String name_product = "";
				for(int k =  0;k<obj.size();k++) {
					Object[] row = (Object[]) obj.get(k);
					optionValueProducts = optionValueProducts + (k != 0 ? ", " : "") + (String) row[0];
					name_product = (String) row[1];
				}
				dto.setProductSkuDTOs(p_dto);
				dto.setProduct_name(name_product);
				dto.setOption_value(optionValueProducts);
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
		if(productSKU== null || user == null) {
			return null;
		}
		Cart cvo = cartRepository.findUserID(data.getUserId());
		CartProduct carpro=new CartProduct();
		CartProduct cartproduct =new CartProduct();
		if(cvo == null){
			System.out.println("vo tao maoi cart ");
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
			System.out.println("id---1: "+carpro.getId());
			CartProductDTO resp= modelMapper.map(carpro, CartProductDTO.class);
			resp.setProductSkuDTOs(modelMapper.map(productSKU, ProductSkuDto.class));
			System.out.println(resp);
			return resp;
		}else {
			//check update			
		cartproduct =	cartProductReponsitory.selectCheck(cvo.getId(), data.getProductSKUId());
		
		if(cartproduct == null) {
			//luu cart product	 new.
		

			System.out.println("vo tao maoi cart product qlt");
			carpro.setCarts(cvo);
			carpro.setProductSkus(productSKU);
			carpro.setQuantity(data.getQuantity());
			cartProductReponsitory.save(carpro);
			carpro.setId(carpro.getId());
			System.out.println("id---2: "+carpro.getId());
			CartProductDTO resp = modelMapper.map(carpro, CartProductDTO.class);
			resp.setProductSkuDTOs(modelMapper.map(productSKU, ProductSkuDto.class));
			return resp;
		}else {
			// update quantity
			Integer checkQuantity= cartproduct.getQuantity() + data.getQuantity();
			if(checkQuantity > 100) {
				System.out.println("check so luong tren 100. ko update dc");
				return null;
			}
			System.out.println("vo uodate qlt");
			Integer soluong=cartproduct.getQuantity()+data.getQuantity();
			cartProductReponsitory.updateQuantitys(soluong,cvo.getId(), data.getProductSKUId());
			CartProductDTO resp= modelMapper.map(carpro, CartProductDTO.class);
			resp.setId(cartproduct.getId());
			
			resp.setQuantity(soluong);
			resp.setProductSkuDTOs(modelMapper.map(productSKU, ProductSkuDto.class));
			return resp;
		}
		}
	}

	@Override
	public CartProductDTO updateProductToCart(dataBodyCart data) {
		if(data.getQuantity() <= 0) data.setQuantity(1);
		User user =	userRepository.getOne(data.getUserId());
		Product_Sku productSKU= productSkuRepository.findProductSKUById(data.getProductSKUId());
		Cart cvo = cartRepository.findUserID(data.getUserId());
		CartProduct cartproduct =new CartProduct();
		CartProduct carpro=new CartProduct();
		if(cvo != null) {
			cartproduct =	cartProductReponsitory.selectCheck(cvo.getId(), data.getProductSKUId());
			if(cartproduct != null) {
				Integer checkQuantity= cartproduct.getQuantity() + data.getQuantity();
				if(checkQuantity > 100) {
					System.out.println("check so luong tren 100. ko tao dc");
					return null;
				}
				Integer soluong=data.getQuantity();
				cartProductReponsitory.updateQuantitys(soluong,cvo.getId(), data.getProductSKUId());
				CartProductDTO resp= modelMapper.map(carpro, CartProductDTO.class);
				resp.setQuantity(soluong);
				resp.setProductSkuDTOs(modelMapper.map(productSKU, ProductSkuDto.class));
				return resp;
			}
			
		}
		
		return null;
	}

	@Override
	public CartProduct getByCartProductId(Integer cart_product) {
		return cartProductReponsitory.getOne(cart_product);
	}

	@Override
	public void deleteCartProductByID(Integer cart_product_id) {
		cartProductReponsitory.deleteById(cart_product_id);
	}

	

}

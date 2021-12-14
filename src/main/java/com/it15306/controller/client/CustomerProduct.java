package com.it15306.controller.client;

import java.math.BigInteger;
import java.security.cert.PKIXRevocationChecker.Option;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.type.TimestampType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.it15306.config.DataResponse;
import com.it15306.config.DataResponseList;
import com.it15306.dto.DistrictDTO;
import com.it15306.dto.ProvinceDTO;
import com.it15306.dto.UserDTO;
import com.it15306.dto.category.CategoryDTO;
import com.it15306.dto.favorite.FavoriteDto;
import com.it15306.dto.favorite.PayloadFavorite;
import com.it15306.dto.option.OptionClientDto;
import com.it15306.dto.option.OptionDTO;
import com.it15306.dto.option.OptionProductDto;
import com.it15306.dto.option.OptionValueClientDto;
import com.it15306.dto.option.OptionValueDTO;
import com.it15306.dto.product.DataBodyFindSkuDto;
import com.it15306.dto.product.ProductByCategoryBodyDto;
import com.it15306.dto.product.ProductDTO;
import com.it15306.dto.product.ProductDetailDto;
import com.it15306.dto.product.ProductSkuDto;
import com.it15306.dto.product.ProductSkuPriceDto;
import com.it15306.entities.Category;
import com.it15306.entities.District;
import com.it15306.entities.OptionValue;
import com.it15306.entities.Option_Product;
import com.it15306.entities.Options;
import com.it15306.entities.Product;
import com.it15306.entities.Product_Sku;
import com.it15306.entities.Province;
import com.it15306.entities.User;
import com.it15306.jwt.JwtTokenProvider;
import com.it15306.services.FavoriteService;
import com.it15306.services.ReviewProductService;
import com.it15306.services.UserService;
import com.it15306.servicesImpl.ProductServiceImpl;
import com.it15306.servicesImpl.ProvinceServiceImpl;
import com.it15306.servicesImpl.ReviewProductServiceImpl;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200","http://35.198.241.56" })
@RestController
@RequestMapping("/miemode_api/v1")
public class CustomerProduct {
	@Autowired
	private ProductServiceImpl productServiceImpl;
	@Autowired
	private FavoriteService favoriteService;
	@Autowired
	private JwtTokenProvider tokenProvider;
	
	@Autowired
	private ReviewProductService reviewProductService;
	
	@Autowired
	private UserService userservice;
	@RequestMapping(value = "/product/list/news", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> getListProductNew(HttpServletRequest httpServletRequest) {
		DataResponseList<ProductDTO> l=  new DataResponseList<ProductDTO>();
		try {
			String token = httpServletRequest.getHeader("Authorization");
			System.out.print( "\n" + token + "khansh \n");
//			User user = new User();
//			if(token != null && token.length() > 0) {
//				String username = tokenProvider.getUserNameFromJWT(token.substring(7));
//				user = userservice.getByUsername(username);
//			}
			ModelMapper modelMapper = new ModelMapper();
			List<Object> list = this.productServiceImpl.getAllProducts(0,10);
			long count = (long) this.productServiceImpl.getCountClient();
			List<Double> minPrice = new ArrayList<Double>();
			List<Double> maxPrice = new ArrayList<Double>();
			List<Product> prs = new ArrayList<Product>();
			for (int i=0; i<list.size(); i++){
				   Object[] row = (Object[]) list.get(i);
				   Product pr = (Product) row[0];
				   minPrice.add((Double) row[1]);
				   maxPrice.add((Double) row[2]);
				   prs.add(pr);
			}
			List<ProductDTO> productDTOs =new ArrayList<ProductDTO>();
			if (prs.size() > 0) {
				for (int i = 0; i < prs.size(); i++) {
					ProductDTO prDto = (modelMapper.map(prs.get(i), ProductDTO.class));
					prDto.setMin_price(minPrice.get(i));
					prDto.setMax_price(maxPrice.get(i));
					prDto.setCategory_id(prs.get(i).getCategory().getId());
					prDto.setStarAvg(reviewProductService.getAvgStar(prs.get(i).getId()));
					PayloadFavorite pf=  new PayloadFavorite();
					if(token != null && token.length() > 0) {
//						String token = httpServletRequest.getHeader("Authorization").substring(7);
						String username = tokenProvider.getUserNameFromJWT(token.substring(7));
						User user = userservice.getByUsername(username);
						pf.setId_product(prs.get(i).getId());
						pf.setUser(user);
						FavoriteDto fa = favoriteService.checkFavorite(pf);
						//System.out.print("32" + fa.getId() + " \n");
						if(fa != null) {
							prDto.setIsFavorite(1);
						}
					}
					productDTOs.add(prDto);
				}
				
			}
			l.setCode(200);
			l.setCount(Integer.parseInt(String.valueOf(count)));
			l.setListData(productDTOs);
			l.setMessage("Success");
			return new ResponseEntity<>(l,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			l.setMessage("Fail");
			l.setCode(HttpStatus.FAILED_DEPENDENCY.value());
			return new ResponseEntity<>(l,HttpStatus.FAILED_DEPENDENCY);
		}
		
	}
	@RequestMapping(value = "/product/list-by-category", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?>  getListProductByCategory(@RequestBody ProductByCategoryBodyDto dto,HttpServletRequest httpServletRequest) {
		DataResponseList<ProductDTO> data=  new DataResponseList<ProductDTO>();
		try {
			ModelMapper modelMapper = new ModelMapper();
			String token = httpServletRequest.getHeader("Authorization");
			System.out.print( "\n" + token + "khansh \n");
//			User user = new User();
//			if(token != null && token.length() > 0) {
//				String username = tokenProvider.getUserNameFromJWT(token.substring(7));
//				user = userservice.getByUsername(username);
//			}
			Category category = new Category();
			category.setId(dto.getCategory_id());
			List<Object> list =  this.productServiceImpl.getProductByCategory(category,dto.getPage(),dto.getTake());
			long count = (long) this.productServiceImpl.getCountClient();
			List<Double> minPrice = new ArrayList<Double>();
			List<Double> maxPrice = new ArrayList<Double>();
			List<Product> prs = new ArrayList<Product>();
			for (int i=0; i<list.size(); i++){
				   Object[] row = (Object[]) list.get(i);
				   Product pr = (Product) row[0];
				   minPrice.add((Double) row[1]);
				   maxPrice.add((Double) row[2]);
				   prs.add(pr);
			}
			List<ProductDTO> productDTOs =new ArrayList<ProductDTO>();
			if (prs.size() > 0) {
				for (int i = 0; i < prs.size(); i++) {
					ProductDTO prDto = (modelMapper.map(prs.get(i), ProductDTO.class));
					prDto.setMin_price(minPrice.get(i));
					prDto.setMax_price(maxPrice.get(i));
					prDto.setCategory_id(prs.get(i).getCategory().getId());
					prDto.setStarAvg(reviewProductService.getAvgStar(prs.get(i).getId()));
					PayloadFavorite pf=  new PayloadFavorite();
					if(token != null && token.length() > 0) {
//						String token = httpServletRequest.getHeader("Authorization").substring(7);
						String username = tokenProvider.getUserNameFromJWT(token.substring(7));
						User user = userservice.getByUsername(username);
						pf.setId_product(prs.get(i).getId());
						pf.setUser(user);
						FavoriteDto fa = favoriteService.checkFavorite(pf);
						//System.out.print("32" + fa.getId() + " \n");
						if(fa != null) {
							prDto.setIsFavorite(1);
						}
					}
					productDTOs.add(prDto);
				}
			}
			data.setCode(200);
			data.setCount(Integer.parseInt(String.valueOf(count)));
			data.setListData(productDTOs);
			data.setMessage("Success");	
			return new ResponseEntity<>(data,HttpStatus.OK);
		} catch (Exception e) {
			data.setMessage("Fail");
			data.setCode(HttpStatus.FAILED_DEPENDENCY.value());
			return new ResponseEntity<>(data,HttpStatus.FAILED_DEPENDENCY);
		}
	}
	
	@RequestMapping(value = "/product/list-selling", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> getListProductSelling(HttpServletRequest httpServletRequest) {
		DataResponseList<ProductDTO> data=  new DataResponseList<ProductDTO>();
		try {
			ModelMapper modelMapper = new ModelMapper();
			String token = httpServletRequest.getHeader("Authorization");	
			List<Object> list =  this.productServiceImpl.getSellingProducts(0,10);
			long count = (long) this.productServiceImpl.getCountClient();
			List<Double> minPrice = new ArrayList<Double>();
			List<Double> maxPrice = new ArrayList<Double>();
			List<Product> prs = new ArrayList<Product>();
			for (int i=0; i<list.size(); i++){
				   Object[] row = (Object[]) list.get(i);
				   Product pr = (Product) row[0];
				   minPrice.add((Double) row[1]);
				   maxPrice.add((Double) row[2]);
				   prs.add(pr);
			}
			List<ProductDTO> productDTOs =new ArrayList<ProductDTO>();
			if (prs.size() > 0) {
				for (int i = 0; i < prs.size(); i++) {
					ProductDTO prDto = (modelMapper.map(prs.get(i), ProductDTO.class));
					prDto.setMin_price(minPrice.get(i));
					prDto.setMax_price(maxPrice.get(i));
					prDto.setCategory_id(prs.get(i).getCategory().getId());
					prDto.setStarAvg(reviewProductService.getAvgStar(prs.get(i).getId()));
					PayloadFavorite pf=  new PayloadFavorite();
					if(token != null && token.length() > 0) {
						String username = tokenProvider.getUserNameFromJWT(token.substring(7));
						User user = userservice.getByUsername(username);
						pf.setId_product(prs.get(i).getId());
						pf.setUser(user);
						FavoriteDto fa = favoriteService.checkFavorite(pf);
						//System.out.print("32" + fa.getId() + " \n");
						if(fa != null) {
							prDto.setIsFavorite(1);
						}
					}
					productDTOs.add(prDto);
				}
			}
			data.setCode(200);
			data.setCount(Integer.parseInt(String.valueOf(count)));
			data.setListData(productDTOs);
			data.setMessage("Success");
			return new ResponseEntity<>(data,HttpStatus.OK);
		} catch (Exception e) {
			data.setMessage("Fail");
			data.setCode(HttpStatus.FAILED_DEPENDENCY.value());
			return new ResponseEntity<>(data,HttpStatus.FAILED_DEPENDENCY);
		}
		
	}

	@RequestMapping(value = "/product/detail/{product_id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> getProductDetail(@PathVariable Integer product_id,HttpServletRequest httpServletRequest) {
		DataResponse<ProductDetailDto> response = new DataResponse<ProductDetailDto>();
		try {
			Object[] obj = (Object[]) this.productServiceImpl.getByIdProduct(product_id);
			Product product=(Product) obj[0];
			String token = httpServletRequest.getHeader("Authorization");
			System.out.print( "\n" + token + "khansh \n");
			
			ProductDetailDto pr = new ProductDetailDto();
			ModelMapper modelMapper = new ModelMapper();
			List<OptionProductDto> optionProductDTOs =new ArrayList<OptionProductDto>();
			List<ProductSkuDto> productSkuDTOs = new ArrayList<ProductSkuDto>();
			int size = product.getOptions_products().size();
			System.out.print(obj[0]);
			if(size > 0) {
				for(int i=0;i<size;i++) {
					Option_Product ot_pr = product.getOptions_products().get(i);
					OptionProductDto op = modelMapper.map(ot_pr, OptionProductDto.class);
					int s = ot_pr.getOption().getOption_values().size();
					OptionDTO oDto = modelMapper.map(ot_pr.getOption(), OptionDTO.class);
					List<OptionValueClientDto> listOtvClient = new ArrayList<OptionValueClientDto>();
					for(int j=0;j<s;j++) {	
						OptionValue otvClient = ot_pr.getOption().getOption_values().get(j);
						listOtvClient.add(modelMapper.map(otvClient, OptionValueClientDto.class));
					}
					oDto.setValues(listOtvClient);
					op.setOption(oDto);
					optionProductDTOs.add(op);
				}
			}
			ProductDTO productDTO  = modelMapper.map(product, ProductDTO.class);
			productDTO.setMin_price((Double) obj[1]);
			productDTO.setMax_price((Double) obj[2]);
			PayloadFavorite pf=  new PayloadFavorite();
			if(token != null && token.length() > 0) {
//				String token = httpServletRequest.getHeader("Authorization").substring(7);
				String username = tokenProvider.getUserNameFromJWT(token.substring(7));
				User user = userservice.getByUsername(username);
				pf.setId_product(product.getId());
				pf.setUser(user);
				FavoriteDto fa = favoriteService.checkFavorite(pf);
				//System.out.print("32" + fa.getId() + " \n");
				if(fa != null) {
					productDTO.setIsFavorite(1);
				}
			}
			
			pr.setOptions_products(optionProductDTOs);
			pr.setCategory(modelMapper.map(product.getCategory(), CategoryDTO.class));
			pr.setProduct(productDTO);
			pr.setProduct_sku(productSkuDTOs);
			response.setCode(200);
			response.setMessage("Success");
			response.setData(pr);
			return new ResponseEntity<>(response,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(HttpStatus.FAILED_DEPENDENCY.value());
			response.setMessage("Fail");
			return new ResponseEntity<>(response,HttpStatus.FAILED_DEPENDENCY);
		}
	}
	@RequestMapping(value = "/product/sku-price", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> getProductSkuPrice(@RequestBody ProductSkuPriceDto dto,HttpServletRequest httpServletRequest) {
		DataResponse<ProductSkuDto> response = new DataResponse<ProductSkuDto>();
		ModelMapper modelMapper = new ModelMapper();
		try {
			Object[] sku_obj = (Object[]) productServiceImpl.findBySku(dto.getProduct_id(), dto.getOption_value_1(), dto.getOption_value_2(), dto.getOption_value_3());
			ProductSkuDto p = new ProductSkuDto();
			Integer sku_id = ((BigInteger) sku_obj[0]).intValue();
			p.setProduct_sku_id(sku_id);
			p.setValue_sku((String) sku_obj[2]);
			p.setPrice((Double) sku_obj[3]);
			p.setQuantity_remain((Integer) sku_obj[4]);
			p.setQuantity_total((Integer) sku_obj[6]);
			p.setQuantiy_rest((Integer) sku_obj[5]);
			p.setStatus((Integer) sku_obj[7]);
			p.setUrl_media((String) sku_obj[8]);
			p.setProduct(modelMapper.map(productServiceImpl.getById(dto.getProduct_id()),ProductDTO.class));
			response.setCode(200);
			response.setMessage("Success");
			response.setData(p);
			return new ResponseEntity<>(response,HttpStatus.OK);
		} catch (Exception e) {
			response.setCode(HttpStatus.FAILED_DEPENDENCY.value());
			response.setMessage("Fail");
			return new ResponseEntity<>(response,HttpStatus.FAILED_DEPENDENCY);
		}
	}
}


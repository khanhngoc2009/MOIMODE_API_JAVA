package com.it15306.servicesImpl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.it15306.config.DataResponseList;
import com.it15306.dto.product.ProductSkuDto;
import com.it15306.dto.product.ProductSkuListAdminDto;
import com.it15306.entities.Category;
import com.it15306.entities.ImageProduct;
import com.it15306.entities.Order;
import com.it15306.entities.Product;
import com.it15306.entities.Product_Sku;
import com.it15306.entities.Sku;
import com.it15306.entities.TypeOptions;
import com.it15306.repository.ImageProductRepository;
import com.it15306.repository.ProductRepository;
import com.it15306.repository.ProductSkuRepository;
import com.it15306.repository.SkuRepository;
import com.it15306.repository.TypeOptionRepository;

@Service("ProductServiceImpl")
public class ProductServiceImpl implements com.it15306.services.ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private SkuRepository skuRepository;
	
	@Autowired
	private ImageProductRepository imageProductRepository;
	
	@Autowired
	private TypeOptionRepository typeOptionRepository;
	
	@Autowired
	private ProductSkuRepository productSkuRepository;
	
	public List<Object> getAllProducts(int page,int take) {
		Pageable paging =  PageRequest.of(page, take);
        Page<Object> pagedResult = productRepository.findAllProduct(paging);
       
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
        	return new ArrayList<Object>();
		}
	}
	
	public List<Object> getSellingProducts(int page,int take) {
		Pageable paging =  PageRequest.of(page, take,Sort.by("create_date"));
        Page<Object> pagedResult = productRepository.findProductSelling(paging);
      
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
        	return new ArrayList<Object>();
		}
	}
	
	public List<Object> getAllProductsAdmin(int page,int take,String category_id,String start_date, String end_date, String name, String status ) {
		Pageable paging =  PageRequest.of(page, take,Sort.by("create_date"));
		System.out.print("k" + category_id + "k\n");
        Page<Object> pagedResult = productRepository.findAllProductsAdmin(paging, category_id, start_date, end_date, name, status);
       
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
        	return new ArrayList<Object>();
		}
	}
	
	public Object getByIdProduct(Integer product_id) {
		return productRepository.findByIdProduct(product_id); 
	}
	
	public Product getById(Integer product_id) {
		
		Optional<Product> op_Res = productRepository.findById(product_id); 
		
		return op_Res.get();
	}
	
	public long getCountClient() {
		return productRepository.countProductClient();
	}
	
	public long getCountAdmin() {
		return productRepository.countProductAdmin(); 
	}
	
	public BigInteger getCountAdminByQuery(String category_id,String start_date, String end_date, String name, String status ) {
		return productRepository.countAdminQuery(category_id, start_date, end_date, name, status); 
	}
	

	@Override
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public void delete(Product product) {
		productRepository.delete(product);
	}

	public List<Object> getProductByCategory(Integer min_price,Integer max_price,Integer type , Category category,int page,int take){
		Pageable paging =  PageRequest.of(page, take);
        if(type == 0) {
        	Page<Object> pagedResult = productRepository.findProductByCategoryPriceRaise(min_price, max_price, category.getId(), paging);
            
            if(pagedResult.hasContent()) {
                return pagedResult.getContent();
            } else {
            	return new ArrayList<Object>();
    		}
        }else if(type==1) {
        	Page<Object> pagedResult = productRepository.findProductByCategoryPriceDECREASE(min_price, max_price,category.getId(), paging);
            
            if(pagedResult.hasContent()) {
                return pagedResult.getContent();
            } else {
            	return new ArrayList<Object>();
    		}
        }else if(type==2) {
        	Page<Object> pagedResult = productRepository.findProductByCategoryNameRaise(min_price, max_price, category.getId(), paging);
            
            if(pagedResult.hasContent()) {
                return pagedResult.getContent();
            } else {
            	return new ArrayList<Object>();
    		}
        }else {
        	Page<Object> pagedResult = productRepository.findProductByCategoryAll(min_price, max_price, category.getId(), paging);
            
            if(pagedResult.hasContent()) {
                return pagedResult.getContent();
            } else {
            	return new ArrayList<Object>();
    		}
        }
	}
	
	public Integer getCountClientCategory(Integer min_price,Integer max_price,Category category) {
		
		List<Object> list= productRepository.countProductClientCategory(min_price, max_price, category.getId());
		System.out.print(list.size());
		return list.size();
	}
	
	public Object findBySku(Integer product_is, Integer option_1,Integer option_2,Integer option_3 ) {
		return skuRepository.findByOptionValue(product_is, option_1, option_2, option_3);
	}
	
	public Product_Sku saveProductSku(Product_Sku p_u) {
		return productSkuRepository.save(p_u);
	}
	public ImageProduct saveImageProduct(ImageProduct i_p) {
		return imageProductRepository.save(i_p);
	}
	
	public Iterable<Sku> saveListSku(List<Sku> listSku) {
		Iterable<Sku> iterableSku = listSku;
		return skuRepository.saveAll(iterableSku);
	}
	
	
	
	public void saveSku(Integer product_sku_id,Integer option_sku_id) {
		 skuRepository.saveValue(product_sku_id,option_sku_id);
	}
	
	
	public List<TypeOptions> getListTypeOption() {
		return typeOptionRepository.findAll();
	}
	
	
	public List<Product_Sku> getListProductSkuByProductId(Integer product_id) {
		return productSkuRepository.findByProductId(product_id);
	}
	
	
	public List<ImageProduct> getImageByProductId(Integer product_id) {
		return imageProductRepository.getByProductId(product_id);
	}
	
	public Product_Sku getProductSkuById(Integer id) {
		return productSkuRepository.getOne(id);
	}
	
	public List<Object> getSkuOption(Integer id) {
		return skuRepository.getInfoBySku(id);
	}
	
	public ResponseEntity<DataResponseList<ProductSkuListAdminDto>>  getListProductSku (int page,int take, String sku_value) {
		ModelMapper modelMapper = new ModelMapper();
		DataResponseList<ProductSkuListAdminDto> data = new DataResponseList<ProductSkuListAdminDto>();
		try {
			Pageable paging =  PageRequest.of(page, take); 
			Page<Product_Sku> pagedResult = productSkuRepository.findProductSKU(sku_value, paging);
			if(pagedResult.hasContent()) {
				List<Product_Sku>  pr_skus = pagedResult.getContent();
				List<ProductSkuListAdminDto> skuDtos= new ArrayList<ProductSkuListAdminDto>();
				int size = pr_skus.size();
				for(int i=0;i<size;i++) {
					ProductSkuListAdminDto  p=  modelMapper.map(pr_skus.get(i), ProductSkuListAdminDto.class);
					List<Object> obj =  skuRepository.getInfoBySku(pr_skus.get(i).getProduct_sku_id());
					String optionValueProducts = "";
					String name_product = "";
					for(int k =  0;k<obj.size();k++) {
						Object[] row = (Object[]) obj.get(k);
						optionValueProducts = optionValueProducts + (k != 0 ? ", " : "") + (String) row[0];
						name_product = (String) row[1];
					}
					p.setProduct_name(name_product);
					p.setOption_value(optionValueProducts);
					skuDtos.add(p);
				}
				data.setCount(productSkuRepository.countSku(sku_value));
				data.setListData(skuDtos);
				data.setMessage("Success");
				return new ResponseEntity<>(data,HttpStatus.OK);
	        } else {
	        	data.setListData(new ArrayList<ProductSkuListAdminDto>());
	        	data.setCode(200);
	        	data.setMessage("Thanh cong");
	        	return new ResponseEntity<>(data,HttpStatus.OK);
			}
		} catch (Exception e) {
			data.setCode(HttpStatus.FAILED_DEPENDENCY.value());
			data.setMessage("Fail");
			
			return new ResponseEntity<>(data,HttpStatus.FAILED_DEPENDENCY);
		}
	}
}



















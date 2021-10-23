package com.it15306.dto.product;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.OneToMany;

import com.it15306.dto.CategoryDTO;
import com.it15306.dto.OptionClientDto;
import com.it15306.dto.OptionDTO;
import com.it15306.dto.OptionProductDto;
import com.it15306.dto.OptionValueDTO;
import com.it15306.dto.ProductDTO;
import com.it15306.dto.ProductSkuDto;
import com.it15306.dto.ProductSkuValueDto;
import com.it15306.entities.Category;
import com.it15306.entities.Option_Product;
import com.it15306.entities.Product_Sku;

public class ProductDetailDto {
	private ProductDTO product;
	private CategoryDTO category = new CategoryDTO();

	private List<ProductSkuDto> product_sku = new ArrayList<>();
	
	private List<OptionProductDto> options_products = new ArrayList<>();

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public CategoryDTO getCategory() {
		return category;
	}

	public void setCategory(CategoryDTO category) {
		this.category = category;
	}

	public List<ProductSkuDto> getProduct_sku() {
		return product_sku;
	}

	public void setProduct_sku(List<ProductSkuDto> product_sku) {
		this.product_sku = product_sku;
	}

	public List<OptionProductDto> getOptions_products() {
		return options_products;
	}

	public void setOptions_products(List<OptionProductDto> options_products) {
		this.options_products = options_products;
	}

	
	
}

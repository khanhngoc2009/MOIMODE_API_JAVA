package com.it15306.dto.cart;


import com.it15306.dto.product.ProductSkuDto;

public class CartProductDTO {
	private Integer id;

	private Integer quantity;
	private ProductSkuDto ProductSkuDTOs;
	private CartDTO CartDTO;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the productSkuDTOs
	 */
	public ProductSkuDto getProductSkuDTOs() {
		return ProductSkuDTOs;
	}
	/**
	 * @param productSkuDTOs the productSkuDTOs to set
	 */
	public void setProductSkuDTOs(ProductSkuDto productSkuDTOs) {
		ProductSkuDTOs = productSkuDTOs;
	}
	/**
	 * @return the cartDTO
	 */
	public CartDTO getCartDTO() {
		return CartDTO;
	}
	/**
	 * @param cartDTO the cartDTO to set
	 */
	public void setCartDTO(CartDTO cartDTO) {
		CartDTO = cartDTO;
	}
	
	
	
}

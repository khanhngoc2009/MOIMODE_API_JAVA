package com.it15306.dto.cart;

import java.util.Date;
import java.util.List;


import com.it15306.dto.UserDTO;

public class CartDTO {
	
	private Integer id;
	

	private Date createdate;
	
	private Integer status;

	private UserDTO user;
	
	private List<CartProductDTO> cartProductsDTO;

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
	 * @return the createdate
	 */
	public Date getCreatedate() {
		return createdate;
	}

	/**
	 * @param createdate the createdate to set
	 */
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the user
	 */
	public UserDTO getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(UserDTO user) {
		this.user = user;
	}

	/**
	 * @return the cartProductsDTO
	 */
	public List<CartProductDTO> getCartProductsDTO() {
		return cartProductsDTO;
	}

	/**
	 * @param cartProductsDTO the cartProductsDTO to set
	 */
	public void setCartProductsDTO(List<CartProductDTO> cartProductsDTO) {
		this.cartProductsDTO = cartProductsDTO;
	}


	
}

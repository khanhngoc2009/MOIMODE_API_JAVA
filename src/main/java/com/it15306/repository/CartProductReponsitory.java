package com.it15306.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.it15306.entities.Cart;
import com.it15306.entities.CartProduct;

@Repository
public interface CartProductReponsitory extends JpaRepository<CartProduct, Integer>{
	final String DELETES = "Delete from CartProduct C where C.productSkus.product_sku_id=:idproduct and C.carts.id=:idCart";
	final String SELECTCARTPRODUCT = "SELECT C from CartProduct C where C.productSkus.product_sku_id=:idproduct and C.carts.id=:idCart";
	final String UPDATEQUATITY = "UPDATE   CartProduct C  SET C.quantity=:quantity where C.carts.id=:idCart and C.productSkus.product_sku_id=:idproduct ";
	final String selectCartProuctByUserId = "select cp.id,cp.carts.id,cp.productSkus.product_sku_id,cp.quantity from Cart c join CartProduct cp on cp.carts.id=c.id  where c.user.id=:userId";
	final String selectCartProductByCart = "SELECT C from CartProduct C where C.carts.id=:idCart";
	
	@Transactional
	@Modifying
	@Query(DELETES)
	void deleteLists(@Param("idCart") Integer idCart,@Param("idproduct") Integer idproduct);
	
	@Query(SELECTCARTPRODUCT)
	CartProduct selectCheck(@Param("idCart") Integer idCart,@Param("idproduct") Integer idproduct);
	
	@Transactional
	@Modifying
	@Query(UPDATEQUATITY)
	void updateQuantitys(@Param("quantity") Integer quantity,@Param("idCart") Integer idCart,@Param("idproduct") Integer idproduct);
	
	@Query(value = selectCartProuctByUserId)
	List<CartProduct> selectCartProuctByUserId(@Param("userId") Integer userId);
	
	@Query(selectCartProductByCart)
	List<CartProduct> selectCartProductByCart(@Param("idCart") Integer idCart);
}

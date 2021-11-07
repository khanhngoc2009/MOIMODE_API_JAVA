package com.it15306.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.it15306.entities.Cart;
import com.it15306.entities.CartProductPK;
import com.it15306.entities.User;


@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>  {
	final String SELECT_CART_BY_USERID = "SELECT C FROM Cart  C WHERE C.user.id=:id";
	@Query(SELECT_CART_BY_USERID)
	Cart findUserID(@Param("id") Integer id);

}

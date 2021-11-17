package com.it15306.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;

import com.it15306.entities.AddressOrder;
import com.it15306.entities.District;
import com.it15306.entities.ImageProduct;
import com.it15306.entities.Province;

@Repository
public interface ImageProductRepository extends JpaRepository<ImageProduct, Integer> {
	final String GET_BY_PRODUCT_ID  = " select i from ImageProduct i where i.product.id = :product_id";
	
	@Query(GET_BY_PRODUCT_ID)
	List<ImageProduct> getByProductId(@Param("product_id") Integer product_id);
}

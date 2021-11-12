package com.it15306.repository;


import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;

import com.it15306.entities.Code_Forgot_Password;
import com.it15306.entities.Product;
import com.it15306.entities.Product_Sku;
import com.it15306.entities.Sku;


@Repository
public interface ForgotCodeRepository extends PagingAndSortingRepository<Code_Forgot_Password, Integer>  {
	final String SELECT_BY_EMAIL="select f from Code_Forgot_Password f where f.email=:email AND f.code=:code ";
	
	@Query(SELECT_BY_EMAIL)
	Code_Forgot_Password findByEmail(
			@Param("email") String email,
			@Param("code") Integer code
	);
}

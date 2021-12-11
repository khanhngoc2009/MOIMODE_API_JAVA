package com.it15306.repository;

import java.util.List;
//import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;

import com.it15306.entities.Code_Forgot_Password;
import com.it15306.entities.District;
import com.it15306.entities.Favorite;
import com.it15306.entities.Ward;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {

	final String FIND_BY_USER = "select f from Favorite f where f.product.id =:product_id and f.status=1 order by f.create_time desc";
	
	@Query(FIND_BY_USER)
	Page<Favorite> listFavoriteByProduct(Pageable page,@Param("product_id") Integer product_id);

}

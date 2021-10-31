package com.it15306.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;

import com.it15306.entities.Option_Sku_Value;
import com.it15306.entities.Options;
import com.it15306.entities.Product;
import com.it15306.entities.User;


@Repository
public interface OptionSkuValueRepository extends JpaRepository<Option_Sku_Value, Integer>  {
	
}

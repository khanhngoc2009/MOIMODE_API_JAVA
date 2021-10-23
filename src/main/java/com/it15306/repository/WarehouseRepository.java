package com.it15306.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.it15306.entities.Warehouse;


@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer>  {
	

}

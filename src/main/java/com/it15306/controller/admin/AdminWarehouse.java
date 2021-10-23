package com.it15306.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.it15306.dto.WarehouseDTO;
import com.it15306.services.WarehouseService;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
@RequestMapping("/miemode_api/v1")
public class AdminWarehouse {
	@Autowired
	WarehouseService warehouseService;
	
	@GetMapping("/admim/listWarehouse")
	@ResponseBody
	public ResponseEntity<List<WarehouseDTO>>  getAll() {
	List<WarehouseDTO> list=warehouseService.getAllWarehouses();
		
		if( list.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(list);
	}
}

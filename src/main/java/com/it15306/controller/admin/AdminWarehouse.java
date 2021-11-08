package com.it15306.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.it15306.dto.PageDto;
import com.it15306.dto.WarehouseDTO;
import com.it15306.services.WarehouseService;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200","http://35.198.241.56" })
@RestController
@RequestMapping("/miemode_api/v1")
public class AdminWarehouse {
	@Autowired
	WarehouseService warehouseService;
	
	@PostMapping("/admin/warehouse/list")
	@ResponseBody
	public ResponseEntity<List<WarehouseDTO>>  getAll(@RequestBody PageDto data) {
	List<WarehouseDTO> list=warehouseService.getAllWarehouses();
		
		if( list.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/admin/warehouse/{id}")
	@ResponseBody
	public ResponseEntity<WarehouseDTO>  getById(@PathVariable("id") Integer id) {
	WarehouseDTO warehouseDTO =warehouseService.getByIdWarehouse(id);
		
		if( warehouseDTO == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(warehouseDTO);
	}
	
	@PostMapping("/admim/warehouse/create")
	@ResponseBody
	public ResponseEntity<WarehouseDTO>  create(@RequestBody WarehouseDTO warehouseDTO) {
	WarehouseDTO whDTO =warehouseService.createWarehouse(warehouseDTO);
		
		if( whDTO == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(whDTO);
	}
	
	@PutMapping("/admin/warehouse/update")
	@ResponseBody
	public ResponseEntity<WarehouseDTO>  update(@RequestBody WarehouseDTO warehouseDTO) {
	WarehouseDTO whDTO =warehouseService.updateWarehouse(warehouseDTO);
		
		if( whDTO == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(whDTO);
	}
	
	@DeleteMapping("/admin/warehouse/delete/{id}")
	@ResponseBody
	public ResponseEntity<Integer>  delete(@PathVariable("id") Integer id) {
		Integer idresutl =warehouseService.deleteWarehouse(id);
		
		if( idresutl == 0) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(idresutl);
	}
}

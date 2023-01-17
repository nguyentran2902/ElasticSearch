package com.nguyentran.elasticsearch.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nguyentran.elasticsearch.entity.Laptop;
import com.nguyentran.elasticsearch.service.LaptopService;

@RestController
@RequestMapping("/admin/laptop")
public class LaptopController {

	@Autowired
	private LaptopService laptopService;
	
	//getAll
	@GetMapping("/getAll")
	public ResponseEntity<?> getAll(){
		HashMap<String, Object> map = new HashMap<>();
		try {
			List<Laptop> listPerson = laptopService.getAll();
			if(listPerson==null) {
				map.put("status", true);
				map.put("data", "no content");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
			}
			map.put("status", true);
			map.put("data", listPerson);
			return ResponseEntity.ok(map);
			
		} catch (Exception e) {
			map.put("status", false);
			map.put("data", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
		}		
	}
	
	
	//create
	@PostMapping("/create")
	public ResponseEntity<?> createLaptop(@RequestBody Laptop laptop) {
		HashMap<String, Object> map = new HashMap<>();
		if (laptop == null) {
			map.put("status", false);
			map.put("data", "invalid input???");
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(map);
		}
		
		try {
			Laptop lSave = laptopService.create(laptop);
			if (lSave != null) {
				map.put("status", true);
				map.put("data", lSave);
				return ResponseEntity.ok(map);
			}
			
			map.put("status", false);
			map.put("data", "no content");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
			
		} catch (Exception e) {
			map.put("status", false);
			map.put("data", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
		}
		
					
	}
}

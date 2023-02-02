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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nguyentran.elasticsearch.entity.Laptop;
import com.nguyentran.elasticsearch.service.LaptopService;

@RestController
@RequestMapping("/admin/laptop")
public class LaptopController {

	@Autowired
	private LaptopService laptopService;

	// getAll
	@GetMapping("/getAll")
	public ResponseEntity<?> getAll(@RequestParam(required = true, defaultValue = "1") Integer pageNum,
			@RequestParam(required = true, defaultValue = "6") Integer pageSize) {
		HashMap<String, Object> map = new HashMap<>();
		try {
			List<Laptop> listLaptop = laptopService.getAll(pageNum, pageSize);
			if (listLaptop == null) {
				map.put("status", true);
				map.put("data", "no content");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
			}
			map.put("status", true);
			map.put("data", listLaptop);
			return ResponseEntity.ok(map);

		} catch (Exception e) {
			map.put("status", false);
			map.put("data", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
		}
	}

	// create
	@PostMapping("/create")
	public ResponseEntity<?> createLaptop(@RequestBody Laptop laptop) {
		HashMap<String, Object> map = new HashMap<>();
		if (laptop == null) {
			map.put("status", false);
			map.put("data", "invalid input???");
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(map);
		}

		try {
			Laptop laptopId = laptopService.create(laptop);
			System.out.println(laptopId);
			if (laptopId != null) {
				map.put("status", true);
				map.put("data", laptopId);
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

	// search
	@PostMapping("/search")
	public ResponseEntity<?> searchLaptop(
			@RequestParam(required = true, defaultValue = "1")Integer pageNum,
			@RequestParam(required = true, defaultValue = "6") Integer pageSize,
			@RequestParam(required = false) String query

	) {
		HashMap<String, Object> map = new HashMap<>();
		try {
			List<Laptop> listLaptop = laptopService.searchLaptop(pageNum, pageSize, query);
			if (listLaptop == null) {
				map.put("status", true);
				map.put("data", "no content");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
			}
			map.put("status", true);
			map.put("data", listLaptop);
			return ResponseEntity.ok(map);

		} catch (Exception e) {
			map.put("status", false);
			map.put("data", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
		}
	}

	// filter
	@PostMapping("/filter")
	public ResponseEntity<?> filter(
			@RequestParam(required = true, defaultValue = "1") Integer pageNum,
			@RequestParam(required = true, defaultValue = "6") Integer pageSize,
			@RequestParam(required = false) String[] type,
			@RequestParam(required = false) Integer[] price,
			@RequestParam(required = false) String[] category,
			@RequestParam(required = false) String[] screenSize,
			@RequestParam(required = false) String[] cpu,
			@RequestParam(required = false) String[] ram,
			@RequestParam(required = false) String[] rom) {
		
		HashMap<String, Object> map = new HashMap<>();
		try {
			List<Laptop> listLaptop = laptopService.filter(pageNum, pageSize, type,
					price, category, screenSize, cpu,
					ram,rom);
			if (listLaptop == null) {
				map.put("status", true);
				map.put("data", "no content");
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(map);
			}
			map.put("status", true);
			map.put("data", listLaptop);
			return ResponseEntity.ok(map);

		} catch (Exception e) {
			map.put("status", false);
			map.put("data", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
		}
	}
	
	
	//suggestions
	@PostMapping("/suggestions")
	public List<String> fetchSuggestions(@RequestParam(value = "q", required = false) String query) {                         
        
        List<String> suggests = laptopService.fetchSuggestions(query);
       
        return suggests;
	  }
}

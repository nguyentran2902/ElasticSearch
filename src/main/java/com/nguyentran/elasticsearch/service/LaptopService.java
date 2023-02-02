package com.nguyentran.elasticsearch.service;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;

import org.springframework.stereotype.Service;

import com.nguyentran.elasticsearch.entity.Laptop;
import com.nguyentran.elasticsearch.respository.LaptopReponsitory;

@Service
public class LaptopService {

	@Autowired
	private LaptopReponsitory laptopReponsitory;

	private static final String LAPTOP_INDEX = "laptop";

	@Autowired
	private ElasticsearchOperations elasticsearchOperations;

//	@Autowired
//	private ElasticsearchRestTemplate elasticsearchRestTemplate;

	// get all
	public List<Laptop> getAll(Integer pageNum, Integer pageSize) {

		return laptopReponsitory.getAll(pageNum,pageSize);

	}

	public Laptop create(Laptop laptop) {

		Laptop lapSave = elasticsearchOperations.save(laptop, IndexCoordinates.of(LAPTOP_INDEX));
		return lapSave;

	}

	// search multi field
	public List<Laptop> searchLaptop(Integer pageNum, Integer pageSize, String query) {
		return laptopReponsitory.searchLaptop(pageNum,pageSize,query);
	}

	// filter
	public List<Laptop> filter(Integer pageNum, Integer pageSize, String[] type,
			Integer[] price, String[] category,
			String[] screenSize, String[] cpu, String[] ram, String[] rom) {

		return laptopReponsitory.filter(pageNum, pageSize, type, price, category,
				screenSize, cpu,ram,rom);
	}

	

	// fetchSuggestions: Gợi ý khi gõ tìm kiếm
	public List<String> fetchSuggestions(String query) {

		
		return laptopReponsitory.fetchSuggestions(query);
	}

}

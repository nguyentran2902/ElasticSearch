package com.nguyentran.elasticsearch.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nguyentran.elasticsearch.entity.Laptop;
import com.nguyentran.elasticsearch.respository.LaptopReponsitory;

@Service
public class LaptopService {

	@Autowired
	private LaptopReponsitory laptopReponsitory;

	public List<Laptop> getAll() {
		Iterable<Laptop> iLaptop = laptopReponsitory.findAll();
		List<Laptop> listLaptops = new ArrayList<>();
		iLaptop.forEach(laptop ->{
			listLaptops.add(laptop);
		});
		
		return listLaptops;
		
	}

	public Laptop create(Laptop laptop) {
		return laptopReponsitory.save(laptop);
	}
}

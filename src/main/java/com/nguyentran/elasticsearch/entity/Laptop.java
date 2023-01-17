package com.nguyentran.elasticsearch.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.nguyentran.elasticsearch.entity.laptopObject.CPU;
import com.nguyentran.elasticsearch.entity.laptopObject.Card;
import com.nguyentran.elasticsearch.entity.laptopObject.Category;
import com.nguyentran.elasticsearch.entity.laptopObject.Ram;
import com.nguyentran.elasticsearch.entity.laptopObject.Rom;
import com.nguyentran.elasticsearch.entity.laptopObject.Screen;

import lombok.Data;

@Data
@Document(indexName = "laptop")
public class Laptop {
	
	@Id
	private String id;
	private String name;
	private Category category;
	private double price;
	private String type;
	private Screen screen;
	private CPU cpu;
	private Ram ram;
	private Rom rom;
	private Card card;
	private String system;
	private String pin;
	private List<String> specials;
	private int yearDebut;
	
}

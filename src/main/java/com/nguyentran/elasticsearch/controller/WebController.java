package com.nguyentran.elasticsearch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.nguyentran.elasticsearch.service.LaptopService;

@Controller
public class WebController {

	@Autowired
	private LaptopService laptopService;
	@GetMapping("/search")
	public String search(Model model) {
		return "search";
	}
}

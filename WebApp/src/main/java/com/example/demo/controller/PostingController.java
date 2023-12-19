package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostingController {
	
	@GetMapping("/posting")
	public String view() {
		return "posting";
	}
	
	@PostMapping("/posting")
	public void posting() {
		
	}
}

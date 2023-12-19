package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	
	
	@GetMapping("/")
	public String view() {
		return "home";
	}
	
	@PostMapping("/")
	public void posting() {
	}
}

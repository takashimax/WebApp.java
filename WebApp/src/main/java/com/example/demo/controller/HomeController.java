package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.ItemCategoryInfo;
import com.example.demo.repository.PreCookRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	private final PreCookRepository preCookRepository;

	@GetMapping("/")
	public String view(Model model) {
		List<ItemCategoryInfo> itemCategoryInfos = preCookRepository.findAll();
		model.addAttribute("itemCategoryInfos",itemCategoryInfos); 
		return "home";
	}
	@PostMapping("/")
	public void posting() {
	}
}

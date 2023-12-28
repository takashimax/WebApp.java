package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.ItemCategoryInfo;
import com.example.demo.form.ItemCategoryForm;
import com.example.demo.repository.ItemCategoryRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	private final ItemCategoryRepository itemCategoryRepository;

	@GetMapping("/")
	public String view(Model model) {
		List<ItemCategoryInfo> itemCategoryInfos = itemCategoryRepository.findAll();
		model.addAttribute("itemCategoryInfos",itemCategoryInfos); 
		return "home";
	}
	@PostMapping("/")
	public String posting(ItemCategoryForm itemCategoryform, RedirectAttributes redirectAttributes) {
		String itemCategoryInfo = itemCategoryform.getItemName();
		redirectAttributes.addFlashAttribute("itemCategoryInfo", itemCategoryRepository.findByItemName(itemCategoryInfo));
		return "precook";
	}

}

package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.PostingInfo;
import com.example.demo.service.PostingService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PostingController {
	private final PostingService postingService;

	@GetMapping("/posting")
	public String view(Model model) {
		List<PostingInfo> postingList = postingService.findAll();
		model.addAttribute("postingList", postingList);
		return "posting";
	}

	@PostMapping("/posting")
	public void posting() {
		
	}
}

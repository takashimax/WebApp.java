package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.ItemCategoryInfo;
import com.example.demo.entity.ItemDetailInfo;
import com.example.demo.repository.ItemCategoryRepository;
import com.example.demo.repository.ItemDetailRepository;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class PrecookController {
	
	private final ItemCategoryRepository itemCategoryRepository;
	private final ItemDetailRepository itemDetailRepository;
	
	@GetMapping("/precook/{itemName}")
	public String view(@PathVariable(name = "itemName") String itemName, Model model, RedirectAttributes redirectAttributes) {
		/*
		 * アイテムカテゴリーの品目名の取得
		 */
		Optional<ItemCategoryInfo> itemCategoryOpt = itemCategoryRepository.findByItemName(itemName);
		model.addAttribute("itemCategoryOpt",itemCategoryOpt );
		/*
		 * アイテムカテゴリーの品目名からdetailテーブルの情報を取得
		 */
		List<ItemDetailInfo> itemDetailList = itemDetailRepository.findByItemCategoryInfo(itemCategoryOpt);
		model.addAttribute("itemDetailList", itemDetailList);
		return "precook";
	}
	
	@GetMapping("/precook/{urlPath}")
	
	public String serchview(RedirectAttributes redirectAttributes, String urlPath) {
		redirectAttributes.getFlashAttributes();
		return "precook";
	}
	
}

package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.ItemCategoryInfo;
import com.example.demo.entity.ItemDetailInfo;
import com.example.demo.repository.ItemDetailRepository;
import com.example.demo.repository.PreCookRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PrecookController {
	
	private final PreCookRepository preCookRepository;
	private final ItemDetailRepository itemDetailRepository;
	private final ItemCategoryInfo itemCategoryInfo;
	
	@GetMapping("/precook/{itemName}")
	public String view(@PathVariable(name = "itemName") String itemName, Model model) {
		/*
		 * アイテムカテゴリーの品目名の取得
		 */
		Optional<ItemCategoryInfo> itemCategoryOpt = preCookRepository.findByItemName(itemName);
		model.addAttribute("itemCategoryOpt",itemCategoryOpt );
		/*
		 * アイテムカテゴリーのIDからdetailテーブルの情報を取得
		 */
		model.addAttribute("itemId", itemCategoryInfo.getItemId());
		
		List<ItemDetailInfo> itemDetailList = itemDetailRepository.findAll();
		model.addAttribute("itemDetailList", itemDetailList);
		return "precook";
	}
}

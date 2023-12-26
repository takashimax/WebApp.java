package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ItemCategoryInfo;

@Repository
public interface PreCookRepository extends
		JpaRepository<ItemCategoryInfo, Integer> {
	public Optional<ItemCategoryInfo> findByItemName(String itemName);
}

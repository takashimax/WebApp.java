package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.repository.ItemDetailRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ItemDetailService {
	
	private final ItemDetailRepository itemDetailRepository;
	
}

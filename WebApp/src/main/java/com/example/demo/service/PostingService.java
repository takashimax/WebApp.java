package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.PostingInfo;
import com.example.demo.repository.PostingRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PostingService {

	private final PostingRepository postingRepository;
	
	public List<PostingInfo> findAll() {
		return postingRepository.findAll();
	}
}

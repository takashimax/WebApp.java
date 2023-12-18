package com.example.demo.service;

import java.util.Optional;

import org.dozer.Mapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserInfo;
import com.example.demo.form.SignupForm;
import com.example.demo.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SignupService {

	private final UserInfoRepository userInfoRepository;
	private final Mapper mapper;
	private final PasswordEncoder passwordEncoder;
	
	public Optional<Object> signupUser(SignupForm signupForm) {
		Optional<UserInfo> userInfoExisted = userInfoRepository.findById(signupForm.getEmail());
		if(userInfoExisted.isPresent()) {
			return Optional.empty();
		}
		UserInfo userInfo = mapper.map(signupForm, UserInfo.class);
		String encodedPassword = passwordEncoder.encode(signupForm.getPassword());
		userInfo.setPassword(encodedPassword);
		return Optional.of(userInfoRepository.save(userInfo));
	}
}
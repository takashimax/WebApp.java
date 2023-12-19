package com.example.demo.service;

import java.util.Optional;

import org.dozer.Mapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserInfo;
import com.example.demo.form.UserForm;
import com.example.demo.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserInfoRepository userInfoRepository;
	private final Mapper mapper;
	private final PasswordEncoder passwordEncoder;

	public Optional<UserInfo> serchUser(String email) {
		return userInfoRepository.findByEmail(email);
	}

	public Optional<Object> signupUser(UserForm userForm) {
		Optional<UserInfo> userInfoExisted = userInfoRepository.findByEmail(userForm.getEmail());
		if (userInfoExisted.isPresent()) {
			return Optional.empty();
		}
		UserInfo userInfo = mapper.map(userForm, UserInfo.class);
		String encodedPassword = passwordEncoder.encode(userForm.getPassword());
		userInfo.setPassword(encodedPassword);
		return Optional.of(userInfoRepository.save(userInfo));
	}
}

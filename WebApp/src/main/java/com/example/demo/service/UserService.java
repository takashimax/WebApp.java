package com.example.demo.service;

import java.util.Optional;

import org.dozer.Mapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.UserInfo;
import com.example.demo.form.UserForm;
import com.example.demo.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
	private final UserInfoRepository userInfoRepository;
	private final Mapper mapper;
	private final PasswordEncoder passwordEncoder;

	public boolean serchUser(UserForm userForm) {
		Optional<UserInfo> userInfo = userInfoRepository.findByEmail(userForm.getEmail());
		boolean isCorrectUserAuth = userInfo.isPresent()
				&& passwordEncoder.matches(userForm.getPassword(), userInfo.get().getPassword());
		if (isCorrectUserAuth) {
			return true;
		}else {
			return false;
		}
	}

	public Optional<UserInfo> signupUser(UserForm userForm) {
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

package com.example.demo.controller;

import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.MessageConst;
import com.example.demo.form.SignupForm;
import com.example.demo.service.SignupService;
import com.example.demo.util.AppUtil;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SignupController {

	private final SignupService signupService;
	private final MessageSource messageSource;

	@GetMapping("/signup")
	public String view(Model model, SignupForm signupForm) {
		return "signup";
	}

	@PostMapping("/signup")
	public void signup(Model model, SignupForm signupForm) {
		var userInfoOpt = signupService.signupUser(signupForm);
		String message = AppUtil.getMessage(messageSource, getMessageKey(userInfoOpt));
		model.addAttribute("message", message);

	}
	
	private String getMessageKey(Optional<Object> userInfoOpt) {
		if(userInfoOpt.isEmpty()) {
			return MessageConst.SIGNUP_EXISTED_LOGIN_ID;
		}else {
			return MessageConst.SIGNUP_SUCCEED;
		}
	}
}

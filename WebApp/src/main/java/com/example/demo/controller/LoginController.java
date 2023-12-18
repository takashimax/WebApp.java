package com.example.demo.controller;

import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.MessageConst;
import com.example.demo.entity.UserInfo;
import com.example.demo.form.LoginForm;
import com.example.demo.service.LoginService;
import com.example.demo.util.AppUtil;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {

	private final LoginService loginService;

	private final PasswordEncoder passwordEncoder;
	
	private final MessageSource messageSource;
	@GetMapping("/login")
	public String view(Model model, LoginForm loginForm) {
		return "login";
	}

	@PostMapping("login")
	public String login(Model model, LoginForm loginForm) {
		Optional<UserInfo> userInfo = loginService.serchUser(loginForm.getEmail());
		boolean isCorrectUserAuth = userInfo.isPresent()
				&& passwordEncoder.matches(loginForm.getPassword(), userInfo.get().getPassword());
		if(isCorrectUserAuth) {
			return "redirect:/menu";
			
		}else {
			String message = AppUtil.getMessage(messageSource, MessageConst.LOGIN_WRONG_INPUT);
			model.addAttribute("message", message);
			return "login";
		}
	}
}

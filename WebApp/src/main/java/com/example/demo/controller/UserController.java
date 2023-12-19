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
import com.example.demo.form.UserForm;
import com.example.demo.service.UserService;
import com.example.demo.util.AppUtil;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	
	private final MessageSource messageSource;
	@GetMapping("/login")
	public String viewLogin(Model model, UserForm userForm) {
		return "login";
	}

	@PostMapping("/login")
	public String login(Model model, UserForm userForm) {
		Optional<UserInfo> userInfo = userService.serchUser(userForm.getEmail());
		boolean isCorrectUserAuth = userInfo.isPresent()
				&& passwordEncoder.matches(userForm.getPassword(), userInfo.get().getPassword());
		if(isCorrectUserAuth) {
			return "redirect:/home";
			
		}else {
			String message = AppUtil.getMessage(messageSource, MessageConst.LOGIN_WRONG_INPUT);
			model.addAttribute("message", message);
			return "login";
		}
	}
	
	@GetMapping("/signup")
	public String viewSignup(Model model, UserForm userForm) {
		return "signup";
	}

	@PostMapping("/signup")
	public void signup(Model model, UserForm userForm) {
		var userInfoOpt = userService.signupUser(userForm);
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

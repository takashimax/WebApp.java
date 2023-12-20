package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
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
	private final MessageSource messageSource;

	@GetMapping("/login")
	public String viewLogin(Model model, UserForm userForm) {
		return "login";
	}

	@PostMapping("/login")
	public String login(Model model, @Validated UserForm userForm, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : bindingResult.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError", errorList);
			return "login";
		} else {
			boolean result = userService.serchUser(userForm);
			if (result) {
				return "redirect:/";
			} else {
				String message = AppUtil.getMessage(messageSource, MessageConst.LOGIN_WRONG_INPUT);
				model.addAttribute("message", message);
				return "login";
			}
		}
	}

	@GetMapping("/signup")
	public String viewSignup(Model model, UserForm userForm) {
		return "signup";
	}

	@PostMapping("/signup")
	public String signup(Model model, @Validated UserForm userForm, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for (ObjectError error : bindingResult.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError", errorList);
			return "signup";
		} else {
			Optional<UserInfo> userInfoOpt = userService.signupUser(userForm);
			String message = AppUtil.getMessage(messageSource, getMessageKey(userInfoOpt));
			model.addAttribute("message", message);
			return "redirect:/login";
		}
	}

	private String getMessageKey(Optional<UserInfo> userInfoOpt) {
		if (userInfoOpt.isEmpty()) {
			return MessageConst.SIGNUP_EXISTED_LOGIN_ID;
		} else {
			return MessageConst.SIGNUP_SUCCEED;
		}
	}
}

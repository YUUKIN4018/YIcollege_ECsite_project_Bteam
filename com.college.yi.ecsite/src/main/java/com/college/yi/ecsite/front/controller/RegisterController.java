package com.college.yi.ecsite.front.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.college.yi.ecsite.front.form.RegisterForm;
import com.college.yi.ecsite.front.service.RegisterService;

import jakarta.validation.Valid;

@Controller
public class RegisterController {

	@Autowired
	private RegisterService registerService;

	@GetMapping("/register")
	public String mypage() {
		return "mypage";
	}

	@PostMapping("/register")
	public String registerUser(@ModelAttribute("registerForm") @Valid RegisterForm registerForm,
			BindingResult bindingResult, Model model) {
		if (!registerForm.getPassword().equals(registerForm.getPasswordConfirm())) {
			bindingResult.rejectValue("password_confirm", "error.pass_confirm", "パスワードが一致しません");

		}
		if (bindingResult.hasErrors()) {
			return ("/front/register");

		}
		registerService.registerUser(registerForm);

		return ("/redirect/login");

	}
}

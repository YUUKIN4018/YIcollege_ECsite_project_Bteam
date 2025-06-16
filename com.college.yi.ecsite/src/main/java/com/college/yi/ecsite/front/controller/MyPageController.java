package com.college.yi.ecsite.front.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import com.college.yi.ecsite.entity.User;
import com.college.yi.ecsite.front.dto.MyPageDto;
import com.college.yi.ecsite.front.service.MyPageService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MyPageController {

	@Autowired
	private MyPageService myPageService;

	@GetMapping("/mypage")
	public String showMyPage(HttpSession session, Model model) {

		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "redirect:/login";

		}

		MyPageDto dto = myPageService.getUserInfoForMyPage(loginUser.getUser_id());
		model.addAttribute("userInfo", dto);

		return "/front/mypage";

	}

}

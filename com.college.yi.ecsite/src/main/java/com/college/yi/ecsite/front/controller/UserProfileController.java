package com.college.yi.ecsite.front.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.college.yi.ecsite.EcsiteApplication;
import com.college.yi.ecsite.entity.User;
import com.college.yi.ecsite.exception.DuplicateEmailException;
import com.college.yi.ecsite.front.form.UserProfileForm;
import com.college.yi.ecsite.front.service.UserProfileService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller //コントローラー
public class UserProfileController {

	private final EcsiteApplication ecsiteApplication;

	@Autowired
	private UserProfileService userProfileService;

	UserProfileController(EcsiteApplication ecsiteApplication) {
		this.ecsiteApplication = ecsiteApplication;
	}

	@GetMapping("/profile/edit")
	public String showEditProfile(HttpSession session, Model model) {
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "redirect:/login";

		}
		UserProfileForm form = userProfileService.getUserProfileForm(loginUser.getUser_id());
		model.addAttribute("userProfileForm", form);

		return "/front/profile/edit";

	}

	@PostMapping("/profile/edit")
	public String updateUserProfile(@ModelAttribute("userProfileForm") @Valid UserProfileForm form,
			BindingResult bindingResult, HttpSession session, RedirectAttributes redirectAttributes) {
		User loginUser = (User) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "redirect:/login";

		}
		if (bindingResult.hasErrors()) {
			return "/front/profile/edit";

		}

		try {
			userProfileService.updateUserProfile(form, loginUser.getUser_id());

			redirectAttributes.addFlashAttribute("updateMessage", "ユーザー情報を更新しました");
			return "redirect:/mypage";

		} catch (DuplicateEmailException e) {

			bindingResult.rejectValue("email", "duplicate", e.getMessage());
			return "/front/profile/edit";

		}

	}

}

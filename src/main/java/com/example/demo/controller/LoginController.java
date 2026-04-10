package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.DTO.MemberInfoDTO;
import com.example.demo.service.MemberInfoService;


@Controller
public class LoginController {

	@Autowired
	private MemberInfoService memberInfoService;
	
	//로그인 화면 열기
	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	
		//로그인 처리
	@PostMapping("/login")
	public String loginCheck4(
			String memberId,
			String password,
			Model model) {
		MemberInfoDTO member
			= memberInfoService.loginCheck(memberId, password);
		
		if(member != null) {
			
			//로그인 성공
			return "menu";
		} else {
			
			//로그인 실패
			model.addAttribute("errorMessage","IDまたはパスワードが違います");
			return "login";
		}
	}
	
	
	
	
	
	
	
}

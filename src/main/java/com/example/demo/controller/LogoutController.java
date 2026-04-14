package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class LogoutController {
	
	// Logger 추가하기
	private static final Logger logger = 
	        LoggerFactory.getLogger(LogoutController.class);

	@GetMapping("/logout")
	public String logout(HttpSession session){

	    // 삭제전 user_id 저장
	    Object beforeUserId = session.getAttribute("user_id");
	    
	    // 세션 전부 삭제
	    session.invalidate();
	    
	    // 에비던스용 로그
	    logger.info("セッション情報を削除しました");
	    logger.info("session.user_id: null (before={})", beforeUserId);
	    
	    // 로그인 화면으로 이동
	    return "redirect:/login";
	}

}
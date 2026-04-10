package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {

	//메뉴 화면 표시
	@GetMapping("/menu")
	public String showMenu() {
		
		return "menu"; //menu.jsp로 이동한다.
	}
	
}

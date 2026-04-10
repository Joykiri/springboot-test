package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class GameController {

	@GetMapping("/game")
	public String initGame(
	        HttpSession session) {

	    System.out.println(
	        "user_name=" +
	        session.getAttribute("user_name")
	    );

	    return "game";
	}
	
}
package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {

    @GetMapping("/logout")
    public String logout(HttpSession session){

        // 세션 전부 삭제
        session.invalidate();

        // 로그인 화면으로 이동
        return "redirect:/login";
    }

}
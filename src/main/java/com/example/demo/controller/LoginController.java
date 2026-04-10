package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.DTO.UserDTO;
import com.example.demo.service.LoginService;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    // 로그인 화면 표시
    @GetMapping("/login")
    public String showLoginPage() {

        return "login";
    }

    // 로그인 처리
    @PostMapping("/login")
    public String login(
            @RequestParam("memberId") String memberId,
            @RequestParam("password") String password,
            HttpSession session,
            Model model) {

        UserDTO userDTO = new UserDTO();

        // =========================
        // ID 체크
        // =========================

        // 101 ID 미입력
        if(memberId == null || memberId.isEmpty()) {

            model.addAttribute("err_msg",
                    "会員IDを入力してください。");
            return "login";
        }

        // 102 ID 자리수
        if(memberId.length() != 6) {

            model.addAttribute("err_msg",
                    "会員IDは6桁で入力してください。");
            return "login";
        }

        // 104 ID 형식
        if(!memberId.matches("^[0-9]+$")) {

            model.addAttribute("err_msg",
                    "会員IDは半角数字以外は入力できません。");
            return "login";
        }

        // =========================
        // PASSWORD 체크
        // =========================

        // 103 PW 미입력
        if(password == null || password.isEmpty()) {

            model.addAttribute("err_msg",
                    "パスワードを入力してください。");
            return "login";
        }

        // 105 PW 자리수
        if(password.length() != 8) {

            model.addAttribute("err_msg",
                    "パスワードは8文字で入力してください。");
            return "login";
        }

        // 106 PW 형식 재차수정중
        if(!password.matches("^[0-9a-zA-Z]+$")) {

            model.addAttribute("err_msg",
                    "パスワードは半角英数字以外は入力できません。");
            return "login";
        }

        // =========================
        // DB 로그인 처리
        // =========================

        userDTO.setUser_id(memberId);
        userDTO.setPassword(password);

        userDTO = loginService.login(userDTO);

        if(userDTO.isSuccess()) {

            session.setAttribute("user_id",
                    userDTO.getUser_id());

            session.setAttribute("user_name",
                    userDTO.getUser_name());

            return "redirect:/menu";

        } else {

            // 🔥 107 / 108 제대로 출력됨
            model.addAttribute("err_msg",
                    userDTO.getErr_msg());

            return "login";
        }
    }
}
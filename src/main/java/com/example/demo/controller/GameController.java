package com.example.demo.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import com.example.demo.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GameController {

	@Autowired
	private GameService gameService;

	// ===== 1. 게임 화면 처음 열기 =====
	@GetMapping("/game")
	public String initGame(
	        HttpSession session,
	        Model model) {

	    Integer point =
	        (Integer)session.getAttribute("point");

	    if(point == null){
	        point = 0;
	        session.setAttribute("point", point);
	    }

	    model.addAttribute("point", point);

	    return "game";
	}



	// ===== 2. 게임 실행 =====
	@PostMapping("/game")
	public String playGame(

			@RequestParam("input_num0") String n0,
			@RequestParam("input_num1") String n1,
			@RequestParam("input_num2") String n2,

			HttpSession session,
			Model model) {

		// 입력 숫자 합치기
		String input = n0 + n1 + n2;
		
		
		// ERROR 메세지 종류별!!! 되자아~
		// 204: 아무것도 안 넣었거나 문자 입력 (영문/히라가나/카타카나)
		if(
			(n0.equals("") && n1.equals("") && n2.equals("")) ||

			n0.matches("[A-Za-zぁ-んァ-ヶ].*") ||
			n1.matches("[A-Za-zぁ-んァ-ヶ].*") ||
			n2.matches("[A-Za-zぁ-んァ-ヶ].*")
		){
			model.addAttribute("errMsg","数字を入力してください。");
			return "game";
		}


		// 201: 전각 숫자
		if(
			n0.matches("[０-９]") ||
			n1.matches("[０-９]") ||
			n2.matches("[０-９]")
		){
			model.addAttribute("errMsg","半角数字を入力してください。");
			return "game";
		}

		
		// 202: 3칸 다 입력 안 된 경우 (1칸/2칸 입력)
		if(
			n0.equals("") ||
			n1.equals("") ||
			n2.equals("")
		){
			model.addAttribute("errMsg","3桁の数字を入力してください。");
			return "game";
		}


		// 203: 숫자 중복 체크
		if(
			n0.equals(n1) ||
			n0.equals(n2) ||
			n1.equals(n2)
		){
			model.addAttribute("errMsg","異なる桁に同じ数字は入力できません。");
			return "game";
		}
		// 숨은 숫자 가져오기
		String hidden =
			(String)session.getAttribute("hidden");

		if(hidden == null){
			hidden =
				gameService.createHiddenNumber();

			session.setAttribute(
				"hidden",
				hidden
			);
		}

		// ===== 게임 횟수 =====
		Integer count =
			(Integer)session.getAttribute("count");

		if(count == null){
			count = 0;
		}

		count++;

		session.setAttribute("count", count);


		// ===== 판정 =====
		String result =
			gameService.judgeNumber(
				input,
				hidden
			);


		// ===== 리스트 처리 =====
		List<String> inputList =
			(List<String>)session.getAttribute("inputList");

		if(inputList == null){
			inputList = new ArrayList<>();
		}

		List<String> resultList =
			(List<String>)session.getAttribute("resultList");

		if(resultList == null){
			resultList = new ArrayList<>();
		}

		inputList.add(input);
		resultList.add(result);

		session.setAttribute("inputList", inputList);
		session.setAttribute("resultList", resultList);

		model.addAttribute("inputList", inputList);
		model.addAttribute("resultList", resultList);


		// ===== 성공 체크 =====
		if(result.equals("あたり")){

			int addPoint = 0;
			String msg = "";

			if(count <= 5){
				addPoint = 1000;
				msg = "挑戦に成功！1000ポイント！";
			}
			else if(count <= 7){
				addPoint = 500;
				msg = "挑戦に成功！500ポイント！";
			}
			else{
				addPoint = 200;
				msg = "挑戦に成功！200ポイント！";
			}

			Integer currentPoint =
				(Integer)session.getAttribute("point");

			if(currentPoint == null){
				currentPoint = 0;
			}

			currentPoint += addPoint;

			session.setAttribute("point",currentPoint);

			model.addAttribute("point",currentPoint);

			model.addAttribute("errMsg",msg);

			session.setAttribute("gameEnd",true);

			return "game";
		}


		// ===== 10회 실패 =====
		if(count >= 10){

			model.addAttribute("errMsg","挑戦に失敗しました…");

			session.setAttribute("gameEnd",true);
			
			
			// ⭐ 실패시에도 point 전달
			
			Integer currentPoint =
				(Integer)session.getAttribute("point");

			model.addAttribute(
				"point",
				currentPoint
			);
			return "game";
		}
		
		return "game";
	}
	// ⭐ 시스템 에러 로그 (002)
	@ExceptionHandler(Exception.class)
	public String handleException(Exception e){
		System.out.println("[002] システムエラーが発生しました。");
		e.printStackTrace();
		return "game";
	}

	}


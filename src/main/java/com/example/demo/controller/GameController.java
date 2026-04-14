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

        String userId =
            (String)session.getAttribute("user_id");

        Integer point =
            (Integer)session.getAttribute("point");

        if(point == null){

            point =
                gameService.getPoint(userId);

            session.setAttribute(
                "point",
                point
            );
        }

        model.addAttribute(
            "point",
            point
        );

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

        // =========================
        // ⭐ 총 게임 종료 체크 추가
        // =========================
        Integer gameRound =
            (Integer)session.getAttribute("gameRound");

        if(gameRound == null){
            gameRound = 1;
            session.setAttribute(
                "gameRound",
                gameRound
            );
        }

        if(gameRound > 10){

            model.addAttribute(
                "errMsg",
                "今日のゲームを終了します。"
            );

            session.setAttribute(
                "gameEnd",
                true
            );

            return "game";
        }



        // ===== 입력 숫자 합치기 =====
        String input =
            n0 + n1 + n2;


        // ===== ERROR 204 =====
        if(
            (n0.equals("") && n1.equals("") && n2.equals("")) ||

            n0.matches("[A-Za-zぁ-んァ-ヶ].*") ||
            n1.matches("[A-Za-zぁ-んァ-ヶ].*") ||
            n2.matches("[A-Za-zぁ-んァ-ヶ].*")
        ){
            model.addAttribute(
                "errMsg",
                "数字を入力してください。"
            );
            return "game";
        }


        // ===== ERROR 201 =====
        if(
            n0.matches("[０-９]") ||
            n1.matches("[０-９]") ||
            n2.matches("[０-９]")
        ){
            model.addAttribute(
                "errMsg",
                "半角数字を入力してください。"
            );
            return "game";
        }


        // ===== ERROR 202 =====
        if(
            n0.equals("") ||
            n1.equals("") ||
            n2.equals("")
        ){
            model.addAttribute(
                "errMsg",
                "3桁の数字を入力してください。"
            );
            return "game";
        }


        // ===== ERROR 203 =====
        if(
            n0.equals(n1) ||
            n0.equals(n2) ||
            n1.equals(n2)
        ){
            model.addAttribute(
                "errMsg",
                "異なる桁に同じ数字は入力できません。"
            );
            return "game";
        }



        // ===== 숨은 숫자 =====
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

        session.setAttribute(
            "count",
            count
        );



        // ===== 판정 =====
        String result =
            gameService.judgeNumber(
                input,
                hidden
            );

     // ===== DB 저장 추가 ⭐⭐⭐
        String memberId =
            (String)session.getAttribute("user_id");

        java.sql.Date today =
            new java.sql.Date(
                System.currentTimeMillis()
            );

        gameService.insertResult(
            memberId,
            today,
            count,
            input,
            result
        );

        // ===== 리스트 =====
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

        session.setAttribute(
            "inputList",
            inputList
        );

        session.setAttribute(
            "resultList",
            resultList
        );

        model.addAttribute(
            "inputList",
            inputList
        );

        model.addAttribute(
            "resultList",
            resultList
        );



        // ===== 성공 =====
        if(result.equals("あたり")){

            int addPoint = 0;
            String msg = "";

            if(count <= 5){
                addPoint = 1000;
                msg =
                    "挑戦に成功！1000ポイント！";
            }
            else if(count <= 7){
                addPoint = 500;
                msg =
                    "挑戦に成功！500ポイント！";
            }
            else{
                addPoint = 200;
                msg =
                    "挑戦に成功！200ポイント！";
            }


            Integer currentPoint =
                (Integer)session.getAttribute("point");

            if(currentPoint == null){
                currentPoint = 0;
            }

            currentPoint += addPoint;

            session.setAttribute(
                "point",
                currentPoint
            );

            // ⭐ DB 포인트 저장
            gameService.updatePoint(
                memberId,
                currentPoint
            );


            model.addAttribute(
                "point",
                currentPoint
            );

            model.addAttribute(
                "errMsg",
                msg
            );

            session.setAttribute(
                "gameEnd",
                true
            );

            return "game";
        }



        // ===== 10회 실패 =====
        if(count >= 10){

            model.addAttribute(
                "errMsg",
                "挑戦に失敗しました…"
            );

            session.setAttribute(
                "gameEnd",
                true
            );

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



    // ===== 新ゲーム =====
    @GetMapping("/newGame")
    public String newGame(
            HttpSession session){

        // ⭐ 총 게임 수 증가
        Integer gameRound =
            (Integer)session.getAttribute("gameRound");

        if(gameRound == null){
            gameRound = 1;
        }

        gameRound++;

        session.setAttribute(
            "gameRound",
            gameRound
        );


        // 기존 초기화
        session.removeAttribute("hidden");
        session.removeAttribute("count");
        session.removeAttribute("inputList");
        session.removeAttribute("resultList");
        session.removeAttribute("gameEnd");

        return "redirect:/game";
    }



    // ===== 에러 처리 =====
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e){

        System.out.println(
            "[002] システムエラーが発生しました。"
        );

        e.printStackTrace();

        return "game";
    }

}
package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.demo.mapper.GameDAO;
import com.example.demo.mapper.ResultMapper;

@Service
public class GameService {
	
	private static final Logger logger =
			LoggerFactory.getLogger(GameService.class);
	
	@Autowired
	private GameDAO gameDAO;

	@Autowired
	private ResultMapper resultMapper;
	
	
	// =========================
	// 숨은 숫자 생성(중복 없이 3자리)
	public String createHiddenNumber() {

		try{

			String num = "";

			while(num.length() < 3) {

				int rand =
					(int)(Math.random() * 10);

				String r = String.valueOf(rand);

				// 중복 방지
				if(!num.contains(r)) {
					num += r;

				}
			}

			logger.info(
				"生成された隠し数字 = {}",num);

			return num;

		}catch(Exception e){

			// 001
			logger.error(
				"[001] データベースへの接続に失敗しました。",e);
			throw e;
		}
	}


	// 게임 판정 (S/B 계산)
	// =========================
	public String judgeNumber(
			String input,
			String hidden) {

		try{

			int strike = 0;
			int ball = 0;

			for(int i = 0; i < 3; i++) {

				char in =
					input.charAt(i);

				if(in == hidden.charAt(i)) {
					strike++;

				}
				else if(
					hidden.contains(
						String.valueOf(in))
				){
					ball++;
				}
			}

			// 결과 생성
			if(strike == 3){
				return "あたり";
			}
			else if(
				strike == 0 &&
				ball == 0
			){
				return "はずれ";

			}
			else{
				return
					strike + "S"
					+ ball + "B";
			}

		}catch(Exception e){

			// 002
			logger.error(
				"002 システムエラーが発生しました。",e);
			throw e;
		}
	}

	// 오늘 게임 했는지 확인
	// =========================
	public boolean hasTodayGame(
			String memberId) {

		try{
			int count =	resultMapper.countTodayGame(memberId);
			return count > 0;

		}catch(Exception e){
			logger.error("003 当日のゲーム確認中にエラーが発生しました。",e);
			throw e;
		}
	}

	// 어제 게임 했는지 확인
	// =========================
	public boolean hasYesterdayGame(
			String memberId) {

		try{

			int count =
				resultMapper
				.countYesterdayGame(memberId);

			return count > 0;

		}catch(Exception e){

			logger.error("004 前日のゲーム確認中にエラーが発生しました。",e);
			throw e;
		}
	}


	// 오늘 게임 횟수 확인
	// =========================
	public int getTodayGameCount(
			String memberId) {

		try{

			return resultMapper
					.countTodayGame(memberId);

		}catch(Exception e){

			logger.error("005 当日のゲーム件数取得中にエラーが発生しました。",e);
			throw e;
		}
	}

	// 결과 저장
	// =========================
	public void insertResult(
			String memberId,
			java.sql.Date playDate,
			int gameCount,
			String inputNum,
			String result){

		try{

			resultMapper.insertResult(
				memberId,
				playDate,
				gameCount,
				inputNum,
				result
			);

		}catch(Exception e){

			logger.error("006 結果保存中にエラーが発生しました。",	e);
			throw e;
		}
	}


	// ⭐ 포인트 조회 (이번에 추가)
	public Integer getPoint(
			String memberId){

		try{

			Integer point =	resultMapper.getPoint(memberId);

			if(point == null){
				point = 0;
			}

			return point;

		}catch(Exception e){
			logger.error("007 ポイント取得中にエラーが発生しました。",e);
			throw e;
		}
	}
	// =========================
	// 포인트 업데이트
	// =========================
	public void updatePoint(
	        String memberId,
	        int point){

	    try{

	        resultMapper.updatePoint(
	            memberId,
	            point
	        );

	    }catch(Exception e){

	        logger.error(
	            "008 ポイント更新中にエラーが発生しました。",
	            e
	        );
	        throw e;
	    }
	}
	// ⭐ 새 게임 초기화
	public void initGame(
	        jakarta.servlet.http.HttpSession session){

	    try{

	        // 이전 기록 제거
	        session.removeAttribute("history");
	        // 게임 종료 상태 초기화
	        session.removeAttribute("gameEnd");
	        // 새 숨은 숫자 생성
	        String hiddenNumber =
	                createHiddenNumber();
	        // 세션에 저장
	        session.setAttribute("hiddenNumber",hiddenNumber);

	        logger.info("新ゲーム生成 = {}",hiddenNumber);

	    }catch(Exception e){

	        logger.error("008 新ゲーム初期化中にエラーが発生しました。",e);

	        throw e;
	    }
	}
}
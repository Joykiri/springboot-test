package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.example.demo.mapper.GameDAO;
import com.example.demo.DTO.GameDTO;

@Service
public class GameService {
	
	private static final Logger logger =
			LoggerFactory.getLogger(GameService.class);
	
	@Autowired
	private GameDAO gameDAO;
	
	//숨은 숫자 생성(중복 없이 3자리)
	public String createHiddenNumber() {
		try{
			
			String num = "";
				while(num.length() < 3) {
			int rand = (int)(Math.random() * 10);
			String r = String.valueOf(rand);
			
			//중복방지
			if(!num.contains(r)) {
				num += r;
			}
		}
		logger.info("生成された隠し数字 = {}", num);
		return num;
	} catch(Exception e) {

		// 001
		logger.error(
			"[001] データベースへの接続に失敗しました。",
			e
		);

		throw e;
	}
}

	//게임 판정(S/B 계산)
	public String judgeNumber(String input, String hidden) {
		try{
			int strike = 0;
			int ball = 0;
		
			for(int i = 0; i< 3; i++) {
					char in = input.charAt(i);
					if(in == hidden.charAt(i)) {
						strike++;
			} else if(hidden.contains(String.valueOf(in))) {
						ball++;
				}
		}	
			//결과를 생성한다 이렇게!
			if(strike == 3) {
				return "あたり";
			} else if (strike == 0 && ball == 0) {
				return "はずれ";
			} else {
				return strike + "S" + ball + "B";
			}
			
		}
		catch(Exception e){

			// ⭐ 002 로그
			logger.error("002 システムエラーが発生しました。",e	);
			throw e;
		}
	}

}
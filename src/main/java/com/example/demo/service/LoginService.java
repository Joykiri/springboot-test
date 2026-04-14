package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.UserDTO;
import com.example.demo.mapper.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class LoginService {

	@Autowired
	private UserDAO userDAO;
	
	private static final Logger logger =
		LoggerFactory.getLogger(LoginService.class);
	
	public UserDTO login(UserDTO userDTO) {
		
		try {
			// ID + PW 같이 확인
			int count = userDAO.getUser(userDTO);
			userDTO.setCount(count);
			
			if(count ==1) {
				
				userDTO.setSuccess(true);
				String userName = userDAO.getUserName(userDTO);
				userDTO.setUser_name(userName);
				
			}else {// 108 구분용
				
				String dbPassword =
					userDAO.getPassword(userDTO);
				
				if(dbPassword == null) {
					// ID 없음
				
					userDTO.setSuccess(false);
					userDTO.setErr_msg("会員IDを確認してください。");

					// ⭐ 여기 로그 추가
					logger.warn("登録された会員情報ではありません。");
				
				}else {
					// PW 틀림
				
					userDTO.setSuccess(false);
					userDTO.setErr_msg("入力したパスワードが一致しません。");

					// ⭐ 여기 로그 추가
					logger.warn("パスワードが一致しません。");
				}
			}
		}catch(Exception e) {
			
			userDTO.setSuccess(false);
			userDTO.setErr_msg("システムエラーが発生しました。");
			
			
			// ⭐ 접속 실패 메시지
		    logger.warn("データベースの接続に失敗しました");
			// ⭐ 시스템 에러 로그
			logger.error("システムエラーが発生しました。", e);
		}

		return userDTO;
	}
}
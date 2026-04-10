package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.UserDTO;
import com.example.demo.mapper.UserDAO;

@Service
public class LoginService {

	@Autowired
	private UserDAO userDAO;
	
	public UserDTO login(UserDTO userDTO) {
		
		try {
			// ID + PW 같이 확인
			int count = userDAO.getUser(userDTO);
			userDTO.setCount(count);
			
			if(count ==1) {
				
				userDTO.setSuccess(true);
				String userName = userDAO.getUserName(userDTO);
				userDTO.setUser_name(userName);
				
				//System.out.println("조회된 이름=");
			} else {// 108 구분용
				
				String dbPassword = userDAO.getPassword(userDTO);
				
				if(dbPassword == null) {
					//ID nonexist - 107
					
					userDTO.setSuccess(false);
					userDTO.setErr_msg("会員IDを確認してください。");
				
				}else {
					// PW wrong case - 108
					userDTO.setSuccess(false);
					userDTO.setErr_msg("入力したパスワードが一致しません。");
				}
			}
		} catch(Exception e) {
			
			userDTO.setSuccess(false);
			userDTO.setErr_msg("システムエラーが発生しました。");
		}
			return userDTO;
	}
}

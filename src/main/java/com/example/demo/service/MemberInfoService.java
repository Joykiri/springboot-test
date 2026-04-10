package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.MemberInfoDTO;
import com.example.demo.mapper.MemberInfoMapper;

@Service
public class MemberInfoService{
	@Autowired
	private MemberInfoMapper memberInfoMapper;
	
	//로그인 체크 메서드
	public MemberInfoDTO loginCheck(String memberId, String password) {
		
		return memberInfoMapper.findByIdAndPassword(memberId, password);
	}
	
}
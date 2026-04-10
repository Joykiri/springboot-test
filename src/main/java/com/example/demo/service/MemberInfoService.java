package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.MemberInfoDTO;
import com.example.demo.mapper.MemberInfoMapper;

@Service
public class MemberInfoService{
	@Autowired
	private MemberInfoMapper memberInfoMapper;
	
		
}
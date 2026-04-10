package com.example.demo.mapper;

import org.apache.ibatis.annotations.*;
import com.example.demo.DTO.MemberInfoDTO;

@Mapper
public interface MemberInfoMapper{
	
	MemberInfoDTO findByIdAndPassword(
		@Param("memberId") String memberId,
		@Param("password") String password
			);
}

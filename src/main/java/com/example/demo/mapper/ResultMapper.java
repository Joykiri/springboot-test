package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ResultMapper {

    // 당일 기록 존재 여부
    int countTodayGame(
        @Param("memberId") String memberId
    );

    // 전일 기록 존재 여부
    int countYesterdayGame(
        @Param("memberId") String memberId
    );
 // ⭐ 포인트 조회 추가
    int getPoint(@Param("memberId") String memberId);
    
    // 게임 결과 저장
    int insertResult(
        @Param("memberId") String memberId,
        @Param("playDate") java.sql.Date playDate,
        @Param("gameCount") int gameCount,
        @Param("inputNum") String inputNum,
        @Param("result") String result
    );
    void updatePoint(
    	    String memberId,
    	    int point
    	);
}
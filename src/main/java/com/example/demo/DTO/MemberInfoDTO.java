package com.example.demo.DTO;
import java.time.LocalDateTime;

public class MemberInfoDTO{
	private String memberId;
	private String memberName;
	private String password;
	private LocalDateTime recCreateDate;
	private LocalDateTime recUpdateDate;
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LocalDateTime getRecCreateDate() {
		return recCreateDate;
	}
	public void setRecCreateDate(LocalDateTime recCreateDate) {
		this.recCreateDate = recCreateDate;
	}
	public LocalDateTime getRecUpdateDate() {
		return recUpdateDate;
	}
	public void setRecUpdateDate(LocalDateTime recUpdateDate) {
		this.recUpdateDate = recUpdateDate;
	}
	
	
	
	
	
	
}

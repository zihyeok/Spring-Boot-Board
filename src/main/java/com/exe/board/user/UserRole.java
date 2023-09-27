package com.exe.board.user;

import lombok.Getter;

@Getter//값을 처음붙어 넣기때문에 get만 필요함
public enum UserRole {
	
	ADMIN("ROLE_ADMIN"),
	USER("ROLE_USER");
	//시작할때 반드시 ROLE_ 시작해야함
	
	private String value;
	
	UserRole(String value){
		this.value = value;
	}
	
}

package com.exe.board.user;

import lombok.Getter;

@Getter//���� ó���پ� �ֱ⶧���� get�� �ʿ���
public enum UserRole {
	
	ADMIN("ROLE_ADMIN"),
	USER("ROLE_USER");
	//�����Ҷ� �ݵ�� ROLE_ �����ؾ���
	
	private String value;
	
	UserRole(String value){
		this.value = value;
	}
	
}

package com.exe.board.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserCreateForm {
	
	@Size(min = 3,max = 25)
	@NotEmpty(message = "����� ID�� �ʼ��׸��Դϴ�.")
	private String userName;
	
	@NotEmpty(message = "��й�ȣ�� �ʼ��׸��Դϴ�.")
	private String password1;
	
	@NotEmpty(message = "��й�ȣ�� �ʼ��׸��Դϴ�.")
	private String password2;
	
	@NotEmpty(message = "�̸��ϴ� �ʼ��׸��Դϴ�.")
	@Email
	private String email;
	
}

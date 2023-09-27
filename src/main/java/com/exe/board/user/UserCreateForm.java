package com.exe.board.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserCreateForm {
	
	@Size(min = 3,max = 25)
	@NotEmpty(message = "사용자 ID는 필수항목입니다.")
	private String userName;
	
	@NotEmpty(message = "비밀번호는 필수항목입니다.")
	private String password1;
	
	@NotEmpty(message = "비밀번호는 필수항목입니다.")
	private String password2;
	
	@NotEmpty(message = "이메일는 필수항목입니다.")
	@Email
	private String email;
	
}

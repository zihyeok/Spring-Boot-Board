package com.exe.board.quesstion;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class QuestionForm {
	
	@NotEmpty(message = "������ �ʼ��׸��Դϴ�.")
	@Size(max = 200)
	private String subject;
	
	//@NotEmpty Null, �� ���ڿ� �Ұ�
	@NotEmpty(message = "������ �ʼ������Դϴ�.")
	private String content;
	
}

package com.exe.board.answer;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class AnswerForm {
	
	@NotEmpty(message = "������ �ʼ������Ե�!")
	private String content;
	
}

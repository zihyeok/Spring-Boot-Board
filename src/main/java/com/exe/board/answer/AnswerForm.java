package com.exe.board.answer;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class AnswerForm {
	
	@NotEmpty(message = "내용을 필수지말입돠!")
	private String content;
	
}

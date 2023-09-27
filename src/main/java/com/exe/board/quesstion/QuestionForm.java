package com.exe.board.quesstion;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class QuestionForm {
	
	@NotEmpty(message = "제목을 필수항목입니다.")
	@Size(max = 200)
	private String subject;
	
	//@NotEmpty Null, 빈 문자열 불가
	@NotEmpty(message = "내용은 필수지말입니다.")
	private String content;
	
}

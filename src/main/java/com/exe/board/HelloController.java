package com.exe.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller // 폴더/파일 이렇게 인식함
@RestController //@Controller + @ResponseBody 합친거 
public class HelloController {// json형식으로 받음 json형식이 text형식임
	
	@RequestMapping("/hello")//(value = "/hello")value생략가능
	//@ResponseBody
	public String hello() {
		
		
		return "Hello World!!!"; //리턴값을 텍스트로
	}
	
}

package com.exe.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller // ����/���� �̷��� �ν���
@RestController //@Controller + @ResponseBody ��ģ�� 
public class HelloController {// json�������� ���� json������ text������
	
	@RequestMapping("/hello")//(value = "/hello")value��������
	//@ResponseBody
	public String hello() {
		
		
		return "Hello World!!!"; //���ϰ��� �ؽ�Ʈ��
	}
	
}

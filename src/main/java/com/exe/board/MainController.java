package com.exe.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	
	@RequestMapping("/board")//���������� �ּҰ���
	@ResponseBody
	public String hello() {
		
		return "�ȳ��ϼ���~";
		
	}
	
	@RequestMapping("/")
	public String home() {
		
		return "redirect:/question/list";
	}
	
	
}


/*
 
  JPA(Java Persistence API)
  JPA ORM�� ����ϸ� �����ڰ� ������ ���� �ۼ����� �ʾƵ� 
  �����ͺ��̽��� ����Ʈ�� ó���Ҽ��ִ�.
  JPA�� �������̽��̴�. ���� �������̽��� 
  �����ϴ� ���� Ŭ������ �ʿ��ϴ�.
  
  SQL
  insert into question (subject,content) values ("�ȳ�","�����λ�");
  insert into question (subject,content) values ("����","Spring Boot��?");
  
  JPA
  Question q1 = new Question();
  q1.setSubject("�ȳ�");
  q1,setContent("�����λ�");
  this.questionRepository.save(q1);
  
  Question q2 = new Question();
  q2.setSubject("����");
  q2,setContent("Spring Boot��?");
  this.questionRepository.save(q2);
  
  
  h2�����ͺ��̽��� �ַ� ���߿��̳� �ұԸ� ������Ʈ������ ����ϴ� 
  ���ϱ�� �淮 �����ͺ��̽� 
  
  [Ư¡]
  H2 DB�� �ڹٱ���� �淮ȭ�� �����ͺ��̽�
  ���Ϸ� �����ؼ� ���� DBó�� ������ ���� ����
  �޸� DB�� ����ؼ� ���� �ν��Ͻ��� �����ϴ� �������� ����
  ������Ʈ �ʱ� ���߿��� �׽�Ʈ DB�� ���
  
 */

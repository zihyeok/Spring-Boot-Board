package com.exe.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	
	@RequestMapping("/board")//내가적은게 주소가됨
	@ResponseBody
	public String hello() {
		
		return "안녕하세요~";
		
	}
	
	@RequestMapping("/")
	public String home() {
		
		return "redirect:/question/list";
	}
	
	
}


/*
 
  JPA(Java Persistence API)
  JPA ORM을 사용하면 개발자가 쿼리를 직접 작성하지 않아도 
  데이터베이스의 테이트를 처리할수있다.
  JPA는 인터페이스이다. 따라서 인터페이스를 
  구현하는 실제 클래스가 필요하다.
  
  SQL
  insert into question (subject,content) values ("안녕","가입인사");
  insert into question (subject,content) values ("질문","Spring Boot란?");
  
  JPA
  Question q1 = new Question();
  q1.setSubject("안녕");
  q1,setContent("가입인사");
  this.questionRepository.save(q1);
  
  Question q2 = new Question();
  q2.setSubject("질문");
  q2,setContent("Spring Boot란?");
  this.questionRepository.save(q2);
  
  
  h2데이터베이스는 주로 개발용이나 소규모 프로젝트에서는 사용하는 
  파일기반 경량 데이터베이스 
  
  [특징]
  H2 DB는 자바기반의 경량화된 데이터베이스
  파일로 저장해서 실제 DB처럼 데이터 유지 가능
  메모리 DB로 사용해서 실제 인스턴스가 동작하는 순간에만 유지
  프로젝트 초기 개발에서 테스트 DB로 사용
  
 */

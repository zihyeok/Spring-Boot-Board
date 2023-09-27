package com.exe.board.answer;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.exe.board.quesstion.Question;
import com.exe.board.user.SiteUser;

import lombok.Data;

@Data
@Entity
public class Answer {
	
	@Id//고유값 즉, primary key값이 됨
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	private LocalDateTime createdDate;
	//카멜표기법 createdDate 
	//저장은스네이크표기법으로됨 CREATED_DATE
	
	@ManyToOne// 질문 하나에 답변 여러개 
	private Question question;
	//Foreign Key가 만들어짐 Question에 id와 연결하는 키
	//QUESTION_ID 포린키랑 이런식으로 저장됨
	
	@ManyToOne
	private SiteUser author;
	
	private LocalDateTime modifyDate;
	
	@ManyToMany// 포링키로해서 새로운 테이블을만듬
	Set<SiteUser> voter;
	//한번하면 두번은 안되서 Map값인 set을사용 map은 죽복값이안됨
}

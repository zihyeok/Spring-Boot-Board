package com.exe.board.quesstion;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.exe.board.answer.Answer;
import com.exe.board.user.SiteUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity //JPA가 Entity로 인식 즉, Question이 테이블, 변수가 컬럼
public class Question {//db랑 연결하는 Entity

	@Id //고유값 즉, primary key값이 됨
	@GeneratedValue(strategy = GenerationType.IDENTITY)//1씩증가하는 값 자동으로 들어감
	private Integer id;
	
	@Column(length = 200)//200자까지 가능 
	private String subject;
	
	@Column(columnDefinition = "TEXT")//오로지 TEXT만 쓸 수 있다
	private String content;
	
	private LocalDateTime createdDate;//입력날짜
	//cratedDate라고 적으면 추후 컬럼명에 created_date이렇게 저장된다
	
	@OneToMany(mappedBy = "question",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
	//fetch = FetchType.EAGER
	//test할때 fetch써줌 Lazy지연로딩 100개씩 함
	//eager즉시로딩 한번에 다가져옴
	
	//question은 Answer클래스에서 썼던 manytoone거기
	private List<Answer> answerList;
	
	@ManyToOne// 질문 하나에 답변 여러개 
	private SiteUser author;
	
	private LocalDateTime modifyDate;
	
	@ManyToMany
	Set<SiteUser> voter;
	//한번하면 두번은 안되서 Map값인 set을사용 map은 중복값이안됨
}

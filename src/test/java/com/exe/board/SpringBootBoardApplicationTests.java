package com.exe.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.exe.board.answer.Answer;
import com.exe.board.answer.AnswerRepository;
import com.exe.board.quesstion.Question;
import com.exe.board.quesstion.QuestionRepository;
import com.exe.board.quesstion.QuestionService;

@SpringBootTest
class SpringBootBoardApplicationTests {
	
	@Autowired //의존성 주입 인터페이스 라서 객채생성안되서 이렇게
	private QuestionRepository questionRepository;
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@Autowired
	private QuestionService questionService;
	
	@Test
	void contextLoads() {
	}

	/*
	@Test
	void save() {
		
		Question q1 = new Question();
		q1.setSubject("스프링 부트가 ");
		q1.setContent("스프링 부트에 대해 ");
		q1.setCreatedDate(LocalDateTime.now());
		this.questionRepository.save(q1); //insert
		//this는 생략가능
		
		Question q2 = new Question();
		q2.setSubject("스프링22");
		q2.setContent("스피링부트22");
		q2.setCreatedDate(LocalDateTime.now());
		questionRepository.save(q2);
		
		
	}
	*/
	
	/*
	@Test
	void findAll() {
		
		List<Question> lists = questionRepository.findAll();//select
		
		assertEquals(5, lists.size()); //lists로 넘어온 데이터가 2개인지 확인
		//실행할때 오류가없다면 2개인것으로 확인
		
		Question q = lists.get(0);//인덱스 번호0을 의미함
		assertEquals("스피링부트시러", q.getSubject());
		//lists의 1번째 행 데이터에 subject의 데이터가 같은지 확인
		
	}
	
	*/
	
	/*
	@Test
	void findBySubject() {//같은 값이있으면 안뜸 
		Question q = questionRepository.
				findBySubject("스프링22");
		assertEquals(6, q.getId());
	}
	
	
	@Test
	void findBySubjectAndContent() {
		Question q = questionRepository.
				findBySubjectAndContent("스프링 부트가 무엇인가요?", "스프링 부트에 대해 알고싶어요");  
		
		assertEquals(3, q.getId());
	}
	
	
	
	@Test
	void findBySubjectLike() { //이름달라도 실행가능
		List<Question> lists =
				questionRepository.findBySubjectLike("스프링%");
		
		Question q = lists.get(1);
		
		assertEquals("스프링 부트가 무엇인가요?", q.getSubject());
	}
	*/
	
	/*
	@Test
	void update() {
		
		//optional은 결과를 받은 클라이언트에게
		//null처리를 의무화해서 코드를 안전하게 유도
		Optional<Question> op = questionRepository.findById(4);
		assertTrue(op.isPresent());
		
		Question q = op.get();
		
		q.setSubject("스프링부트");
		q.setContent("스피링");
		
		questionRepository.save(q);
		
	}
	*/
	
	
	/*
	 
	@Test
	void delete() {
		
		assertEquals(5, questionRepository.count());
		//데이터 개수 확인
		
		
		Optional<Question> op = questionRepository.findById(5);
		
		assertTrue(op.isPresent());//5번이 있냐?
		
		Question q = op.get();
		questionRepository.delete(q);//삭제
		assertEquals(4, questionRepository.count());
		
	}
	
	*/
	
	/*
	@Test
	void replySave() {
		
		Optional<Question> op = questionRepository.findById(4);//꺼내오고
		
		assertTrue(op.isPresent());//확인하고 
		
		
		Question q = op.get();
		
		Answer a = new Answer();
		a.setContent("너무 어려워ㅜ");
		a.setQuestion(q);
		//연결된 값만 가져와서 set
		a.setCreatedDate(LocalDateTime.now());
		
		answerRepository.save(a);
		
	}
	
	*/
	 
	 
	
	/*
	
	@Test
	void replyFind() {
		
		Optional<Answer> op = answerRepository.findById(2);//가져오고
		
		assertTrue(op.isPresent());//확인하고 
		
		Answer a = op.get();
		
		assertEquals(4, a.getQuestion().getId());
		//assertEquals("스프링 부트가 무엇인가요?", q.getSubject());
	}
	*/
	/*
		
	@Transactional//한번에 다 실행시킬때 쓴다 
	@Test
	void replyConnectQuestion() {//
		
		
		//findById(0) 기본키에 번호를 찾는거 
		Optional<Question> op = questionRepository.findById(4);//가져오고
		assertTrue(op.isPresent());//확인하고 
		
		
		
		Question q = op.get();
		
		List<Answer> answerList = q.getAnswerList();
		
		assertEquals(1, answerList.size());//포린키로 연결된게 1개라서 1
		assertEquals("너무 어려워ㅜ",
				answerList.get(0).getContent());
			

		
	}
	*/
	
	/* 데이터 넣는거 300개
	@Test
	void save300() {
		
		for (int i = 1; i < 300; i++) {
			
			String subject = String.format("테스트데이터입니다:[%03d]", i);
			String content = String.format("스프링 부트는 재밌다:[%03d]", i);
			
			questionService.create(subject,content,null);
			
		}
		
	}
	*/
	
	
}

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
	
	@Autowired //������ ���� �������̽� �� ��ä�����ȵǼ� �̷���
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
		q1.setSubject("������ ��Ʈ�� ");
		q1.setContent("������ ��Ʈ�� ���� ");
		q1.setCreatedDate(LocalDateTime.now());
		this.questionRepository.save(q1); //insert
		//this�� ��������
		
		Question q2 = new Question();
		q2.setSubject("������22");
		q2.setContent("���Ǹ���Ʈ22");
		q2.setCreatedDate(LocalDateTime.now());
		questionRepository.save(q2);
		
		
	}
	*/
	
	/*
	@Test
	void findAll() {
		
		List<Question> lists = questionRepository.findAll();//select
		
		assertEquals(5, lists.size()); //lists�� �Ѿ�� �����Ͱ� 2������ Ȯ��
		//�����Ҷ� ���������ٸ� 2���ΰ����� Ȯ��
		
		Question q = lists.get(0);//�ε��� ��ȣ0�� �ǹ���
		assertEquals("���Ǹ���Ʈ�÷�", q.getSubject());
		//lists�� 1��° �� �����Ϳ� subject�� �����Ͱ� ������ Ȯ��
		
	}
	
	*/
	
	/*
	@Test
	void findBySubject() {//���� ���������� �ȶ� 
		Question q = questionRepository.
				findBySubject("������22");
		assertEquals(6, q.getId());
	}
	
	
	@Test
	void findBySubjectAndContent() {
		Question q = questionRepository.
				findBySubjectAndContent("������ ��Ʈ�� �����ΰ���?", "������ ��Ʈ�� ���� �˰�;��");  
		
		assertEquals(3, q.getId());
	}
	
	
	
	@Test
	void findBySubjectLike() { //�̸��޶� ���డ��
		List<Question> lists =
				questionRepository.findBySubjectLike("������%");
		
		Question q = lists.get(1);
		
		assertEquals("������ ��Ʈ�� �����ΰ���?", q.getSubject());
	}
	*/
	
	/*
	@Test
	void update() {
		
		//optional�� ����� ���� Ŭ���̾�Ʈ����
		//nulló���� �ǹ�ȭ�ؼ� �ڵ带 �����ϰ� ����
		Optional<Question> op = questionRepository.findById(4);
		assertTrue(op.isPresent());
		
		Question q = op.get();
		
		q.setSubject("��������Ʈ");
		q.setContent("���Ǹ�");
		
		questionRepository.save(q);
		
	}
	*/
	
	
	/*
	 
	@Test
	void delete() {
		
		assertEquals(5, questionRepository.count());
		//������ ���� Ȯ��
		
		
		Optional<Question> op = questionRepository.findById(5);
		
		assertTrue(op.isPresent());//5���� �ֳ�?
		
		Question q = op.get();
		questionRepository.delete(q);//����
		assertEquals(4, questionRepository.count());
		
	}
	
	*/
	
	/*
	@Test
	void replySave() {
		
		Optional<Question> op = questionRepository.findById(4);//��������
		
		assertTrue(op.isPresent());//Ȯ���ϰ� 
		
		
		Question q = op.get();
		
		Answer a = new Answer();
		a.setContent("�ʹ� �������");
		a.setQuestion(q);
		//����� ���� �����ͼ� set
		a.setCreatedDate(LocalDateTime.now());
		
		answerRepository.save(a);
		
	}
	
	*/
	 
	 
	
	/*
	
	@Test
	void replyFind() {
		
		Optional<Answer> op = answerRepository.findById(2);//��������
		
		assertTrue(op.isPresent());//Ȯ���ϰ� 
		
		Answer a = op.get();
		
		assertEquals(4, a.getQuestion().getId());
		//assertEquals("������ ��Ʈ�� �����ΰ���?", q.getSubject());
	}
	*/
	/*
		
	@Transactional//�ѹ��� �� �����ų�� ���� 
	@Test
	void replyConnectQuestion() {//
		
		
		//findById(0) �⺻Ű�� ��ȣ�� ã�°� 
		Optional<Question> op = questionRepository.findById(4);//��������
		assertTrue(op.isPresent());//Ȯ���ϰ� 
		
		
		
		Question q = op.get();
		
		List<Answer> answerList = q.getAnswerList();
		
		assertEquals(1, answerList.size());//����Ű�� ����Ȱ� 1���� 1
		assertEquals("�ʹ� �������",
				answerList.get(0).getContent());
			

		
	}
	*/
	
	/* ������ �ִ°� 300��
	@Test
	void save300() {
		
		for (int i = 1; i < 300; i++) {
			
			String subject = String.format("�׽�Ʈ�������Դϴ�:[%03d]", i);
			String content = String.format("������ ��Ʈ�� ��մ�:[%03d]", i);
			
			questionService.create(subject,content,null);
			
		}
		
	}
	*/
	
	
}

package com.exe.board.quesstion;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.exe.board.DataNotFoundException;
import com.exe.board.user.SiteUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {
	
	private final QuestionRepository questionRepository;
	
	public Page<Question> getLists(Pageable pageable){
		//�߰�service����� �ٸ��������� ��밡���ϰ���
		//���̷�Ʈ�� ����� �߰��� ���Ұ� 
		
		//�����ؼ� ����ϴ°�
		List<Sort.Order> sort = new ArrayList<Sort.Order>();
		sort.add(Sort.Order.desc("createdDate"));
		
		//����¡ó��
		pageable = PageRequest.of(
				pageable.getPageNumber()<=0 ? 0 :
					pageable.getPageNumber() -1 ,
					pageable.getPageSize(),Sort.by(sort));
		
		return questionRepository.findAll(pageable);
	}
	
	public Question getQuestion(Integer id) {
		
		Optional<Question> op = questionRepository.findById(id);
		
		if (op.isPresent()) {
			return op.get();
		}else {
			throw new DataNotFoundException("�����Ͱ� �����");
		}
		
	}
	
	//�Է� 
	public void create(String subject,String content,SiteUser author) {
		
		Question question = new Question();
		
		question.setSubject(subject);
		question.setContent(content);
		question.setCreatedDate(LocalDateTime.now());
		question.setAuthor(author);
		
		questionRepository.save(question);
	}
	
	//����
	public void modify(Question question,String subject,String content) {
		
		question.setSubject(subject);
		question.setContent(content);
		question.setModifyDate(LocalDateTime.now());
		
		questionRepository.save(question);
	}
	
	//����
	public void delete(Question question) {
		
		questionRepository.delete(question);
		
	}
	
	//��õ�� ����
	public void vote(Question question,SiteUser siteUser) {
		
		question.getVoter().add(siteUser);//��õ���� �߰��ؼ�
		
		questionRepository.save(question);//���� update���
		
	}
	
	
}

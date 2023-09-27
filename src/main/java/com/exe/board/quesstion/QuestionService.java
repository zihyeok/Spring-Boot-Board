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
		//중간service만들어 다른곳에서도 사용가능하게함
		//다이렉트로 만들면 중간에 사용불가 
		
		//정렬해서 출력하는거
		List<Sort.Order> sort = new ArrayList<Sort.Order>();
		sort.add(Sort.Order.desc("createdDate"));
		
		//페이징처리
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
			throw new DataNotFoundException("데이터가 없어요");
		}
		
	}
	
	//입력 
	public void create(String subject,String content,SiteUser author) {
		
		Question question = new Question();
		
		question.setSubject(subject);
		question.setContent(content);
		question.setCreatedDate(LocalDateTime.now());
		question.setAuthor(author);
		
		questionRepository.save(question);
	}
	
	//수정
	public void modify(Question question,String subject,String content) {
		
		question.setSubject(subject);
		question.setContent(content);
		question.setModifyDate(LocalDateTime.now());
		
		questionRepository.save(question);
	}
	
	//삭제
	public void delete(Question question) {
		
		questionRepository.delete(question);
		
	}
	
	//추천인 저장
	public void vote(Question question,SiteUser siteUser) {
		
		question.getVoter().add(siteUser);//추천인을 추가해서
		
		questionRepository.save(question);//수정 update방식
		
	}
	
	
}

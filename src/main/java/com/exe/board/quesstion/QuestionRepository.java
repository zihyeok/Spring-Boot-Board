package com.exe.board.quesstion;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository 
	extends JpaRepository<Question, Integer>{
	//<entity의 타입 , 프라이머리키의 타입>
	
	//findBy뒤에 찾을 데이터를 추가로 적는것이 규칙이다
	
	Question findBySubject(String subject); //사용자 정의 메소드
	//findBy를 읽은 JAP가 자동으로 사용자 정의 메소드로 인식한다
	
	Question findBySubjectAndContent(String subject,String content);
	List<Question> findBySubjectLike(String subject);
	
	Page<Question> findAll(Pageable pageable);
	
}

/*
데이터 처리를 위해서는 실제 데이터베이스와 연동하는
JPA 리포지터리가 필요함
 
엔티티에의해 생성된 테이블에 접근하는 메소드(findAll,save,findById...)를
사용하기위한 인터페이스이다

save는 덮어씨우는느낌
@기본 메소드 : save,update(save),delete,findById,findAll
@사용자정의 메소드 : findBySubject(서브젝트로 찾기) ,
findBySubjectAndContent(서브젝트와 컨텐트로 찾기,
findBySubjectLike(서브젝트의 like로 포함된 데이터 찾기)
 

CRUD(Create,Read-select,Update,Delete)를 어떻게 처리할지
정의하는 계층이 Repository이다
*/

/*
And (A and B)
findBySubjectAndContent(String subject, String content)	
컬럼명 findBySubjectAndContent(String subject, String content)	

Or (A or B)
findBySubjectOrContent(String subject, String content)
	
Between (컬럼명 between A and B)
findByCreateDateBetween(LocalDateTime fromDate, LocalDateTime toDate)
	
LessThan (컬럼명 < A)
findByIdLessThan(Integer id)	

GreaterThanEqual (컬럼명 >= A)
findByIdGraterThanEqual(Integer id) 

Like (컬럼명 like 'A%')
findBySubjectLike(String subject) 
 
In (컬럼명 in (A,B))
findBySubjectIn(String[] subjects)

OrderBy (order by 컬럼명)
findBySubjectOrderByCreateDateAsc(String subject) 
*/ 


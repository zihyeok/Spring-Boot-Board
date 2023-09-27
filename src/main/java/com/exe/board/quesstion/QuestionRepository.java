package com.exe.board.quesstion;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository 
	extends JpaRepository<Question, Integer>{
	//<entity�� Ÿ�� , �����̸Ӹ�Ű�� Ÿ��>
	
	//findBy�ڿ� ã�� �����͸� �߰��� ���°��� ��Ģ�̴�
	
	Question findBySubject(String subject); //����� ���� �޼ҵ�
	//findBy�� ���� JAP�� �ڵ����� ����� ���� �޼ҵ�� �ν��Ѵ�
	
	Question findBySubjectAndContent(String subject,String content);
	List<Question> findBySubjectLike(String subject);
	
	Page<Question> findAll(Pageable pageable);
	
}

/*
������ ó���� ���ؼ��� ���� �����ͺ��̽��� �����ϴ�
JPA �������͸��� �ʿ���
 
��ƼƼ������ ������ ���̺� �����ϴ� �޼ҵ�(findAll,save,findById...)��
����ϱ����� �������̽��̴�

save�� �����´���
@�⺻ �޼ҵ� : save,update(save),delete,findById,findAll
@��������� �޼ҵ� : findBySubject(������Ʈ�� ã��) ,
findBySubjectAndContent(������Ʈ�� ����Ʈ�� ã��,
findBySubjectLike(������Ʈ�� like�� ���Ե� ������ ã��)
 

CRUD(Create,Read-select,Update,Delete)�� ��� ó������
�����ϴ� ������ Repository�̴�
*/

/*
And (A and B)
findBySubjectAndContent(String subject, String content)	
�÷��� findBySubjectAndContent(String subject, String content)	

Or (A or B)
findBySubjectOrContent(String subject, String content)
	
Between (�÷��� between A and B)
findByCreateDateBetween(LocalDateTime fromDate, LocalDateTime toDate)
	
LessThan (�÷��� < A)
findByIdLessThan(Integer id)	

GreaterThanEqual (�÷��� >= A)
findByIdGraterThanEqual(Integer id) 

Like (�÷��� like 'A%')
findBySubjectLike(String subject) 
 
In (�÷��� in (A,B))
findBySubjectIn(String[] subjects)

OrderBy (order by �÷���)
findBySubjectOrderByCreateDateAsc(String subject) 
*/ 


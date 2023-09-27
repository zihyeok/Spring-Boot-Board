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
@Entity //JPA�� Entity�� �ν� ��, Question�� ���̺�, ������ �÷�
public class Question {//db�� �����ϴ� Entity

	@Id //������ ��, primary key���� ��
	@GeneratedValue(strategy = GenerationType.IDENTITY)//1�������ϴ� �� �ڵ����� ��
	private Integer id;
	
	@Column(length = 200)//200�ڱ��� ���� 
	private String subject;
	
	@Column(columnDefinition = "TEXT")//������ TEXT�� �� �� �ִ�
	private String content;
	
	private LocalDateTime createdDate;//�Է³�¥
	//cratedDate��� ������ ���� �÷��� created_date�̷��� ����ȴ�
	
	@OneToMany(mappedBy = "question",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
	//fetch = FetchType.EAGER
	//test�Ҷ� fetch���� Lazy�����ε� 100���� ��
	//eager��÷ε� �ѹ��� �ٰ�����
	
	//question�� AnswerŬ�������� ��� manytoone�ű�
	private List<Answer> answerList;
	
	@ManyToOne// ���� �ϳ��� �亯 ������ 
	private SiteUser author;
	
	private LocalDateTime modifyDate;
	
	@ManyToMany
	Set<SiteUser> voter;
	//�ѹ��ϸ� �ι��� �ȵǼ� Map���� set����� map�� �ߺ����̾ȵ�
}

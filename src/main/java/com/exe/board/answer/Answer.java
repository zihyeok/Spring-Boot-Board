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
	
	@Id//������ ��, primary key���� ��
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	private LocalDateTime createdDate;
	//ī��ǥ��� createdDate 
	//������������ũǥ������ε� CREATED_DATE
	
	@ManyToOne// ���� �ϳ��� �亯 ������ 
	private Question question;
	//Foreign Key�� ������� Question�� id�� �����ϴ� Ű
	//QUESTION_ID ����Ű�� �̷������� �����
	
	@ManyToOne
	private SiteUser author;
	
	private LocalDateTime modifyDate;
	
	@ManyToMany// ����Ű���ؼ� ���ο� ���̺�������
	Set<SiteUser> voter;
	//�ѹ��ϸ� �ι��� �ȵǼ� Map���� set����� map�� �׺����̾ȵ�
}

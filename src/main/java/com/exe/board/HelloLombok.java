package com.exe.board;

import lombok.Data;
import lombok.RequiredArgsConstructor;

//�����ε��� ������
//������ �ݵ�� final�� ����
//final �̱� ������ set�� ��� ����
@RequiredArgsConstructor
@Data
public class HelloLombok {//Constructor ������ //Args �Ű�����

	private final String name; 
	private final int age; //final �� �������� 
	
	/*
	 public static void main(String[] args) {
		 
		// HelloLombok hk = new HelloLombok();
		 HelloLombok hk = new HelloLombok("���γ�",41);
		 
		// hk.setName("�����"); //set�� ������
		// hk.setAge(27);
		 
		 System.out.println(hk.getName());
		 System.out.println(hk.getAge());
		 
		
		 
		 
		 
		 
	 }
	*/
}

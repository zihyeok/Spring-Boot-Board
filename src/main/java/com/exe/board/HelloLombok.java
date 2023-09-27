package com.exe.board;

import lombok.Data;
import lombok.RequiredArgsConstructor;

//오버로딩된 생성자
//변수는 반드시 final로 설정
//final 이기 때문에 set는 사용 못함
@RequiredArgsConstructor
@Data
public class HelloLombok {//Constructor 생성자 //Args 매개변수

	private final String name; 
	private final int age; //final 로 만들어야함 
	
	/*
	 public static void main(String[] args) {
		 
		// HelloLombok hk = new HelloLombok();
		 HelloLombok hk = new HelloLombok("유인나",41);
		 
		// hk.setName("배수지"); //set는 사용안함
		// hk.setAge(27);
		 
		 System.out.println(hk.getName());
		 System.out.println(hk.getAge());
		 
		
		 
		 
		 
		 
	 }
	*/
}

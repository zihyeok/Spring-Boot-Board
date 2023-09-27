package com.exe.board;

public class DataNotFoundException 
		extends RuntimeException{//그냥Exception 써도됨
	
	public DataNotFoundException(String message) {
		super(message);//데이터가 없으면 띠움
	}//에러창
	
	
}

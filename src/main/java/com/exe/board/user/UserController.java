package com.exe.board.user;

import javax.validation.Valid;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
	
	private final UserService userSercice;//����������
	
	@GetMapping("/signup")
	public String signup(UserCreateForm userCreateForm) {
		
		return "signup_form";
	}
	
	@PostMapping("/signup")
	public String singup(@Valid UserCreateForm userCreateForm,BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "signup_form";
		}
		
		if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
			
			bindingResult.rejectValue("password2", "passwordInCorrect",
					"2���� �н����尡 ��ġ���� �ʽ��ϴ�.");
			//rejectValue Ư���ʵ忡 ���
			//"password2" ���� ���ϰ� "passwordInCorrect"�������°� �̰� ���������
			return "signup_form";
		}
		
		try {
			
		userSercice.create(userCreateForm.getUserName(), 
				userCreateForm.getEmail(),userCreateForm.getPassword1());
		
		}catch(DataIntegrityViolationException e) {
			
			e.printStackTrace();
			
			bindingResult.reject("signupFailed","�̹� ��ϵ� ������Դϴ�,");
			//signupFailed ��͵� ����� ���� 
			return "signup_form";
		}catch(Exception e) {
			
			e.printStackTrace();
			//reject �ʵ忡 ��� 
			bindingResult.reject("signupFailed",e.getMessage());
			
			return "signup_form";
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String login() {
		
		//get���� â�� ����ָ� post�α��� ó���� ��ť��Ƽ�� ���ش�
		
		return "login_form";
	}
	
	
	
}

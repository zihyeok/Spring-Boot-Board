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
	
	private final UserService userSercice;//의존성주입
	
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
					"2개의 패스워드가 일치하지 않습니다.");
			//rejectValue 특정필드에 사용
			//"password2" 값을 비교하고 "passwordInCorrect"에러띄우는거 이건 사용자정의
			return "signup_form";
		}
		
		try {
			
		userSercice.create(userCreateForm.getUserName(), 
				userCreateForm.getEmail(),userCreateForm.getPassword1());
		
		}catch(DataIntegrityViolationException e) {
			
			e.printStackTrace();
			
			bindingResult.reject("signupFailed","이미 등록된 사용자입니다,");
			//signupFailed 어것도 사용장 정의 
			return "signup_form";
		}catch(Exception e) {
			
			e.printStackTrace();
			//reject 필드에 사용 
			bindingResult.reject("signupFailed",e.getMessage());
			
			return "signup_form";
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String login() {
		
		//get으로 창만 띠워주면 post로그인 처리는 씨큐러티가 해준다
		
		return "login_form";
	}
	
	
	
}

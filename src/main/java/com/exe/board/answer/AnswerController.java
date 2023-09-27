package com.exe.board.answer;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.exe.board.quesstion.Question;
import com.exe.board.quesstion.QuestionService;
import com.exe.board.user.SiteUser;
import com.exe.board.user.UserService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/answer")//여기에 공통된 주소를 써줘서 밑에부분에 안적어줘도됨"/answer/create/{id}"
@RequiredArgsConstructor 
@Controller
public class AnswerController {
	
	private final QuestionService questionService;
	private final AnswerService answerService;
	private final UserService userService;
	
	//@PostMapping get,post 정해서 쓰면됨
	@PreAuthorize("isAuthenticated")//인증된사람만사용해라~
	@PostMapping("/create/{id}")//id는 컬럼이름 
	public String createAnswer(Model model,@PathVariable("id")Integer id,
			@Valid AnswerForm answerForm, BindingResult bindingResult,
			Principal principal) {
//@Valid AnswerForm,BindingResult  순서 바뀌면안됨
		
		Question question = questionService.getQuestion(id);
		SiteUser siteUser = userService.getUser(principal.getName());
		
		if (bindingResult.hasErrors()) {//에러를 가지고 있으면~
			model.addAttribute("question",question);
			return "question_detail";
		}
		
		
		Answer answer = answerService.create(question, 
				answerForm.getContent(),siteUser);
		
		return String.format("redirect:/question/detail/%s#answer_%s",
				answer.getQuestion().getId(),answer.getId());
		//return ("redirect:/question/deteil/"+ id); 
		//이렇게 가능하지만 String.format을 쓰기위해 
	}
	
	@PreAuthorize("isAuthenticated")
	@GetMapping("/modify/{id}")
	public String answerModify(AnswerForm answerForm,
			@PathVariable("id") Integer id,Principal principal) {
		
		Answer answer = answerService.getAnswer(id);
		
		if (!answer.getAuthor().getUserName().equals(principal.getName())) {
			
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST,"수정할 권한이 없습니다,");
		}
		
		answerForm.setContent(answer.getContent());
		
		return "answer_form";
	}
	
	@PreAuthorize("isAuthenticated")
	@PostMapping("/modify/{id}")
	public String answerModify(@Valid AnswerForm answerForm,BindingResult bindingResult,
			@PathVariable("id") Integer id,Principal principal) {
		
		if (bindingResult.hasErrors()) {
			return "answer_form";
		}
		
		Answer answer = answerService.getAnswer(id);
		
		if (!answer.getAuthor().getUserName().equals(principal.getName())) {
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST,"수정 권한이 없습니다.");
		}
		
		answerService.modify(answer, answerForm.getContent());
		
		return String.format("redirect:/question/detail/%s#answer_%s",
				answer.getQuestion().getId(),answer.getId());
	}
	
	
	@PreAuthorize("isAuthenticated")
	@GetMapping("/delete/{id}")
	public String answerDelete(@PathVariable("id") Integer id,Principal principal) {
		//principal는 로그인한 사용자의 정보를 알려줌 
		
		Answer answer = answerService.getAnswer(id);//부모값 읽어옴
		
		if (!answer.getAuthor().getUserName().equals(principal.getName())) {
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST,"삭제 권한이 없습니다.");
		}
		
		answerService.delete(answer);
		
		return String.format("redirect:/question/detail/%s",
				answer.getQuestion().getId());
	}
	
	@PreAuthorize("isAuthenticated")
	@GetMapping("/vote/{id}")
	public String answerVote(Principal principal,@PathVariable Integer id) {
		
		Answer answer = answerService.getAnswer(id);
		
		SiteUser siteUser = userService.getUser(principal.getName());//로그인한사람 정보
		
		
		answerService.vote(answer, siteUser);
		
		return String.format("redirect:/question/detail/%s#answer_%s", 
				answer.getQuestion().getId(),answer.getId());//#answer_%s
											//answer.getId() 여기 이부분들이 포커스처럼됨
		
	}
	
	
	
}

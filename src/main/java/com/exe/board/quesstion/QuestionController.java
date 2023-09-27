package com.exe.board.quesstion;


import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

import com.exe.board.answer.AnswerForm;
import com.exe.board.user.SiteUser;
import com.exe.board.user.UserService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/question")
@RequiredArgsConstructor//오버로딩된 생성자 만듬 내부적으로 안보이게
@Controller
public class QuestionController {
	
	
	//private final QuestionRepository questionRepository; 다이렉트
	//https://www.thymeleaf.org/ecosystem.html  /thymeleaf 사이트
	
	private final UserService userService;
	private final QuestionService questionService;
	//의존성주입
	
	@RequestMapping("/list")
	public String list(Model model,@PageableDefault Pageable pageable) {
		
		//List<Question> lists = questionRepository.findAll(); 다이렉트
		Page<Question> paging = questionService.getLists(pageable);
 		
		model.addAttribute("paging",paging);
		
		return "question_list";
	}
	
	@RequestMapping("/detail/{id}")//넘어오는 값이라 id로 맞춤
	public String detile(Model model,@PathVariable("id")Integer id,
			AnswerForm answerForm) {//answerForm이 밑에questionForm과 같은개념
		
		Question question = questionService.getQuestion(id);//id 사용자정의
		model.addAttribute("question",question);
		
		return "question_detail";
	}
	
	@PreAuthorize("isAuthenticated")//인증된사람만사용해라~
	@GetMapping("/create")
	public String questionCreate(QuestionForm questionForm){
	//QuestionForm questionForm 값을 가져오려고 
		
	//post로 가는 경로가 없어도 똑같은 주소가 있어서 알아서감
		return "question_form";
	}
	
	@PreAuthorize("isAuthenticated")//인증된사람만사용해라~
	@PostMapping("/create")
	public String questionCreate(@Valid QuestionForm questionForm,
			BindingResult bindingResult,Principal principal) {//principal 로그인하고있는아이디
		//@Valid QuestionForm form에가서 검사해와~
		//BindingResult 결과값 가져와
		
		//@RequestParam 생략가능
		
		if (bindingResult.hasErrors()) {
			return "question_form";
		}
		
		SiteUser siteUser = userService.getUser(principal.getName());//지금 로그인한 아이디
		
		questionService.create(questionForm.getSubject(),questionForm.getContent(),siteUser);
		
		return "redirect:/question/list";
	}
	
	@PreAuthorize("isAuthenticated")
	@GetMapping("/modify/{id}")//넘어오는 값 id라
	public String questionModify(QuestionForm questionForm,
			@PathVariable("id") Integer id, Principal principal) {
		
		Question question = questionService.getQuestion(id);
		
		if (!question.getAuthor().getUserName().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"수정권한이 없습니다,");
			
		}
		
		questionForm.setSubject(question.getSubject());
		questionForm.setContent(question.getContent());
		
		return "question_form";
	}
	
	@PreAuthorize("isAuthenticated")
	@PostMapping("/modify/{id}")
	public String questionModify(@Valid QuestionForm questionForm, BindingResult bindingResult,
			@PathVariable("id") Integer id, Principal principal) {
		
		if (bindingResult.hasErrors()) {
			return "question_form";
		}
		
		Question question = questionService.getQuestion(id);
		
		if (!question.getAuthor().getUserName().equals(principal.getName())) {
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST,"수정권한이 없습니다,");
			
		}
		
		questionService.modify(question, questionForm.getSubject(), 
				questionForm.getContent());
		
		
		return String.format("redirect:/question/detail/%s", id);
	}
	
	@PreAuthorize("isAuthenticated")
	@GetMapping("/delete/{id}")
	public String questionDelete(Principal principal,@PathVariable Integer id) {
		
		Question question = questionService.getQuestion(id);
		
		if (!question.getAuthor().getUserName().equals(principal.getName())) {
			
			throw new ResponseStatusException(
					HttpStatus.BAD_REQUEST,"삭제할 권한이 없습니다,");
		}
		
		questionService.delete(question);
		
		return "redirect:/";
	}
	
	@PreAuthorize("isAuthenticated")
	@GetMapping("/vote/{id}")
	public String questionVote(Principal principal,@PathVariable Integer id) {
		
		Question question = questionService.getQuestion(id);
		
		SiteUser siteUser = userService.getUser(principal.getName());//로그인한사람 정보
		
		
		questionService.vote(question, siteUser);
		
		return String.format("redirect:/question/detail/%s", id);
	}
	
}


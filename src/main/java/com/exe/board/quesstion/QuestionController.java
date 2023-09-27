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
@RequiredArgsConstructor//�����ε��� ������ ���� ���������� �Ⱥ��̰�
@Controller
public class QuestionController {
	
	
	//private final QuestionRepository questionRepository; ���̷�Ʈ
	//https://www.thymeleaf.org/ecosystem.html  /thymeleaf ����Ʈ
	
	private final UserService userService;
	private final QuestionService questionService;
	//����������
	
	@RequestMapping("/list")
	public String list(Model model,@PageableDefault Pageable pageable) {
		
		//List<Question> lists = questionRepository.findAll(); ���̷�Ʈ
		Page<Question> paging = questionService.getLists(pageable);
 		
		model.addAttribute("paging",paging);
		
		return "question_list";
	}
	
	@RequestMapping("/detail/{id}")//�Ѿ���� ���̶� id�� ����
	public String detile(Model model,@PathVariable("id")Integer id,
			AnswerForm answerForm) {//answerForm�� �ؿ�questionForm�� ��������
		
		Question question = questionService.getQuestion(id);//id ���������
		model.addAttribute("question",question);
		
		return "question_detail";
	}
	
	@PreAuthorize("isAuthenticated")//�����Ȼ��������ض�~
	@GetMapping("/create")
	public String questionCreate(QuestionForm questionForm){
	//QuestionForm questionForm ���� ���������� 
		
	//post�� ���� ��ΰ� ��� �Ȱ��� �ּҰ� �־ �˾Ƽ���
		return "question_form";
	}
	
	@PreAuthorize("isAuthenticated")//�����Ȼ��������ض�~
	@PostMapping("/create")
	public String questionCreate(@Valid QuestionForm questionForm,
			BindingResult bindingResult,Principal principal) {//principal �α����ϰ��ִ¾��̵�
		//@Valid QuestionForm form������ �˻��ؿ�~
		//BindingResult ����� ������
		
		//@RequestParam ��������
		
		if (bindingResult.hasErrors()) {
			return "question_form";
		}
		
		SiteUser siteUser = userService.getUser(principal.getName());//���� �α����� ���̵�
		
		questionService.create(questionForm.getSubject(),questionForm.getContent(),siteUser);
		
		return "redirect:/question/list";
	}
	
	@PreAuthorize("isAuthenticated")
	@GetMapping("/modify/{id}")//�Ѿ���� �� id��
	public String questionModify(QuestionForm questionForm,
			@PathVariable("id") Integer id, Principal principal) {
		
		Question question = questionService.getQuestion(id);
		
		if (!question.getAuthor().getUserName().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"���������� �����ϴ�,");
			
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
					HttpStatus.BAD_REQUEST,"���������� �����ϴ�,");
			
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
					HttpStatus.BAD_REQUEST,"������ ������ �����ϴ�,");
		}
		
		questionService.delete(question);
		
		return "redirect:/";
	}
	
	@PreAuthorize("isAuthenticated")
	@GetMapping("/vote/{id}")
	public String questionVote(Principal principal,@PathVariable Integer id) {
		
		Question question = questionService.getQuestion(id);
		
		SiteUser siteUser = userService.getUser(principal.getName());//�α����ѻ�� ����
		
		
		questionService.vote(question, siteUser);
		
		return String.format("redirect:/question/detail/%s", id);
	}
	
}


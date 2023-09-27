package com.exe.board.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.exe.board.user.UserSecurityService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration// 보안에 관련된 환경설정이야! 
@EnableWebSecurity//모든 url 제어를 받게함 
@EnableGlobalMethodSecurity(prePostEnabled = true)//@PreAuthorize 이코드를 활성화시킴
public class SecurityConfig {
	
	
	private final UserSecurityService userSecurityService;
	
	@Bean//객체생성 @Configuration 썼을때만 사용가능
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		// SecurityFilterChain 
		// chain - 체인 모양처럼 쭉 연결되서 간다
		//			다음 필터로 넘기는 역할. 서버까지 도달 못함
		//			일련의 작업을 하는게 체인
		
		// filter - 기존에 배웠던 그 필터
		// 모든 인증되지 않는 요청 수락 - 웹은 들어가고 h2(db)는 못들어감
		// .csrf().ignoringAntMatchers("/h2-console") 이 주소오면 무시해 (예외해)
		// .formLogin().loginPage("/user/login").defaultSuccessUrl("/")
		//		로그인 성공하면 여기로 보내
		//		내장객체라 선언하면 서로 연결되어
		//		UserSecurityService 찾아가서(혹은 가져와서) 처리한다고 보면 될듯하다

		http
		.authorizeHttpRequests().antMatchers("/**").permitAll()//모든 인증되지 않은 요청을 허락
		.and()
		.csrf().ignoringAntMatchers("/h2-console/**")//스프링이 아니라 따로 예외처리해야함 
		.and()
		.headers().addHeaderWriter(new XFrameOptionsHeaderWriter(
				XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
		.and()
		.formLogin().loginPage("/user/login").defaultSuccessUrl("/")//formLogin().loginPage부분이 검사
		.and()//UserSecurityService로 보냈다가 다시와서 /일로보냄
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
		.logoutSuccessUrl("/").invalidateHttpSession(true)//세션지우기
		;
		
		
		return http.build();
		
	}
	
	@Bean//매소드가 클래스화 하는거 
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	//AuthenticationManager는 스프링 시튜리티의 인증을 담당
	//UserSecurityService와 PasswordEncoder가 자동으로 설정
	// 	db에 있는 비번이 암호와 되어 있는데
	// 	사용자가 입력한 값이랑 비교해야 한다
	//	따라서 입력받은걸 암호화해서 비교한다
	//	같은 값을 암호화하면 같은걸로 암호화 한다. (단방향 암호화)
	//	복호화 암호화는 암호화한걸 원래 값으로 바꾼다
	@Bean
	public AuthenticationManager authenticationManager(
			AuthenticationConfiguration authenticationConfiguration) throws Exception{
		
		return authenticationConfiguration.getAuthenticationManager();
	}
 	
	
}

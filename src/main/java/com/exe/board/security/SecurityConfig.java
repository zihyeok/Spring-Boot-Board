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
@Configuration// ���ȿ� ���õ� ȯ�漳���̾�! 
@EnableWebSecurity//��� url ��� �ް��� 
@EnableGlobalMethodSecurity(prePostEnabled = true)//@PreAuthorize ���ڵ带 Ȱ��ȭ��Ŵ
public class SecurityConfig {
	
	
	private final UserSecurityService userSecurityService;
	
	@Bean//��ü���� @Configuration �������� ��밡��
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		// SecurityFilterChain 
		// chain - ü�� ���ó�� �� ����Ǽ� ����
		//			���� ���ͷ� �ѱ�� ����. �������� ���� ����
		//			�Ϸ��� �۾��� �ϴ°� ü��
		
		// filter - ������ ����� �� ����
		// ��� �������� �ʴ� ��û ���� - ���� ���� h2(db)�� ����
		// .csrf().ignoringAntMatchers("/h2-console") �� �ּҿ��� ������ (������)
		// .formLogin().loginPage("/user/login").defaultSuccessUrl("/")
		//		�α��� �����ϸ� ����� ����
		//		���尴ü�� �����ϸ� ���� ����Ǿ�
		//		UserSecurityService ã�ư���(Ȥ�� �����ͼ�) ó���Ѵٰ� ���� �ɵ��ϴ�

		http
		.authorizeHttpRequests().antMatchers("/**").permitAll()//��� �������� ���� ��û�� ���
		.and()
		.csrf().ignoringAntMatchers("/h2-console/**")//�������� �ƴ϶� ���� ����ó���ؾ��� 
		.and()
		.headers().addHeaderWriter(new XFrameOptionsHeaderWriter(
				XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
		.and()
		.formLogin().loginPage("/user/login").defaultSuccessUrl("/")//formLogin().loginPage�κ��� �˻�
		.and()//UserSecurityService�� ���´ٰ� �ٽÿͼ� /�Ϸκ���
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
		.logoutSuccessUrl("/").invalidateHttpSession(true)//���������
		;
		
		
		return http.build();
		
	}
	
	@Bean//�żҵ尡 Ŭ����ȭ �ϴ°� 
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	//AuthenticationManager�� ������ ��Ʃ��Ƽ�� ������ ���
	//UserSecurityService�� PasswordEncoder�� �ڵ����� ����
	// 	db�� �ִ� ����� ��ȣ�� �Ǿ� �ִµ�
	// 	����ڰ� �Է��� ���̶� ���ؾ� �Ѵ�
	//	���� �Է¹����� ��ȣȭ�ؼ� ���Ѵ�
	//	���� ���� ��ȣȭ�ϸ� �����ɷ� ��ȣȭ �Ѵ�. (�ܹ��� ��ȣȭ)
	//	��ȣȭ ��ȣȭ�� ��ȣȭ�Ѱ� ���� ������ �ٲ۴�
	@Bean
	public AuthenticationManager authenticationManager(
			AuthenticationConfiguration authenticationConfiguration) throws Exception{
		
		return authenticationConfiguration.getAuthenticationManager();
	}
 	
	
}

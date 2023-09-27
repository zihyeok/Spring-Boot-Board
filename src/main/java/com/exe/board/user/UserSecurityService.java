package com.exe.board.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserSecurityService implements UserDetailsService{
	
	private final UserRepository userRepository;
	
	
	// 사용자명으로 비밀번호를 조회해서 리턴하는 메소드  
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		Optional<SiteUser> searchUser = userRepository.findByUserName(userName);
	
		if (!searchUser.isPresent()) {//jdk11 - isEmpty()
			throw new UsernameNotFoundException("사용자를 찾을 수 없습니다,");
		}
		
		SiteUser siteUser = searchUser.get();
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		if ("admin".equals(userName)) {//ADMIN 권한
			authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
		}else {//USER 권한
			authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
		}
		
	//사용자명,비밀번호,권한 입력으로 스프링 시큐리티의 User 객채를 생성후 리턴	
	return new User(siteUser.getUserName(),siteUser.getPassword(),authorities);
	}
	
	//UserSecurityService는 스프링이 시큐리티가 제공하는
	//UserDetailsService 인터페이스로 구현을 하고
	//loadUserbyUsername 메소드는 사용자 명으로 비밀번호를 조회하여
	//리턴하는 메소드이다.
	
	//스프링 시큐리티는 loadUserByUsername메소드에 의해 리턴된
	//User 객체의 비밀번호가 로그인시 입력받은 비밀번호와 일치하는지
	//비교 검사하는 로직을 내부적으로 가지고 있다.
	
	
}

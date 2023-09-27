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
	
	
	// ����ڸ����� ��й�ȣ�� ��ȸ�ؼ� �����ϴ� �޼ҵ�  
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		Optional<SiteUser> searchUser = userRepository.findByUserName(userName);
	
		if (!searchUser.isPresent()) {//jdk11 - isEmpty()
			throw new UsernameNotFoundException("����ڸ� ã�� �� �����ϴ�,");
		}
		
		SiteUser siteUser = searchUser.get();
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		if ("admin".equals(userName)) {//ADMIN ����
			authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getValue()));
		}else {//USER ����
			authorities.add(new SimpleGrantedAuthority(UserRole.USER.getValue()));
		}
		
	//����ڸ�,��й�ȣ,���� �Է����� ������ ��ť��Ƽ�� User ��ä�� ������ ����	
	return new User(siteUser.getUserName(),siteUser.getPassword(),authorities);
	}
	
	//UserSecurityService�� �������� ��ť��Ƽ�� �����ϴ�
	//UserDetailsService �������̽��� ������ �ϰ�
	//loadUserbyUsername �޼ҵ�� ����� ������ ��й�ȣ�� ��ȸ�Ͽ�
	//�����ϴ� �޼ҵ��̴�.
	
	//������ ��ť��Ƽ�� loadUserByUsername�޼ҵ忡 ���� ���ϵ�
	//User ��ü�� ��й�ȣ�� �α��ν� �Է¹��� ��й�ȣ�� ��ġ�ϴ���
	//�� �˻��ϴ� ������ ���������� ������ �ִ�.
	
	
}

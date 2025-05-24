package com.rm.service.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rm.dto.user.CustomUserDetails;
import com.rm.dto.user.UserEntity;
import com.rm.mapper.user.UserMapper;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	UserMapper userMapper;
	
	public CustomUserDetailsService(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity user = userMapper.getUserById(username);
		if(user == null) {
			return null;
		}
		
		// User타입의 값 중 필요한 값을 UserDetials에 담아서 전달해야함!
		return new CustomUserDetails(user);
		
		
		
	}

}



















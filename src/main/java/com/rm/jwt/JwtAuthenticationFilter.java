package com.rm.jwt;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rm.dto.user.CustomUserDetails;
import com.rm.dto.user.UserDTO;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	AuthenticationManager authenticationManager;
	JwtUtils jwtUtils;
	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
		this.authenticationManager = authenticationManager;
		this.jwtUtils = jwtUtils;
	}

	// Authentication을 시도할 때 어떻게 시도할 건지 설정하는 메소드
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		// UsernamePasswordAuthenticationToken은 단순히 아이디와 비밀번호를 한번에 담기 위한 객체이다. 
		// AuthenticationManager한테 넘겨줄때는 아이디랑 비밀번호를 여기에 담아서 줘야함
		// form-data 형식의 body에 username, password라는 key값에 들어있는 데이터만 받아올 수 있다.
//		String id = obtainUsername(request);
//		String pwd = obtainPassword(request);
		System.err.println("attemptAuthentication() 메소드 호출됨");
	
		try {
			ServletInputStream inputStream = request.getInputStream();
			System.err.println("attemptAuthentication() 메소드 호출됨1");
			String body = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
			System.err.println("attemptAuthentication() 메소드 호출됨2");
			
			
			ObjectMapper objectMapper = new ObjectMapper();
			System.err.println("attemptAuthentication() 메소드 호출3");
			UserDTO udto = objectMapper.readValue(body, UserDTO.class);
			System.err.println("attemptAuthentication() 메소드 호출4");
			
			String id = udto.getUserId();
			String pwd = udto.getPassword();
			System.err.println("attemptAuthentication() 메소드 호출5");
			
			
			
			UsernamePasswordAuthenticationToken userInfo = new UsernamePasswordAuthenticationToken( id, pwd , null);
			System.err.println("attemptAuthentication() 메소드 호출6");
			
			
			return authenticationManager.authenticate(userInfo);
			
			
		}catch(IOException ex) {
			return null;
		}
		
		
	}
	
	//로그인 성공하면 무엇을 할지 설정하는 메소드
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// jwt 를 만들고 응답으로 전달한다
		System.out.println("성공입니다");
		
		// 로그인한 User 정보 가져오기
		CustomUserDetails userDetails = (CustomUserDetails)authResult.getPrincipal();
		// userId
		String userId = userDetails.getUsername();
		
		// userRole
		Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
		String role = null;
		for( GrantedAuthority auth : authorities) {
			role = auth.getAuthority();
		}
		
		String token = jwtUtils.createJwt(userId, role, 30*60*1000L );  // 30분으로 설정
		
		ObjectMapper mapper = new ObjectMapper();
		
		Map<String, String> responseBody = new HashMap<>();
		
		responseBody.put("userId", userId);
		responseBody.put("token", token);
		
		response.getWriter().write(mapper.writeValueAsString(responseBody));
		
		response.addHeader("Authorization",  "Bearer " + token);
		
	}
	
	// 로그인 실패하면 무엇을 할지 설정하는 메소드
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// 401 오류로 응답 전달
		System.out.println("실패입니다");
		response.setStatus(401);
	}
	
	
}





















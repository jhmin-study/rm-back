package com.rm.jwt;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.rm.dto.user.CustomUserDetails;
import com.rm.dto.user.UserDTO;
import com.rm.dto.user.UserEntity;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtFilter extends OncePerRequestFilter {
	JwtUtils jwtUtils;
	
	public JwtFilter(JwtUtils jwtUtils) {
		this.jwtUtils = jwtUtils;
	}
	

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// jwt를 찾아서 검증하는 기능
		String authToken = request.getHeader("Authorization"); // "Bearer fweoiuhfweoighogiuhowghew"
		
		if(authToken == null || ! authToken.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		System.out.println("JWT 검증 시작!");
		
		String jwt = authToken.split(" ")[1]; // "fweoiuhfweoighogiuhowghew"
		
		if(jwtUtils.isExpired(jwt)) {
			System.out.println("token이 만료된 토큰임");
			filterChain.doFilter(request, response);
			return;
		}
		
		// 정상토큰
		System.out.println("정상 토큰으로 확인됨!");
		
		// 나중에 사용할 수도 있으니 Session에 토큰 payload에 넣었던 정보를 저장해 주자
		// 단, 이때의 session은 Stateless 형태이며, 이 요청이 끝나면 사라짐
		UserEntity user = new UserEntity();
		user.setUserId(jwtUtils.getUserId(jwt));
		
		// Spring Security에서 User정보 저장하는 UserDetails 형식으로 변환
		CustomUserDetails customUserDetails = new CustomUserDetails(user);
		
		// User정보(principal) + 비밀번호정보(credentials) + 권한정보(authorities) 로 구성된 UsernamePasswordAuthenticationToken으로 만들어서, 그 토큰을 세션에 저장
		Authentication auth  = new UsernamePasswordAuthenticationToken( customUserDetails, null , customUserDetails.getAuthorities());
		
		// auth를 세션에 등록
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		filterChain.doFilter(request, response);
		
		
	}

	

}
























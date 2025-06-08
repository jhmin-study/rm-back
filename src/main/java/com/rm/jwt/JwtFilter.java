package com.rm.jwt;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
		
		// user 관련 api호출이 아닌 경우 재발급 X
		String reqURLStr = request.getRequestURL().toString();
		System.out.println(reqURLStr);
		String[] reqURL = reqURLStr.split("/");
		for (String string : reqURL) {
			System.out.println(string + ",");
		}
		if (!reqURL[3].equals("api") ||
				!reqURL[4].equals("user")) {
			filterChain.doFilter(request, response);
			return;
		}
		// response를 ContentCachingResponseWraper 클래스로 변경
		ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) response);
		
		filterChain.doFilter(request, responseWrapper);
		
		// responseWrapper에서 수정
		byte[] responseArray = responseWrapper.getContentAsByteArray();
		String responseStr = new String(responseArray, StandardCharsets.UTF_8);
		System.out.println("responseStr : " + responseStr);
		
		JsonNode node = new ObjectMapper().readTree(responseStr);
		
		// 빈 배열일 경우 빈 객체로 변환
		if (responseStr.equals("[]")) {
			responseStr = "{}";
		}

		
		String arrayNodeName = new ArrayNode(null).getClass().getName();
		// ArrayNode면 재발급을 진행하지 않는다.
		if (node.getClass().getName().equals(arrayNodeName)) {
			return;
		}
		
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
		
		
		// userId
		String userId = customUserDetails.getUsername();
		System.out.println(userId);
		// userRole
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		String role = null;
		for( GrantedAuthority authEntity : authorities) {
			role = authEntity.getAuthority();
		}
		
		String token = jwtUtils.createJwt(userId, role, 30*60*1000L );  // 30분으로 설정
		
//		responseBody.put("userId", userId);
//		responseBody.put("token", token);
		System.out.println("response 수정");
		
		
		System.out.println("responseStr : " + responseStr);
		System.out.println("node : " + node.getClass().getName());
		
		((ObjectNode)node).put("userId", userId);
		((ObjectNode)node).put("token", token);
		
		String newResponse = new ObjectMapper().writeValueAsString(node);
		response.setContentType("application/json");
//		response.setContentLength(newResponse.length());
		response.getOutputStream().write(newResponse.getBytes(StandardCharsets.UTF_8));
		
//		response.getWriter().write(mapper.writeValueAsString(responseBody));
//		response.addHeader("Authorization",  "Bearer " + token);
		
		System.out.println("토큰발급 끝 : " + newResponse);
		System.out.println("newResponse length : " + newResponse.length());
		System.out.println("token length : " + token.length());
		System.out.println("response buffer size : " + response.getBufferSize());
		
	}

	

}
























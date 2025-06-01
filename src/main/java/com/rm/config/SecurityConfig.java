package com.rm.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.rm.jwt.JwtAuthenticationFilter;
import com.rm.jwt.JwtFilter;
import com.rm.jwt.JwtUtils;

/* ************************************************************************** */
/* Class명  : SecurityConfig                                                  */
/* 개발자   : 김재용                                                          */
/* 개발일자 : 2025.05.10                                                      */
/* 기능설명 : JWT 기반 인증을 위한 Security 설정 클래스                       */
/* ************************************************************************** */
@Configuration     // 이 클래스는 설정을 위한 자바 클래스야! 알려주는 어노테이션
@EnableWebSecurity // 설정은 설정인데, Security 설정 클래스야!
public class SecurityConfig {
	
	// CORS 설정
	// CSRF 설정
	// formlogin 설정
	// authorizerezuest 설정
	
	AuthenticationConfiguration configuration;
	JwtUtils jwtUtils;
	
	public SecurityConfig(AuthenticationConfiguration configuration,JwtUtils jwtUtils) {
		this.configuration = configuration;
		this.jwtUtils = jwtUtils;
	}
	
	
	@Bean
	public AuthenticationManager getAuthenticationManager(AuthenticationConfiguration configuration) 
	throws Exception{
		
		return configuration.getAuthenticationManager();
	}
	
	// @Configuration 안의 @Bean은 --> 이미 만들어져있는 클래스 타입 객체를 등록하고 싶을때 사용
	// --> 우리가 실행할 일이 없는 메소드 --> spring boot가 최초로 1회 실행하고, 
	// 실행 결과를 Container에 Bean으로 등록시키는 용도의 메소드
	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEncoder() {

		return new BCryptPasswordEncoder();
	}

	//Security 설정, 
	// return은 무조건 SecurityFilterChain 타입이어야 한다
	// 매개변수는 HttpSecurity 를 인자로 받아와야해.
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
			
		http.cors((auth)-> auth.configurationSource((request)->{
	         CorsConfiguration config = new CorsConfiguration();
	         // 어디로부터 들어오는 요청을 허용할 것인지, VUE의 포트번호를 입력
	         config.setAllowedOrigins(Collections.singletonList("http://localhost:8004"));
	         // 어떤 요청방식에 대해 허용할 것인지 (GET, DELETE ,... )
	         config.setAllowedMethods(Collections.singletonList("*"));
	         
	         // 쿠키를 전달 받을 것인지 설정, true --> Origin을 * 로 설정할 수 없음
	         config.setAllowCredentials(true);
	         
	         // Header 설정 허용 여부
	         config.setAllowedHeaders(Collections.singletonList("*"));
	         
	         // 브라우저가 닫혀도, 쿠키가 메모리에 남아있는 시간 설정
	         config.setMaxAge(3600L);
	         
	         // 응답헤더 수정 권한
	         config.setExposedHeaders(Collections.singletonList("Authorization"));
	         return config;
	      }));
		
		//csrf 설정
		http.csrf((auth)-> auth.disable() );
		
		//기본으로 뜨는 로그인 화면과 관련된 설정
		http.formLogin( (auth) -> auth.disable() );
		
		// /api/join으로 GET요청은 로그인 하지 않아도 할 수 있도록 하고 싶음
		// authorizeHttpRequests --> 요청에 대한 권한을 설정하는 메소드
		// TODO : 개발 중에는 permitAll 설정. 주소 추가할 때마다 추가해줘야 함
		http.authorizeHttpRequests( (auth)-> auth.requestMatchers("/api/workplace").authenticated()
				.anyRequest().permitAll() 
			);
		

		http.addFilterBefore(new JwtFilter(jwtUtils), JwtAuthenticationFilter.class);
		
		JwtAuthenticationFilter myFilter = new JwtAuthenticationFilter( getAuthenticationManager(configuration) , jwtUtils );
		myFilter.setFilterProcessesUrl("/api/login");
		
		http.addFilterAt( 
				myFilter , 
				UsernamePasswordAuthenticationFilter.class 
			);
		
		
		
		// session --> jwt 방식은 세션로그인 방식이 아니기 때문에 , session을 STATELESS 상태로 변경
		// STATEFUL --> 이전 트랜잭션에 대한 정보를 저장하는 것
		// STATELESS --> 과거 트랜잭션에 대한 정보를 저장하지 않음, 각 요청은 독립적으로 수행이 됨
		http.sessionManagement( (auth)-> auth.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		return http.build();
	}
	
	
	
}

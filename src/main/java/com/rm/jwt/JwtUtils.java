package com.rm.jwt;


import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;


@Component
public class JwtUtils {
	SecretKey secretKey;
	
	public JwtUtils(@Value("${spring.jwt.secret}") String secret) {
		
		this.secretKey = new SecretKeySpec(secret.getBytes( StandardCharsets.UTF_8 ), Jwts.SIG.HS256.key().build().getAlgorithm() );
	}
	
	// 토큰 만드는 메소드
	public String createJwt(String userId, String userRole , Long expiredMs) {
		Date startTime = new Date(System.currentTimeMillis());
		Date endTime = new Date( System.currentTimeMillis() + expiredMs );

		// ID와 Role을 payload에 넣어주겠다.
		String jsonWebToken = Jwts.builder().claim("userId", userId).claim("userRole", userRole)
			.issuedAt( startTime )
			.expiration( endTime )
			.signWith(secretKey)
			.compact();
		
		System.out.println("만들어진 토큰 : " +  jsonWebToken);
		System.out.println("expired at " + endTime);
		return jsonWebToken;
	}
	
	// token에서 userId 꺼내오는 메소드
	public String getUserId(String token) {

		return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("userId", String.class);
	}
	
	// token에서 userRole 꺼내오는 케소드
	public String getUserRole(String token) {

		return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("userRole", String.class);
	}
	
	
	//  token이 만료된 토큰인지 확인하는 메소드 --> true --> 만료되었음
	public boolean isExpired(String token) {
		
		return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
	}
	
}



























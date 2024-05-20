package com.ssafit.pjt.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	
//	@Value("${jwt.key}")
	private String key = "SSAFIT_TOTAL_PROJECT_YJ_JH_SSAFIT";
	
	@Value("${jwt.accesstoken.expiretime}")
	private Long accessTokenExpireTime;
	   
	@Value("${jwt.refreshtoken.expiretime}")
	private Long refreshTokenExpireTime;
	
	private SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
	
	//다양한 데이터를 Map으로 받아서 처리를 할 수도 있지만 심플하게 아이디만 입력받아서 처리하겠다. 
	public String createAccessToken(String userId) {
		Date exp = new Date(System.currentTimeMillis() + 1000*accessTokenExpireTime); 
		return Jwts.builder().header().add("typ","JWT").and().claim("id", userId)
				.expiration(exp).signWith(secretKey).compact();
		//compact해야 스트링으로 완성되면서 반환..
		
	}
	
	public String createRefreshToken(String userId) {
		Date exp = new Date(System.currentTimeMillis() + 1000*refreshTokenExpireTime);
		return Jwts.builder().header().add("typ","JWT").and().claim("id", userId)
				.expiration(exp).signWith(secretKey).compact();
		
	}
	
	public Jws<Claims> validate(String token){
		//데이터를 얻기 위함이 아니고 유효성 검사... 그냥 한 번 메서드 돌리는거임. 
		//실제로 확인하려는 용도가 아니고, 유효기간이 지났다면 알아서 에러를 발생시키려고 함.
		
		return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
		
	
	}
	
//	public static void main(String[] args) {
//		JwtUtil j = new JwtUtil();
//		String token = j.createAccessToken("user1");
//		System.out.println(j.validate(token).getPayload().get("id"));
//	}
	public String getId(String token) {
		return (String)Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("id");
	}
	
}




package com.ssafit.pjt.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	private String key = "SSAFY_NonMajor_JavaTrack_SecretKey";
	private SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
	
	//다양한 데이터를 Map으로 받아서 처리를 할 수도 있지만 심플하게 아이디만 입력받아서 처리하겠다. 
	public String createToken(String id) {
		Date exp = new Date(System.currentTimeMillis() + 1000*60*60); //유효시간은 1시간이다.
		return Jwts.builder().header().add("typ","JWT").and().claim("id", id)
				.expiration(exp).signWith(secretKey).compact();
		//compact해야 스트링으로 완성되면서 반환..
		
	}
	
	public Jws<Claims> validate(String token){
		//데이터를 얻기 위함이 아니고 유효성 검사... 그냥 한 번 메서드 돌리는거임. 
		//실제로 확인하려는 용도가 아니고, 유효기간이 지났다면 알아서 에러를 발생시키려고 함.
		
		return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);

	
	}
}




//package com.ssafy.jwt.util;
//
//import java.io.UnsupportedEncodingException;
//import java.util.Date;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.JwtBuilder;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.MalformedJwtException;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.SignatureException;
//import io.jsonwebtoken.UnsupportedJwtException;
//
//
//@Component
//public class JwtUtil {
//	
//	@Value("${jwt.key}")
//	private String jwtKey;
//	
//	@Value("${jwt.accesstoken.expiretime}")
//	private Long accessTokenExpireTime;
//	
//	@Value("${jwt.refreshtoken.expiretime}")
//	private Long refreshTokenExpireTime;
//	
//	public String createAccessToken(String userId) throws UnsupportedEncodingException {
//		System.out.println("jwt: ");
//		System.out.println(jwtKey);
//		
//		long currentTime = System.currentTimeMillis(); // 현재시간
//		
//		JwtBuilder jwtAccessTokenBuilder = Jwts.builder();
//		
//		jwtAccessTokenBuilder.claim("userId", userId);
//		jwtAccessTokenBuilder.setIssuedAt(new Date(currentTime));
//		jwtAccessTokenBuilder.setExpiration(new Date(currentTime + accessTokenExpireTime*1000));
//		jwtAccessTokenBuilder.signWith(SignatureAlgorithm.HS256, jwtKey.getBytes("UTF-8"));
//		
//		return jwtAccessTokenBuilder.compact();
//	}
//	
//	public String createRefreshToken(String userId) throws UnsupportedEncodingException {
//		long currentTime = System.currentTimeMillis(); // 현재시간
//		
//		JwtBuilder jwtRefreshTokenBuilder = Jwts.builder();
//		
//		jwtRefreshTokenBuilder.claim("userId", userId);
//		jwtRefreshTokenBuilder.setIssuedAt(new Date(currentTime));
//		jwtRefreshTokenBuilder.setExpiration(new Date(currentTime + refreshTokenExpireTime*1000));
//		jwtRefreshTokenBuilder.signWith(SignatureAlgorithm.HS256, jwtKey.getBytes("UTF-8"));
//		
//		return jwtRefreshTokenBuilder.compact();
//	}
//	
//	public boolean validCheck(String token){
//		
//		try {
//			System.out.println(token);
//			Jwts.parser().setSigningKey(jwtKey.getBytes("UTF-8")).parseClaimsJws(token);
//		} catch(Exception e) { // token을 파싱하는데 에러가 발생했다면 유효한 토큰이 아님.
//			System.out.println(e);
//			return false;
//		}
//		System.out.println("valid");
//		return true;
//	}
//
//}
//

package com.ssafit.pjt.Interceptor;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafit.pjt.util.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor{
	private final String HEADER_AUTH = "access-token";
	private static final Logger logger = Logger.getLogger(JwtInterceptor.class.getName());
	@Autowired
	private JwtUtil jwtUtil;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		logger.info("Interceptor preHandle 메서드 호출: " + request.getRequestURI());
//		System.out.println(logger.get);
//		if(request.getMethod().equals("OPTIONS")) {
//			return true;
//			//options 요청은 
//			//클라이언트는 서버에게 실제 요청을 보내려고 했을 때 사전 요청을 보내서 서버가 현재
//			//요청을 수락할 수 있는 상태인지를 확인한다.
//			//그게 options요청이다,
//			// 확인하는 용도에서는 우선 통과시키고,,,, 확인을 하겠다.
//		}
//		
		String accessToken = request.getHeader(HEADER_AUTH);
		
		if(accessToken != null) {
			jwtUtil.validate(accessToken);
			//유효한 토큰인지 검증.
			return true;
		}
		System.out.println("여기옴");
		throw new Exception("재로그인이 필요합니다.");
		
	}
}

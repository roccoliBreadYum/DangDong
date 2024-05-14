package com.ssafit.pjt.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafit.pjt.util.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor{
	private final String HEADER_AUTH = "access-token";
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		
		if(request.getMethod().equals("OPTIONS")) {
			return true;
			//options 요청은 
			//클라이언트는 서버에게 실제 요청을 보내려고 했을 때 사전 요청을 보내서 서버가 현재
			//요청을 수락할 수 있는 상태인지를 확인한다.
			//그게 options요청이다,
			// 확인하는 용도에서는 우선 통과시키고,,,, 확인을 하겠다.
		}
		
		String token = request.getHeader(HEADER_AUTH);
		if(token != null) {
			jwtUtil.validate(token);
			//터지면 예외 발생해서 터짐.
			return true;
		}
		
		throw new Exception("유효하지 않은 접근이다.");
		
	}
}

package com.ssafit.pjt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafit.pjt.Interceptor.JwtInterceptor;

//Web 관련 설정
//자원설정. 
// 스프링부트를 REST API를 할거양. 데이터만 주는거야. 그러면 이거는 필요 없을거임.
//cors -> 교차출처원칙을 허용하겠다
//		다른 출처를 요청했을 때, 브라우저가 막아버려.
@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 자원 설정
		registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
	}

	// CORS 에러 전역 처리
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
				.allowedOrigins("http://localhost:5173")
				.allowCredentials(true)
				.exposedHeaders("*")
				.maxAge(3600); // Preflight
		// Caching
	}

	// 인터셉터 등 처리 가능
	// implements WebMvcConfigurer있기 때문에 오버라이드 가넝
	@Autowired
	private JwtInterceptor jwtInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor).addPathPatterns("/**").excludePathPatterns("/api-user/user/login", "/resources/**",
				"/static/**",
				"/swagger-ui/**", "/v3/api-docs/**", "/api-user/user/refresh"); // 이건 통과 나머지는 안돼!
	}
}

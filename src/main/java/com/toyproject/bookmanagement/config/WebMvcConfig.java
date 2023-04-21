package com.toyproject.bookmanagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")							// 모든 요청
				.allowedMethods("*")						// 모든 메소드
				.allowedOrigins("*");	// 해당 port 요청    --@CrossOrigin 매번 호출안하기 위해서--
//				.allowedOrigins("http://localhost:3000");
	}
}

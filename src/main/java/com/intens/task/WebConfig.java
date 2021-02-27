package com.intens.task;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

	@Configuration
	public  class WebConfig implements WebMvcConfigurer {
		@Override
		public void addCorsMappings(CorsRegistry registry) {
			
			registry.addMapping("/api/candidates")
					.allowedOrigins("http://localhost:3000")
					.allowedMethods("OPTIONS", "HEAD", "GET", "PUT", "POST", "DELETE", "PATCH")
					.allowCredentials(true)
					.maxAge(3600);
			
			registry.addMapping("/api/skills")
			.allowedOrigins("http://localhost:3000")
			.allowedMethods("OPTIONS", "HEAD", "GET", "PUT", "POST", "DELETE", "PATCH")
			.allowCredentials(true)
			.maxAge(3600);
			
			

			registry.addMapping("/**")
					.allowedOrigins("*")
					.allowedMethods("OPTIONS", "HEAD", "GET", "PUT", "POST", "DELETE", "PATCH")
					.allowCredentials(true)
					.maxAge(3600);
					
		}
	
	}



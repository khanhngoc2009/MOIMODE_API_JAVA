package com.it15306.config;

import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class AppCorsConfiguration {
	
	@Bean
    public WebMvcConfigurer  corsConfigurer() {
        return new WebMvcConfigurer() {
        	@Override
        	public void addCorsMappings(CorsRegistry registry) {
        		registry.addMapping("/**")
        		.allowedMethods("PUT, GET, POST, DELETE")
        		.allowedHeaders("*")
        		.allowedOrigins("http://localhost:3000");
        	}
		};
        
    }
}

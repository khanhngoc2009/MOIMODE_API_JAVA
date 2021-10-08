package com.it15306.config;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.context.annotation.Bean;

import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@EnableSwagger2
@Configuration
//@EnableWebMvc
public class SwaggerConfig {
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
//          .apis(RequestHandlerSelectors.any())              
//          .paths(PathSelectors.any())                          
          .build();                                           
    }
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//	    registry.addResourceHandler("swagger-ui.html")
//	      .addResourceLocations("classpath:/META-INF/resources/");
//
//	    registry.addResourceHandler("/webjars/**")
//	      .addResourceLocations("classpath:/META-INF/resources/webjars/");
//	}
//	@Bean
//	  public Docket api() {
//	    return new Docket(DocumentationType.SWAGGER_2)
//	        .select()
//	        .apis(RequestHandlerSelectors.basePackage("com.world.hello"))
//	        .paths(regex("/api.*"))
//	        .build()
//	        .apiInfo(metaData());
//	  }
//	private ApiInfo metaData() {
//	    return new ApiInfo(
//	        "Spring Boot REST API",
//	        "Spring Boot REST API for User management",
//	        "1.0",
//	        "Terms of service",
//	        new Contact("Hello", "https://helloworld.com", "hello@world.com"),
//	        "Apache License Version 2.0",
//	        "https://www.apache.org/licenses/LICENSE-2.0",
//	        new ArrayList<>());
//	  }
}

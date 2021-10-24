package com.it15306.config;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//import io.jsonwebtoken.lang.Collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;


@EnableSwagger2
@Configuration
//@EnableWebMvc
public class SwaggerConfig {
	@Bean
    public Docket api(ServletContext servletContext) {
      return new Docket(DocumentationType.SWAGGER_2)
    		.securitySchemes(Arrays.asList(apiKey()))
          .securityContexts(Collections.singletonList(securityContext()))
          .select()
          .apis(RequestHandlerSelectors.basePackage("com.it15306.controller"))
          .build()
          ;
    }

    private SecurityContext securityContext() {
      return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.regex("/miemode_api/v1/admin/.*")).build();
    }

    private List<SecurityReference> defaultAuth() {
      final AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
      final AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{authorizationScope};
      return Collections.singletonList(new SecurityReference("Bearer", authorizationScopes));
    }

    private ApiKey apiKey() {
      return new ApiKey("Bearer", "Authorization", "header");
    }

}

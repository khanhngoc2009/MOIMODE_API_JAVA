package com.it15306.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpMethod;
import org.springframework.security.config.BeanIds;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//import com.it15306.jwt.JwtAuthenticationFilter;
import com.it15306.jwt.JwtRequestFilter;
//import com.it15306.services.UserService;
import com.it15306.services.UserServiceImpl;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl userService;
	
    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
      return super.authenticationManagerBean();
  }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Password encoder, ????? Spring Security s??? d???ng m?? h??a m???t kh???u ng?????i d??ng
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userService) // Cung c??p userservice cho spring security
                .passwordEncoder(passwordEncoder()); // cung c???p password encoder
        
        
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .cors();
        
//         Ph??n quy???n
        http.authorizeRequests()
        
            .antMatchers( "/miemode_api/v1/login", "/miemode_api/v1/user/register")
            .permitAll() 
//            
//            .antMatchers(HttpMethod.GET, "/api/v1/products/**", "/api/v1/categories/**","/api/v1/product/**","/api/v1/category/*","/storages/**")
//            .permitAll() 
            .antMatchers(HttpMethod.GET, "/miemode_api/v1/admin/**")
            .hasAuthority("ADMIN")
            .antMatchers(HttpMethod.POST, "/miemode_api/v1/admin/**")
            .hasAuthority("ADMIN")
            .antMatchers(HttpMethod.PUT, "/miemode_api/v1/admin/**")
            .hasAuthority("ADMIN")
            
//            .anyRequest().authenticated()
//            .antMatchers(HttpMethod.GET, "/miemode_api/v1/ward/*")
//            .hasAuthority("CUSTOMER")
            // T???t c??? c??c request kh??c ?????u c???n ph???i x??c th???c m???i ???????c truy c???p
        .and().
      exceptionHandling().and().sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // Th??m m???t l???p Filter ki???m tra jwt
//        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
    
    
}

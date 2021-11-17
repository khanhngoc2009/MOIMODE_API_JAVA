package com.it15306.config;

import com.it15306.jwt.JwtRequestFilter;
import com.it15306.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;

//import com.it15306.jwt.JwtAuthenticationFilter;
//import com.it15306.services.UserService;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
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
        // Password encoder, để Spring Security sử dụng mã hóa mật khẩu người dùng
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userService) // Cung cáp userservice cho spring security
                .passwordEncoder(passwordEncoder()); // cung cấp password encoder
        
        
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .cors();
        
//         Phân quyền
        http.authorizeRequests()
        
//             .antMatchers( "/miemode_api/v1/login", "/miemode_api/v1/user/register")
//             .permitAll() 
             
             .antMatchers(HttpMethod.GET, "/miemode_api/v1/admin/**")
             .hasAuthority("ADMIN")
             .antMatchers(HttpMethod.POST, "/miemode_api/v1/admin/**")
             .hasAuthority("ADMIN")
             .antMatchers(HttpMethod.PUT, "/miemode_api/v1/admin/**")
             .hasAuthority("ADMIN")
             .antMatchers( "/miemode_api/v1/infor").authenticated()
             
//            .anyRequest().authenticated()

        .and().
      exceptionHandling().and().sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // Thêm một lớp Filter kiểm tra jwt
//        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
    
    
}

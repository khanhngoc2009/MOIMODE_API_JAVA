package com.it15306.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthenticateInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(
			
		HttpServletRequest req,
		HttpServletResponse res,
		Object handle
	) {
//		HttpSession session = req.getSession();
//		if(session.getAttribute("user") == null) {
//			return false;
//		}
//		
		return true;
	}
}

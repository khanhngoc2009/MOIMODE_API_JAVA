package com.it15306.services;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
	@Autowired
	HttpSession session;
	
	public <T> T get(String name) {
		if(session.getAttribute(name) != null) {
			return (T) session.getAttribute(name);
		}
		return null;
	}
	
	public void set(String name, Object value) {
		session.setAttribute(name, value);
	}
	
	public void remove(String name) {
		session.setAttribute(name, null);
	}
}

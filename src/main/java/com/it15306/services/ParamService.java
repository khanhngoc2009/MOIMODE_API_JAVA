package com.it15306.services;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.Part;

@Service
public class ParamService {
	@Autowired
	HttpServletRequest request;
	
	public String getString(String name, String defaultValue){
		
		if (request.getParameter(name) != null) {
			return request.getParameter(name);
		}
		return defaultValue;
	}
	
	public int getInt(String name, int defaultValue){
		if (request.getParameter(name) != null) {
			return Integer.parseInt(request.getParameter(name));
		}
		return defaultValue;
	}
	
	public double getDouble(String name, double defaultValue){
		if (request.getParameter(name) != null) {
			return Double.parseDouble(request.getParameter(name));
		}
		return defaultValue;
	}
	
	public boolean getBoolean(String name, boolean defaultValue){
		if (request.getParameter(name) != null) {
			return Boolean.getBoolean(request.getParameter(name));
		}
		return defaultValue;
	}
	
	public Date getDate(String name, String pattern) throws ParseException{
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		if(request.getParameter(name) != null) {
			Date dateParam = formatter.parse(request.getParameter(name));
			if (dateParam != null) {
				return dateParam;
			}
		}
        
        Date date = formatter.parse(pattern);
		return date;
	}
	public File save(MultipartFile file, String path) throws IOException, ServletException {
		
		
		return null;
		
	}
	
	
}

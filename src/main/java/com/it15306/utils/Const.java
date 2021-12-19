package com.it15306.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Const {
		public static final String passwordDefault= "Miemode@1234";
		public static final String urlServer= "http://35.198.241.56";
		public String getDate (String startDate) {
			try {
//				String sDate1="2021/12/19";  
				SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");  
				Calendar cal = Calendar.getInstance();
				cal.setTime( format.parse( startDate ) );
				cal.add( Calendar.DATE, 1 );
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
				String strDate = dateFormat.format(cal.getTime());
				return strDate;
			} catch (Exception e) {
			}
			return null;
		}
}

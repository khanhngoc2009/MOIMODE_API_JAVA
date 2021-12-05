package com.it15306.config;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;


public class VnpayConfig {
//	URL Thanh toán là địa chỉ URL mang thông tin thanh toán.
	public static String vnp_PayUrl="https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
	 public static String vnp_Returnurl = "http://35.198.241.56/carts";
//	   public static String vnp_TmnCode = "3IDEO9Q5";
//	    public static String vnp_HashSecret = "ZXFBIUDXWGGAYUKRCZAIXTITBWYWSDMY";
	 
	    public static String vnp_apiUrl = "http://sandbox.vnpayment.vn/merchant_webapi/merchant.html";
	    public static String vnp_TmnCode = "9HYUAVCC";
	    public static String vnp_HashSecret = "RLVBIULWYQJJYOTIOAHWWKCTPQNHSZAF";
	    
	    
	    public static String getRandomNumber(int len) {
	        Random rnd = new Random();
	        String chars = "0123456789";
	        StringBuilder sb = new StringBuilder(len);
	        for (int i = 0; i < len; i++) {
	            sb.append(chars.charAt(rnd.nextInt(chars.length())));
	        }
	        System.out.println(sb.toString());
	        return sb.toString();
	    }
	    
	    public static String getIpAddress(HttpServletRequest request) {
	        String ipAdress;
	        try {
	            ipAdress = request.getHeader("X-FORWARDED-FOR");
	            if (ipAdress == null) {
	                ipAdress = request.getRemoteAddr();
	            }
	        } catch (Exception e) {
	            ipAdress = "Invalid IP:" + e.getMessage();
	        }
	        System.out.println("dc Address: "+ipAdress);
	        return ipAdress;
	    }
	    
	  //Util for VNPAY
	    public static String hashAllFields(Map fields) throws UnsupportedEncodingException {
	        // create a list and sort it
	        List fieldNames = new ArrayList(fields.keySet());
	        Collections.sort(fieldNames);
	        // create a buffer for the md5 input and add the secure secret first
	        StringBuilder sb = new StringBuilder();
	        sb.append(vnp_HashSecret);
	        Iterator itr = fieldNames.iterator();
	        while (itr.hasNext()) {
	            String fieldName = (String) itr.next();
	            String fieldValue = (String) fields.get(fieldName);
	            if ((fieldValue != null) && (fieldValue.length() > 0)) {
	                sb.append(fieldName);
	                sb.append("=");
	                sb.append(URLDecoder.decode(fieldValue,"UTF-8"));
	            }
	            if (itr.hasNext()) {
	                sb.append("&");
	            }
	        }
	        
	        return Sha256(sb.toString());
	    }
	    
	    public static String Sha256(String message) {
	        String digest = null;
	        try {
	            MessageDigest md = MessageDigest.getInstance("SHA-256");
	            byte[] hash = md.digest(message.getBytes("UTF-8"));

	            // converting byte array to Hexadecimal String
	            StringBuilder sb = new StringBuilder(2 * hash.length);
	            for (byte b : hash) {
	                sb.append(String.format("%02x", b & 0xff));
	            }

	            digest = sb.toString();

	        } catch (UnsupportedEncodingException ex) {
	            digest = "";
	            // Logger.getLogger(StringReplace.class.getName()).log(Level.SEVERE,
	            // null, ex);
	        } catch (NoSuchAlgorithmException ex) {
	            // Logger.getLogger(StringReplace.class.getName()).log(Level.SEVERE,
	            // null, ex);
	            digest = "";
	        }
	        System.out.println("digest: "+digest);
	        return digest;
	    }
}

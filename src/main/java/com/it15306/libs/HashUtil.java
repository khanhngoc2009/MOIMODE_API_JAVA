package com.it15306.libs;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class HashUtil {
	// Không nhìn thấy pasword của user
	public static String hash (String plain) {
		
		return BCrypt.hashpw(plain, BCrypt.gensalt());
	}
	
	public static boolean plain(String plain, String hashed) {
		return BCrypt.checkpw(plain, hashed);
	}
}

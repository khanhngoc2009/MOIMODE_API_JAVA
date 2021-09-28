package com.it15306.payload;


import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
//@EqualsAndHashCode
@ToString
public class RequestLogin implements  Serializable{
	private String username;
    private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
}

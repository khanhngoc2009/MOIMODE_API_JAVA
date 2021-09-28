package com.it15306.dto;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class UserDTO {
	private Integer id;
	@NotNull
	@NotBlank
	private String username;
	private String password;
	
	@NotNull
	@Email(message = "Chưa đúng định dạng email")
	@NotBlank
	private String email;
	
//	@NotNull
//	@NotBlank
//	private String password;
	
	private String photo;
	
	
	@NotNull
	private Integer admin;
	
	private String roles;
	
	@NotNull
	private Integer activated;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Integer getAdmin() {
		return admin;
	}

	public void setAdmin(Integer admin) {
		this.admin = admin;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public Integer getActivated() {
		return activated;
	}

	public void setActivated(Integer activated) {
		this.activated = activated;
	}
	
	
}

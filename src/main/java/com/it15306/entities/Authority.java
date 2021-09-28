package com.it15306.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Authorities")
@Getter
@Setter
@Component
public class Authority {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
//	@OneToMany(mappedBy = "auth")
//	private List<User> users = new ArrayList<>();
	
	
	private Integer roleid;
	private String username;
	
}

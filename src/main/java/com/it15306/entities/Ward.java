package com.it15306.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Ward")
@Getter
@Setter
@Component
public class Ward {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ward_id")
	private Integer ward_id;
	
//	@Basic
//	@Column(name = "create_date")
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date create_date;
	private String ward_name; 
	private Integer status;
	
	
	@ManyToOne
	@JoinColumn(name = "district_id")
	private District district = new District();
	
	@OneToMany(mappedBy = "ward")
	private List<User> users = new ArrayList<>();
}

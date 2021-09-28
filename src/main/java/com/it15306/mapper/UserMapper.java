package com.it15306.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.it15306.dto.UserDTO;
import com.it15306.entities.User;

@Component
public class UserMapper {
	
	@Autowired
	private ModelMapper Mapper;
	
	@Autowired
	private User userEntity;
	
	@Autowired
	private UserDTO userDTO;
	
	public User ConvertToEntity (UserDTO dto) {
		Mapper.map(dto, this.userEntity);
		return this.userEntity;
		
	}
	public UserDTO ConvertToDTO (User user) {
		Mapper.map(user, this.userDTO);
		return this.userDTO;
	}
}

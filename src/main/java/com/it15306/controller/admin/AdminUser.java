package com.it15306.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.it15306.config.DataResponseList;
import com.it15306.dto.PageDto;
import com.it15306.dto.UserDTO;
import com.it15306.entities.District;
import com.it15306.entities.Province;
//import com.it15306.entities.Category;
import com.it15306.entities.User;
import com.it15306.entities.Ward;
import com.it15306.libs.HashUtil;
import com.it15306.mapper.UserMapper;
import com.it15306.repository.UserRepository;
//import com.it15306.services.ProductService;
import com.it15306.services.UserServiceImpl;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
@RequestMapping("/miemode_api/v1")
public class AdminUser {

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private UserMapper mapper;
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/user/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User create(@Valid @RequestBody UserDTO dto) {
		User user = mapper.ConvertToEntity(dto);
		
		String hashPass = HashUtil.hash(user.getPassword());
		user.setPassword(hashPass);
		
		String nameFile = user.getPhoto();
		user.setPhoto("http://localhost:9090/storages/"+nameFile);
		
		this.userService.saveUser(user);
		return user;
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public UserDTO getDetail(@PathVariable("id") User entity) {
		UserDTO userDTO = mapper.ConvertToDTO(entity);
		return userDTO;
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User update(
			@Valid @RequestBody UserDTO dto,
			@PathVariable Integer id
	) {
		User user = mapper.ConvertToEntity(dto);
		User oldUser = userService.getById(String.valueOf(id));
		
		if(!oldUser.getPhoto().equalsIgnoreCase(user.getPhoto())) {
			String nameFile = user.getPhoto();
			user.setPhoto("http://localhost:9090/storages/"+nameFile);
		}
		
		user.setId(id);
		this.userService.saveUser(user);
		return user;
	}
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/user/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public UserDTO delete(@PathVariable("id") User user) {
		UserDTO userDTO = mapper.ConvertToDTO(user);
		this.userService.delete(String.valueOf(user.getId()));
		return userDTO;
	}
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/users", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> getAll(PageDto dto) {
		DataResponseList<UserDTO> dataRes = new DataResponseList<UserDTO>();
		try {
			ModelMapper modelMapper = new ModelMapper();
			List<User> users = this.userService.getAllUsers();
			List<UserDTO> userDTOs =new ArrayList<UserDTO>();
			if (users.size() > 0) {
				
				for (int i = 0; i < users.size(); i++) {
					userDTOs.add(modelMapper.map(users.get(i), UserDTO.class));
				}
			}
			dataRes.setMessage("Thanh cong");
			dataRes.setCode(200);
			dataRes.setListData(userDTOs);
			return new ResponseEntity<>( dataRes,HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			dataRes.setCode(HttpStatus.FAILED_DEPENDENCY.value());
			dataRes.setMessage("That bai");
//			dataRes.setListData(null)
			return new ResponseEntity<>(dataRes,HttpStatus.FAILED_DEPENDENCY);
		}
		
	}

}

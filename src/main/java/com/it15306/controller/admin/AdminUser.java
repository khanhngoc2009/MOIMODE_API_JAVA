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
import com.it15306.dto.user.dataBodyUser;
import com.it15306.dto.user.datatupdateUser;
import com.it15306.dto.user.responUser;
import com.it15306.entities.District;
import com.it15306.entities.Province;
//import com.it15306.entities.Category;
import com.it15306.entities.User;
import com.it15306.entities.Ward;
import com.it15306.libs.HashUtil;
import com.it15306.mapper.UserMapper;
import com.it15306.repository.UserRepository;
import com.it15306.services.UserService;
//import com.it15306.services.ProductService;
import com.it15306.services.UserServiceImpl;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200","http://35.198.241.56" })
@RestController
@RequestMapping("/miemode_api/v1")
public class AdminUser {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserService userService2;
	
	@Autowired
	private UserMapper mapper;
	
//	@PreAuthorize("hasAuthority('ADMIN')")
//	@RequestMapping(value = "/admin/user/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public ResponseEntity<User> create(@Valid @RequestBody UserDTO dto) {
//		try {
//			User user = mapper.ConvertToEntity(dto);
//			
//			String hashPass = HashUtil.hash(user.getPassword());
//			user.setPassword(hashPass);
//			
//			String nameFile = user.getPhoto();
//			user.setPhoto("http://34.87.157.20:8089/storages/"+nameFile);
//			
//			this.userService.saveUser(user);
//			return ResponseEntity.ok(user);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return ResponseEntity.badRequest().build();
//	}
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/user/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<UserDTO> create(@RequestBody responUser data) {
		try {
			UserDTO dto = 	userService2.createUser(data);
			if(dto != null) {
				
				return ResponseEntity.ok(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return ResponseEntity.badRequest().build();
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/user/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public UserDTO getDetail(@PathVariable("id") User entity) {
		UserDTO userDTO = mapper.ConvertToDTO(entity);
		return userDTO;
	}
//	@PreAuthorize("hasAuthority('ADMIN')")
//	@RequestMapping(value = "/user/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public ResponseEntity<User> update(
//			@Valid @RequestBody UserDTO dto
//	) {
//		try {
//			User user = mapper.ConvertToEntity(dto);
//			User oldUser = userService.getById(String.valueOf(dto.getId()));
//			
//			if(!oldUser.getPhoto().equalsIgnoreCase(user.getPhoto())) {
//				String nameFile = user.getPhoto();
//				user.setPhoto("http://34.87.157.20:8089/storages/"+nameFile);
//			}
//			
//			user.setId(dto.getId());
//			this.userService.saveUser(user);
//			return ResponseEntity.ok(user);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return ResponseEntity.badRequest().build();
//		
//	}
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/user/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public UserDTO delete(@PathVariable("id") User user) {
		UserDTO userDTO = mapper.ConvertToDTO(user);
		this.userService.delete(String.valueOf(user.getId()));
		return userDTO;
	}
	
	/*
	 * @PreAuthorize("hasAuthority('ADMIN')")
	 * 
	 * @RequestMapping(value = "/admin/users", method = RequestMethod.POST, produces
	 * = MediaType.APPLICATION_JSON_VALUE)
	 * 
	 * @ResponseBody public ResponseEntity<?> getAll(PageDto dto) {
	 * DataResponseList<UserDTO> dataRes = new DataResponseList<UserDTO>(); try {
	 * ModelMapper modelMapper = new ModelMapper(); List<User> users =
	 * this.userService.getAllUsers(); List<UserDTO> userDTOs =new
	 * ArrayList<UserDTO>(); if (users.size() > 0) {
	 * 
	 * for (int i = 0; i < users.size(); i++) {
	 * userDTOs.add(modelMapper.map(users.get(i), UserDTO.class)); } }
	 * dataRes.setMessage("Thanh cong"); dataRes.setCode(200);
	 * dataRes.setListData(userDTOs); return new ResponseEntity<>(
	 * dataRes,HttpStatus.OK); } catch (Exception e) {
	 * 
	 * dataRes.setCode(HttpStatus.FAILED_DEPENDENCY.value());
	 * dataRes.setMessage("That bai");
	 * 
	 * return new ResponseEntity<>(dataRes,HttpStatus.FAILED_DEPENDENCY); }
	 * 
	 * }
	 */
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/admin/user/list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<DataResponseList<UserDTO>> getAllUser(@RequestBody dataBodyUser data) {
		DataResponseList<UserDTO> dataRes = new DataResponseList<UserDTO>();
		try {
			List<UserDTO> list = userService2.getAllUsersv2(data);
			Long n=userService2.totalement();
			Integer sobanghi=n.intValue();
			
			if(list != null ||list.size() > 0) {
				dataRes.setListData(list);
				dataRes.setCount(sobanghi);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok(dataRes);
		
	}
	

	@RequestMapping(value = "/admin/user/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<UserDTO> updateUser(@RequestBody datatupdateUser data){
		try {
			UserDTO dto = userService2.updateUser(data);
			if(dto != null) {
				return ResponseEntity.ok(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

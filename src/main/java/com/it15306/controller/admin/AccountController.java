package com.it15306.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import com.it15306.dto.UserDTO;
import com.it15306.entities.User;
import com.it15306.jwt.JwtTokenProvider;
import com.it15306.payload.LoginResponse;
import com.it15306.payload.RequestLogin;
import com.it15306.services.UserService;
import com.it15306.services.UserServiceImpl;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
@RequestMapping("/api/v1")
public class AccountController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private UserService userservice;

	@Autowired
	private UserServiceImpl userDetailsService;

	@PostMapping("/login")
	protected ResponseEntity<?> login(@RequestBody RequestLogin request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		final String jwt = tokenProvider.generateToken(userDetails);
		return new ResponseEntity<>(new LoginResponse(jwt), HttpStatus.OK);
	}

	@RequestMapping(value = "/infor", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public UserDTO getInfor(HttpServletRequest httpServletRequest) {
		String token = httpServletRequest.getHeader("Authorization").substring(7);
		String username = tokenProvider.getUserNameFromJWT(token);
		ModelMapper modelMapper = new ModelMapper();
		User user = userservice.getByUsername(username);
		return modelMapper.map(user, UserDTO.class);

	}

}

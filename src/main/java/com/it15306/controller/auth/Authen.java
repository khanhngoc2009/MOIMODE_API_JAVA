package com.it15306.controller.auth;

import com.it15306.config.DataResponse;
import com.it15306.dto.BodyForgotPasswordDto;
import com.it15306.dto.RegisterDto;
import com.it15306.dto.UserDTO;
import com.it15306.entities.District;
import com.it15306.entities.Province;
import com.it15306.entities.User;
import com.it15306.entities.Ward;
import com.it15306.jwt.JwtTokenProvider;
import com.it15306.libs.HashUtil;
import com.it15306.mapper.UserMapper;
import com.it15306.payload.LoginResponse;
import com.it15306.payload.RequestLogin;
import com.it15306.services.UserService;
import com.it15306.services.UserServiceImpl;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;




@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/miemode_api/v1")
public class Authen {
	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private UserMapper mapper;
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@Autowired
	private UserService userservice;

	@Autowired
	private UserServiceImpl userDetailsService;

	@PostMapping("/login")
    @ResponseBody
	protected ResponseEntity<?> login(@RequestBody RequestLogin request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		final String jwt = tokenProvider.generateToken(userDetails);
//		httpServletRequest.addHeader("Authorization",jwt);
		return new ResponseEntity<>(new LoginResponse(jwt), HttpStatus.OK);
	}

	@RequestMapping(value = "/infor", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public UserDTO getInfor(HttpServletRequest httpServletRequest) {
		System.out.print("khoong laas dc ," + httpServletRequest.getHeader("Authorization"));
		String token = httpServletRequest.getHeader("Authorization").substring(7);
		String username = tokenProvider.getUserNameFromJWT(token);
		ModelMapper modelMapper = new ModelMapper();
		User user = userservice.getByUsername(username);
//		System.out.print(user.getEmail());
		return modelMapper.map(user, UserDTO.class);
	}
	
	
	@RequestMapping(value = "/user/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public DataResponse<RegisterDto> register(@RequestBody RegisterDto dto) {
		DataResponse<RegisterDto> resgister = new DataResponse<RegisterDto>();
		try {
			UserDTO uDto = new UserDTO();
			uDto.setAdmin(2);
			uDto.setEmail(dto.getEmail());
			uDto.setPassword(dto.getPassword());
			uDto.setUsername(dto.getUsername());
			User user = mapper.ConvertToEntity(uDto);
			String hashPass = HashUtil.hash(user.getPassword());
			user.setPassword(hashPass);
			user.setCreate_date(new Date());
			Province pr = new Province();
			pr.setProvince_id(1);
			District dt = new District();
			dt.setDistrict_id(1);
			Ward w = new Ward();
			w.setWard_id(1);
			user.setProvince(pr);
			user.setDistrict(dt);
			user.setWard(w);
			user.setRoles("CUSTOMER");
			this.userService.saveUser(user);
			resgister.setCode(200);
			resgister.setMessage("Success");
		} catch (Exception e) {
			// TODO: handle exception
			resgister.setCode(500);
			resgister.setMessage("APi Fail");
		}	
		return resgister;
	}
	@RequestMapping(value = "/user/forgotPassword", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String forgotPassword(@RequestBody BodyForgotPasswordDto body) {
		User user = userService.getByEmail(body.getEmail());
		if(user!= null) {
			try {
				String hashPass = HashUtil.hash(body.getNewPassword());
				user.setPassword(hashPass);
				this.userService.saveUser(user);
				return "Bạn đã đổi mật khẩu hành công";
			} catch (Exception e) {
				return "Fail";
			}
		}else {
			return "Fail";
		}
		
	}
}

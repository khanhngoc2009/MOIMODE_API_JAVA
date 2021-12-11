package com.it15306.controller.auth;

import com.it15306.config.DataResponse;
import com.it15306.dto.UserDTO;
import com.it15306.dto.auth.BodyForgotPasswordDto;
import com.it15306.dto.auth.CheckCodeDto;
import com.it15306.dto.auth.Email;
import com.it15306.dto.auth.EmailDto;
import com.it15306.dto.auth.RegisterDto;
import com.it15306.dto.order.CheckExistDto;
import com.it15306.dto.user.ChangePassworDto;
import com.it15306.entities.Code_Forgot_Password;
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
import com.it15306.servicesImpl.MailServiceImpl;

import java.util.Date;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
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
	private UserService userService;

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
	
	@Autowired private MailServiceImpl mailServiceImpl;

	@PostMapping("/login")
    @ResponseBody
	protected ResponseEntity<?> login(@RequestBody RequestLogin request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		final String jwt = tokenProvider.generateToken(userDetails);
		return new ResponseEntity<>(new LoginResponse(jwt), HttpStatus.OK);
	}

	@RequestMapping(value = "/infor", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public UserDTO getInfor(HttpServletRequest httpServletRequest) {
		System.out.print("khoong laas dc ," + httpServletRequest.getHeader("Authorization"));
		String token = httpServletRequest.getHeader("Authorization").substring(7);
		String username = tokenProvider.getUserNameFromJWT(token);
		ModelMapper modelMapper = new ModelMapper();
		User user = userservice.getByUsername(username);
		UserDTO userDto = modelMapper.map(user, UserDTO.class);
		return userDto;
	}
	
	@RequestMapping(value = "change-password", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> changePassword(@RequestBody ChangePassworDto dto, HttpServletRequest httpServletRequest) {
//		System.out.print("khoong laas dc ," + httpServletRequest.getHeader("Authorization"));
		DataResponse<String> data = new DataResponse<String>();
		try {
			String token = httpServletRequest.getHeader("Authorization").substring(7);
			String username = tokenProvider.getUserNameFromJWT(token);
			ModelMapper modelMapper = new ModelMapper();
			User user = userservice.getByUsername(username);
//			User user = userservice.getById("32");
			if(user!= null) {
				Boolean s = HashUtil.plain(dto.getOld_pass(), user.getPassword());
				if(s==true) {
					Boolean new_pass = HashUtil.plain(dto.getNew_pass(), user.getPassword());
					if(new_pass!=true) {
						String hashPass = HashUtil.hash(dto.getNew_pass());
						user.setPassword(hashPass);
						userservice.saveUser(user);
						data.setData("Doi mat khau thanh cong");
						data.setCode(HttpStatus.OK.value());
						return new ResponseEntity<>(data,HttpStatus.OK);
					}else {
						data.setData("Mat khau moi khong duoc trung");
						data.setCode(HttpStatus.FOUND.value());
						return new ResponseEntity<>(data,HttpStatus.FOUND);
					}
					
				}else {
					data.setCode(HttpStatus.NOT_FOUND.value());
					data.setData("NOT_FOUND");
					return new ResponseEntity<>(data,HttpStatus.NOT_FOUND);
				}
			}else {
				data.setCode(HttpStatus.NOT_FOUND.value());
				data.setData("NOT_FOUND");
				return new ResponseEntity<>(data,HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// TODO: handle exception
			data.setCode(HttpStatus.FAILED_DEPENDENCY.value());
			data.setData("FAIL");
			return new ResponseEntity<>(data,HttpStatus.FAILED_DEPENDENCY);
		}
	}
	
	@RequestMapping(value = "check-old-password-exist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> checkExistPassword(@RequestBody CheckExistDto dto, HttpServletRequest httpServletRequest) {
		DataResponse<String> data = new DataResponse<String>();
		try {
			String token = httpServletRequest.getHeader("Authorization").substring(7);
			String username = tokenProvider.getUserNameFromJWT(token);
			User user = userservice.getByUsername(username);
			if(user!= null) {
				Boolean s = HashUtil.plain(dto.getOld_pass(), user.getPassword());
				if(s==true) {
					data.setData("true");
					data.setCode(HttpStatus.OK.value());
					data.setMessage("Success");
					return new ResponseEntity<>(data,HttpStatus.OK);
				}else {
					data.setCode(200);
					data.setMessage("Success");
					data.setData("false");
					return new ResponseEntity<>(data,HttpStatus.OK);
				}
			}else {
				data.setCode(HttpStatus.NOT_FOUND.value());
				data.setData("NOT_FOUND");
				return new ResponseEntity<>(data,HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// TODO: handle exception
			data.setCode(HttpStatus.FAILED_DEPENDENCY.value());
			data.setData("FAIL");
			return new ResponseEntity<>(data,HttpStatus.FAILED_DEPENDENCY);
		}
	}
	
	
	@RequestMapping(value = "/user/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> register(@RequestBody RegisterDto dto) {
		DataResponse<RegisterDto> resgister = new DataResponse<RegisterDto>();
		User emailExist = userService.getByEmail(dto.getEmail());
		User userExist = userService.getByUsername(dto.getUsername());
		if(emailExist !=null ) {
			resgister.setCode(205);
			resgister.setMessage("Email đã tồn tại!");
			return new ResponseEntity<>(resgister,HttpStatus.FOUND);
		}else if(userExist!=null) {
			resgister.setCode(205);
			resgister.setMessage("Tên đăng nhập đã tồn tại!");
			return new ResponseEntity<>(resgister,HttpStatus.FOUND);
		}
		else {
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
				pr.setId(0);
				District dt = new District();
				dt.setId(0);
				Ward w = new Ward();
				w.setId(0);
				user.setProvince(pr);
				user.setDistrict(dt);
				user.setWard(w);
				user.setRoles("CUSTOMER");
				user.setActivated(1);
				user.setPhoto("https://hatgiongphuongnam.com/asset/upload/image/hat-giong-hoa-cuc-trang-1.1_1.jpg");
				this.userService.saveUser(user);
				resgister.setCode(200);
				resgister.setMessage("Đăng kí thành công!");
				return new ResponseEntity<>(resgister,HttpStatus.OK);
			} catch (Exception e) {
				// TODO: handle exception
				resgister.setCode(HttpStatus.EXPECTATION_FAILED.value());
				resgister.setMessage("Thất bại");
				return new ResponseEntity<>(resgister,HttpStatus.EXPECTATION_FAILED);
			}	
		}
	}
	@RequestMapping(value = "/user/forgot-password", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> forgotPassword(@RequestBody BodyForgotPasswordDto body) {
		User user = userService.getByEmail(body.getEmail());
		if(user!= null) {
			try {
				String hashPass = HashUtil.hash(body.getNewPassword());
				user.setPassword(hashPass);
				this.userService.saveUser(user);
				return new ResponseEntity<>("Bạn đã đổi mật khẩu hành công",HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>("Thất bại",HttpStatus.FOUND);
			}
		}else {
			return new ResponseEntity<>("Thất bại",HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/send_mail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	   public ResponseEntity<?> toSendEmail(@RequestBody Email email){
			EmailDto eBody= new EmailDto();
			User user = userService.getByEmail(email.getEmail());
			eBody.setEmail(email.getEmail());
			if(user!= null) {
				try {
					int code = mailServiceImpl.SendEmailToCustomer(email.getEmail());
					eBody.setMessage("Đã gửi mã đến email " + email.getEmail());
					Code_Forgot_Password c = new Code_Forgot_Password();
					c.setCode(code);
					c.setEmail(email.getEmail());
					mailServiceImpl.saveCode(c);
					return new ResponseEntity<>(eBody,HttpStatus.OK);
				} catch (MailException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					eBody.setMessage("Thất bại");
					return new ResponseEntity<>(eBody,HttpStatus.FAILED_DEPENDENCY);
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					eBody.setMessage("Thất bại");
					return new ResponseEntity<>(eBody,HttpStatus.FAILED_DEPENDENCY);
				}
			}else {
				eBody.setMessage("Email không tồn tại, vui lòng kiểm tra ");
				return new ResponseEntity<>(eBody,HttpStatus.NOT_FOUND);
			}
	   }
	@RequestMapping(value = "/check-code-email", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	   public ResponseEntity<?> toCheckCode(@RequestBody CheckCodeDto dto){
			DataResponse<String> data = new DataResponse<String>();
			try {
				Code_Forgot_Password code =  mailServiceImpl.findByEmail(dto.getEmail(),dto.getCode());
				if(code!=null) {
					Date date = new Date();
					Date create_Date = code.getCreate_time();
					long longTime=  date.getTime() - create_Date.getTime();
					System.out.print(longTime);
					long thirtyMinutes = 30*60;
					System.out.print(thirtyMinutes);
					if(thirtyMinutes>longTime/1000) {
						data.setCode(200);
						data.setMessage("Chính xác");
						mailServiceImpl.delete(code);
						return new ResponseEntity<>(data,HttpStatus.OK);
					}else {
						data.setCode(HttpStatus.GATEWAY_TIMEOUT.value());
						mailServiceImpl.delete(code);
						data.setMessage("Mã code không còn hiệu lực sau 30 phút, vui lòng thử lại!");
						return new ResponseEntity<>(data,HttpStatus.GATEWAY_TIMEOUT);
					}
				}else {
					data.setMessage("Vui lòng kiểm tra lại!");
					data.setCode(HttpStatus.NOT_FOUND.value());
					return new ResponseEntity<>(data,HttpStatus.NOT_FOUND);
				}
			} catch (Exception e) {
				// TODO: handle exception
				data.setCode(HttpStatus.FAILED_DEPENDENCY.value());
				data.setMessage("Thất bại");
				return new ResponseEntity<>(data,HttpStatus.FAILED_DEPENDENCY);
				
			}
	   }
}

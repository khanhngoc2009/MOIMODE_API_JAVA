package com.it15306.controller.auth;

import com.it15306.config.DataResponse;
import com.it15306.dto.UserDTO;
import com.it15306.dto.auth.BodyForgotPasswordDto;
import com.it15306.dto.auth.CheckCodeDto;
import com.it15306.dto.auth.Email;
import com.it15306.dto.auth.EmailDto;
import com.it15306.dto.auth.RegisterDto;
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

	@RequestMapping(value = "/infor", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public UserDTO getInfor(HttpServletRequest httpServletRequest) {
		System.out.print("khoong laas dc ," + httpServletRequest.getHeader("Authorization"));
		String token = httpServletRequest.getHeader("Authorization").substring(7);
		String username = tokenProvider.getUserNameFromJWT(token);
		ModelMapper modelMapper = new ModelMapper();
		User user = userservice.getByUsername(username);
		return modelMapper.map(user, UserDTO.class);
	}
	
	
	@RequestMapping(value = "/user/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public DataResponse<RegisterDto> register(@RequestBody RegisterDto dto) {
		DataResponse<RegisterDto> resgister = new DataResponse<RegisterDto>();
		User emailExist = userService.getByEmail(dto.getEmail());
		User userExist = userService.getByUsername(dto.getUsername());
		if(emailExist !=null ) {
			resgister.setCode(205);
			resgister.setMessage("Email đã tồn tại!");
		}else if(userExist!=null) {
			resgister.setCode(205);
			resgister.setMessage("Tên đăng nhập đã tồn tại!");
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
				pr.setProvince_id(1);
				District dt = new District();
				dt.setDistrict_id(1);
				Ward w = new Ward();
				w.setWard_id(1);
				user.setProvince(pr);
				user.setDistrict(dt);
				user.setWard(w);
				user.setRoles("CUSTOMER");
				user.setActivated(1);
				user.setPhoto("http://http://34.87.157.20:8089/storages/https://hatgiongphuongnam.com/asset/upload/image/hat-giong-hoa-cuc-trang-1.1_1.jpg");
				this.userService.saveUser(user);
				resgister.setCode(200);
				resgister.setMessage("Đăng kí thành công!");
			} catch (Exception e) {
				// TODO: handle exception
				resgister.setCode(HttpStatus.FOUND.value());
				resgister.setMessage("Thất bại");
			}	
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
				return "Thất bại";
			}
		}else {
			return "Thất bại";
		}
	}
	
	@RequestMapping(value = "/send_mail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	   public EmailDto toSendEmail(@RequestBody Email email){
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
					return eBody;
				} catch (MailException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					eBody.setMessage("Thất bại");
					return eBody;
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					eBody.setMessage("Thất bại");
					return eBody;
				}
			}else {
				eBody.setMessage("Email không tồn tại, vui lòng kiểm tra ");
				return eBody;
			}
	   }
	@RequestMapping(value = "/checkCodeEmail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	   public DataResponse<String> toCheckCode(@RequestBody CheckCodeDto dto){
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
					}else {
						data.setCode(201);
						mailServiceImpl.delete(code);
						data.setMessage("Mã code không còn hiệu lực sau 30 phút, vui lòng thử lại!");
					}
				}else {
					data.setMessage("Vui lòng kiểm tra lại!");
					data.setCode(201);
				}
			} catch (Exception e) {
				// TODO: handle exception
				data.setCode(201);
				data.setMessage("Thất bại");
			}
			
			
			return data;
	   }
}

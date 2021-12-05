package com.it15306.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.it15306.dto.DistrictDTO;
import com.it15306.dto.ProvinceDTO;
import com.it15306.dto.UserDTO;
import com.it15306.dto.WardDTO;
import com.it15306.dto.category.CategoryDTO;
import com.it15306.dto.user.dataBodyUser;
import com.it15306.dto.user.datatupdateUser;
import com.it15306.dto.user.responUser;
import com.it15306.entities.Category;
import com.it15306.entities.District;
import com.it15306.entities.Province;
import com.it15306.entities.User;
import com.it15306.entities.Ward;
import com.it15306.libs.HashUtil;
import com.it15306.repository.DistrictRepository;
import com.it15306.repository.ProvinceRepository;
import com.it15306.repository.UserRepository;
import com.it15306.repository.WardRepository;
import com.it15306.servicesImpl.MailServiceImpl;

@Service("userService")
public class UserServiceImpl implements UserService, IUserService, UserDetailsService{
	 Long totalElement;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	MailServiceImpl mailServiceImpl;
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	ProvinceRepository  provinceRepository;
	
	@Autowired
	DistrictRepository  districtRepository;
	
	@Autowired
	WardRepository wardRepository;
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getById(String id) {
		return userRepository.getOne(Integer.parseInt(id));
	}

	@Override
	public User getByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	
	


    
    @Autowired
    private IUserService iUserService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> _userOp = iUserService.findByUsername(userName);

        System.out.println("ussernmae");
        _userOp.orElseThrow( () -> new UsernameNotFoundException("Not found " + userName));
        return _userOp.map(UserDetailsImpl::new).get();
    }
    @Override
	public User saveUser(User user) {
		userRepository.save(user);
		return user;
	}

	@Override
	public void delete(String id) {
		userRepository.deleteById(Integer.parseInt(id));
	}

	@Override
	public Optional<User> findByUsername(String userName) {
		return userRepository.findByUsername(userName);
	}

	@Override
	public User getByUsername(String username) {
		return userRepository.findByUsername2(username);
	}

	@Override
	public List<UserDTO> getAllUsersv2(dataBodyUser data) {
		try {
			
		
		String start="";
		String end="";
		if(data.getName()== null) data.setName("");
		if(data.getEmail()== null) data.setEmail("");
		if(data.getStatus()== null) data.setStatus("");
		if(data.getPage()== null) {
			data.setPage(0);
		}
		if(data.getTake()== null || data.getTake() == 0) {
			data.setTake(10);
		}
		if(data.getStartTime()== null || data.getStartTime() == "") {
			start =startDate();
		}else {
			start = data.getStartTime();
		}
		if(data.getEndTime()== null || data.getEndTime() == "") {
			end =endDate();
		}else {
			end = data.getEndTime();
		}

		Pageable paging =  PageRequest.of(data.getPage(), data.getTake());

		System.out.println("----------"+start+"     "+end);
		Page<User> pageuser= userRepository.selectFillterUser(data.getName(), data.getEmail(), start, end, data.getStatus(), paging);
		List<User> list= pageuser.getContent();
		List<UserDTO> listDto=new ArrayList<UserDTO>();
		totalElement=pageuser.getTotalElements();
		System.out.println("so ban ghi: "+totalElement);
		if(list.size() > 0) {
			
		
			list.forEach(u -> 
				{
					UserDTO dto=new UserDTO();
					dto  =	modelMapper.map(u, UserDTO.class);
					dto.setDistrictdto(modelMapper.map(u.getDistrict(), DistrictDTO.class));
					dto.setProvincedto(modelMapper.map(u.getProvince(), ProvinceDTO.class));
					dto.setWarddto(modelMapper.map(u.getWard(), WardDTO.class));
					listDto.add(dto);
				}
			);
			return listDto;
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("----------2");
		
		return null;
	}

	public String startDate() {
		return userRepository.START_DATE();
	}
	public String endDate() {
		return userRepository.END_DATE();
	}
	@Override
	public Long totalement() {
		return this.totalElement;
	}

	@Override
	public UserDTO createUser(responUser data) {
		
		try {
			List<User> us=userRepository.SELECT_CHECK_USER(data.getUsername(),data.getEmail(), data.getPhone());
			if(us.size() <= 0) {
			User entity = mapToUser(data);
			UserDTO dto=new UserDTO();
			userRepository.save(entity);
			mailServiceImpl.SendEmailCreateAcc(entity.getEmail(), entity.getUsername());
			dto = modelMapper.map(entity, UserDTO.class);
			dto.setId(entity.getId());
			dto.setProvincedto(modelMapper.map(provinceRepository.findById(data.getProvince_id()).get(), ProvinceDTO.class));
			dto.setDistrictdto(modelMapper.map(districtRepository.findById(data.getDistrict_id()).get(), DistrictDTO.class));
			dto.setWarddto(modelMapper.map(wardRepository.findById(data.getWard_id()).get(), WardDTO.class));
			 return dto;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	public User mapToUser(responUser data) {
		User user=new User();
		District district=new District();
		district.setId(data.getDistrict_id());
		Province province=new Province();
		province.setId(data.getProvince_id());
		Ward ward=new Ward();
		ward.setId(data.getWard_id());
		//set password mặc định
		String hashPass = HashUtil.hash("Miemode@1234");
		user.setPassword(hashPass);
		
		user.setId(data.getId());
		user.setEmail(data.getEmail());
		//set mặc định hoạt động
		user.setActivated(1);
		user.setAdmin(data.getAdmin());
		user.setDistrict(district);
		user.setWard(ward);
		user.setRoles(data.getRoles());
		user.setUsername(data.getUsername().trim());
		user.setProvince(province);
		user.setPhoto(data.getPhoto());
		user.setPhone(data.getPhone());
		user.setCreate_date(new Date());
		return user;
		
	}
	@org.springframework.transaction.annotation.Transactional
	@Override
	public UserDTO updateUser(datatupdateUser data) {
		Optional<User> option= userRepository.findById(data.getId());
		System.out.println("-----------1");
		User entity = mapUserUpdate(data);
		System.out.println("-----------2"+entity.getDistrict().getId());
		UserDTO dto=new UserDTO();
		if(option.isPresent()) {
			System.out.println("-----------3");
			User user=option.get();
			User us =mapToEnyities(entity, user);
			System.out.println("++++++++++:"+us.toString());
			us.setPassword(user.getPassword()== null? "": user.getPassword());
			userRepository.save(us);
			dto = modelMapper.map(us, UserDTO.class);
			dto.setProvincedto(modelMapper.map(provinceRepository.findById(data.getProvince_id()).get(), ProvinceDTO.class));
			dto.setDistrictdto(modelMapper.map(districtRepository.findById(data.getDistrict_id()).get(), DistrictDTO.class));
			dto.setWarddto(modelMapper.map(wardRepository.findById(data.getWard_id()).get(), WardDTO.class));
			return dto;
		}
		
		return null;
	}
	public User mapUserUpdate(datatupdateUser data) {
		System.out.println(data.toString());
		User user=new User();
		Province province=new Province();
		province.setId(data.getProvince_id());
		District district=new District();
		district.setId(data.getDistrict_id());
		district.setProvince(province);
		Ward ward=new Ward();
		ward.setId(data.getWard_id());
		ward.setDistrict(district);
		//String hashPass = HashUtil.hash(data.getPassword());
		//user.setPassword(hashPass);
		
		user.setId(data.getId());
		user.setEmail(data.getEmail());
		user.setActivated(data.getActivated());
		user.setAdmin(data.getAdmin());
		user.setProvince(province);
		user.setDistrict(district);
		user.setWard(ward);
		user.setRoles(data.getRoles());
		user.setUsername(data.getUsername().trim());
	
		user.setPhoto(data.getPhoto());
		user.setPhone(data.getPhone());
		//user.setCreate_date(new Date());
		return user;
		
	}
	public User mapToEnyities(User model, User enti){
		enti = modelMapper.map(model, User.class);
		return enti;
	}
}

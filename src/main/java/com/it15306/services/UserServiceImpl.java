package com.it15306.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.it15306.dto.CustomUserDetails;
import com.it15306.entities.User;
import com.it15306.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService, IUserService, UserDetailsService{
	@Autowired
	private UserRepository userRepository;

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

	

//    // JWTAuthenticationFilter sẽ sử dụng hàm này
//    @Transactional
//    public UserDetails loadUserById(Integer id) {
//        User user = userRepository.findById(String.valueOf(id));
//        if(user != null ) {
////        	return new CustomUserDetails(user);
//        }
//		return null;
//    }
    
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

	
}

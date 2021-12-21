package com.aelion.mycrm.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aelion.mycrm.helpers.Request;
import com.aelion.mycrm.models.User;
import com.aelion.mycrm.models.UserRole;
import com.aelion.mycrm.repositories.UserRepository;

@Service
public class UserAuthService implements UserDetailsService {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findByUserName(username).get();
		
		List<UserRole> roles = user.getUserRoles()
				.stream()
				.collect(Collectors.toList());
		
		List<GrantedAuthority> grantedAuthorities = roles
				.stream()
				.map(r -> {
					return new SimpleGrantedAuthority(r.getRole());
				})
				.collect(Collectors.toList());
		
		return new org.springframework.security.core.userdetails.User(username, user.getUserPass(), grantedAuthorities);
	}
	
	public void saveUser(Request request) {
		Optional<User> oUser = this.userRepository.findByUserName(request.getUserName());
		
		if (oUser.isPresent()) {
			throw new RuntimeException("User already exists");
		}
		
		User user = new User();
		user.setUserName(request.getUserName());
		user.setUserPass(this.passwordEncoder.encode(request.getUserPass()));
		
		user.setUserRoles(request.getRoles().stream().map(r -> {
			UserRole userRole = new UserRole();
			userRole.setRole(r);
			return userRole;
			
			}).collect(Collectors.toSet()));
		
		this.userRepository.save(user);
	}

}

package com.aelion.mycrm.controllers;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aelion.mycrm.components.JwtUtil;
import com.aelion.mycrm.exceptions.DisabledUserException;
import com.aelion.mycrm.exceptions.InvalidUserCredentialsException;
import com.aelion.mycrm.helpers.Request;
import com.aelion.mycrm.helpers.Response;
import com.aelion.mycrm.helpers.SignupMessage;
import com.aelion.mycrm.services.UserAuthService;

@RestController
@CrossOrigin(value="http://localhost:4200")
@RequestMapping("/user")
public class UserController {
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserAuthService userAuthService;
	
	@Autowired AuthenticationManager authenticationManager;
	
	@GetMapping("/secret")
	public String generateSafeSecret() {
		SecureRandom random = new SecureRandom();
		byte[] bytes = new byte[36];
		
		random.nextBytes(bytes);
		
		var encoder = Base64.getUrlEncoder().withoutPadding();
		
		return encoder.encodeToString(bytes);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<Response> generateJwtToken(@RequestBody Request request) {
		Authentication authentication = null;
		
		try {
			authentication = this.authenticationManager
				.authenticate(
						new UsernamePasswordAuthenticationToken(request.getUserName(), request.getUserPass())
				);
			
		} catch (DisabledException e) {
			throw new DisabledUserException("User was disabled");
		} catch (BadCredentialsException e) {
			throw new InvalidUserCredentialsException("Invalid credentials");
		}
		
		User user = (User) authentication.getPrincipal();
		
		Set<String> roles = user.getAuthorities().stream().map(r -> r.getAuthority()).collect(Collectors.toSet());
		
		String token = jwtUtil.generateToken(authentication);
		
		Response response = new Response();
		response.setToken(token);
		response.setRoles(roles.stream().collect(Collectors.toList()));
		
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<SignupMessage> signup(@RequestBody Request request) {
		this.userAuthService.saveUser(request);
		SignupMessage message = new SignupMessage("User was successfully registred");
		return new ResponseEntity<SignupMessage>(message, HttpStatus.OK);
	}
	
}

package com.example.demo.serviceimpl;

import java.util.HashMap;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.JwtAuthenticationResponse;
import com.example.demo.dto.RefershTokenRequest;
import com.example.demo.dto.SignInRequest;
import com.example.demo.dto.SignUpRequest;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationSeviceImpl implements AuthenticationService{

	
	private final UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final AuthenticationManager authenticationManager;
	
	private final JwtService jwtService;
	
	public User signUp(SignUpRequest signUpRequest)
	{
		User user=new User();
		user.setFirstName(signUpRequest.getFirstName());
		user.setLastName(signUpRequest.getLastName());
		user.setEmail(signUpRequest.getEmail());
		user.setRole(Role.USER);
		user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
		
		return userRepository.save(user);
	}
	
	public JwtAuthenticationResponse signIn(SignInRequest signInRequest)
	{
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword()));
		
		var user=userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(()-> new IllegalArgumentException("Invalid email or Password"));
		var jwt=jwtService.generateToken(user);
		var refershToken = jwtService.generateRefershToken(new HashMap<>(),user);
		
		JwtAuthenticationResponse authenticationResponse=new JwtAuthenticationResponse();
		
		authenticationResponse.setToken(jwt);
		authenticationResponse.setRefershToken(refershToken);
		return authenticationResponse;
	}
	
	public JwtAuthenticationResponse refershToken(RefershTokenRequest refershTokenRequest)
	{
		String userEmail= jwtService.extractUserName(refershTokenRequest.getToken());
		User user=userRepository.findByEmail(userEmail).orElseGet(null);
		if(jwtService.isTokenValid(refershTokenRequest.getToken(), user))
		{
			var jwt=jwtService.generateToken(user);
			
			JwtAuthenticationResponse authenticationResponse=new JwtAuthenticationResponse();
			
			authenticationResponse.setToken(jwt);
			authenticationResponse.setRefershToken(refershTokenRequest.getToken());
			return authenticationResponse;
		}
		return null;
	}
}

package com.example.demo.service;

import com.example.demo.dto.JwtAuthenticationResponse;
import com.example.demo.dto.RefershTokenRequest;
import com.example.demo.dto.SignInRequest;
import com.example.demo.dto.SignUpRequest;
import com.example.demo.entity.User;

public interface AuthenticationService {

	public User signUp(SignUpRequest signUpRequest);
	
	public JwtAuthenticationResponse signIn(SignInRequest signInRequest);
	
	public JwtAuthenticationResponse refershToken(RefershTokenRequest refershTokenRequest);
}

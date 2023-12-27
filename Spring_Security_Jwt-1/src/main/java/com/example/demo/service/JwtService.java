package com.example.demo.service;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

	public String generateToken(UserDetails userDetails);
	
	public String extractUserName(String token);
	
	public boolean isTokenValid(String token, UserDetails userDetails);
	
	public String generateRefershToken(Map<String, Object> extraClaims,UserDetails userDetails);
}

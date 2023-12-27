package com.example.demo.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.RefershTokenRequest;
import com.example.demo.dto.SignInRequest;
import com.example.demo.dto.SignUpRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.response.ResponseBulider;
import com.example.demo.response.ResponseHandler;
import com.example.demo.response.UserAlreadyExist;
import com.example.demo.service.AuthenticationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@EnableMethodSecurity
public class AuthenticationController {

	private final AuthenticationService authenticationService;
	
	private final UserRepository userRepository;
	
	@PostMapping("/register")
	public ResponseEntity<Object> signUp(@Valid @RequestBody SignUpRequest signUpRequest)
	{
		Optional<User> optional=userRepository.findByEmail(signUpRequest.getEmail());
		if(optional.isPresent())
		{
			return UserAlreadyExist.response("Failed", HttpStatus.FOUND, "User Already Exist Login With Your Email");
		}
		return ResponseBulider.reponseHandler("sucess", HttpStatus.OK, authenticationService.signUp(signUpRequest));
	}
	
	@PostMapping("/signin")
	public ResponseEntity<Object> signIn(@Valid @RequestBody SignInRequest signInRequest)
	{
		return ResponseHandler.responseBuilder("Success", HttpStatus.OK, authenticationService.signIn(signInRequest));
	}
	
	@PostMapping("/refershToken")
	public ResponseEntity<Object> refershToken(@RequestBody RefershTokenRequest refershTokenRequest)
	{
		return ResponseHandler.responseBuilder("Success", HttpStatus.OK, authenticationService.refershToken(refershTokenRequest));
	}
}

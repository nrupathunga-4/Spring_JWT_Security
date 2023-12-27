package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignInRequest {

	@Email(message = "Please Provide A Proper Email")
	private String email;
	
	@NotBlank(message = "Password is Mandatory")
	private String password;
}

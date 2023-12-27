package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SignUpRequest {

	@NotBlank(message = "FirstName Shouldn't Be Null")
	@Pattern(regexp = "^[a-zA-Z]+$",message ="FirstName Should Consist Letters")
	private String firstName;
	
	@NotBlank(message = "LastName Shouldn't Be Null")
	@Pattern(regexp = "^[a-zA-Z]+$",message ="LastName Should Consist Letters")
	private String lastName;
	
	@Email(message = "Please Provide a Proper Email")
	private String email;
	
	@NotBlank(message = "Password Shouldn't Be Null")
	private String password;
}

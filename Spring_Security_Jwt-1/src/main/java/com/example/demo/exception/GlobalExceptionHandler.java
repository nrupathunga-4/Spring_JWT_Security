package com.example.demo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleBussiness(MethodArgumentNotValidException ex)
	{
		Map<String, String> errormap=new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error->{
			errormap.put(error.getField(),error.getDefaultMessage());
		});
		errormap.put("status", "Failed");
		errormap.put("statusCode", "400");
		return errormap;
	}
}

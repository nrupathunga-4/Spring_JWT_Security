package com.example.demo.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.dto.JwtAuthenticationResponse;

public class ResponseHandler {

	public static ResponseEntity<Object> responseBuilder(String status,HttpStatus statusCode,JwtAuthenticationResponse objectResponse)
	{
		Map<String, Object> map=new HashMap<>();
		map.put("status", status);
		map.put("statusCode", statusCode.value());
		map.put("data", objectResponse);
		return new ResponseEntity<>(map,statusCode);
	}
}

package com.example.demo.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UserAlreadyExist {

	public static ResponseEntity<Object> response(String status,HttpStatus statusCode,String message)
	{
		Map<String, Object> map=new HashMap<>();
		map.put("status", status);
		map.put("statusCode", statusCode.value());
		map.put("error", message);
		return new ResponseEntity<>(map,statusCode);
	}
}

package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@SpringBootApplication
public class SpringSecurityJwt1Application implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwt1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User adminAccount=userRepository.findByRole(Role.ADMIN);
		if(null == adminAccount)
		{
			User user=new User();
			user.setFirstName("Ramesh");
			user.setLastName("Kumar");
			user.setEmail("Ramesh@gmail.com");
			user.setRole(Role.ADMIN);
			user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			
			userRepository.save(user);
		}
		
	}

}

package com.example.expensetracker.resources;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.expensetracker.exceptions.EtAuthException;
import com.example.expensetracker.model.User;
import com.example.expensetracker.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import src.main.java.Constants;

@RestController
@RequestMapping("/api/users")
public class UserResource {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public Map<String, String> loginUser(@RequestBody User user) {
		User response = userService.validateUser(user.getEmail(), user.getPassword());
		if (!BCrypt.checkpw(user.getPassword(), response.getPassword()))
			throw new EtAuthException("Incorrect Password");
		return generateJWTToken(response);
	}

	@PostMapping("/register")
	public Map<String, String> registerUser(@RequestBody User user) {
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashed);
		User result = userService.registerUser(user);
		return generateJWTToken(result);
	}
	
	private Map<String, String> generateJWTToken(User user) {
		long timestamp = System.currentTimeMillis();
		System.out.println(user.toString());
		String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, Constants.API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + Constants.TOKEN_VALIDITY))
                .claim("userId", user.getUserId())
                .claim("email", user.getEmail())
                .claim("firstName", user.getFirstName())
                .claim("lastName", user.getLastName())
                .compact();
		Map<String, String> map = new HashMap<>();
		map.put("token", token);
		return map;
	}

}

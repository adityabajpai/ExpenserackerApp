package com.example.expensetracker.service;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.expensetracker.exceptions.EtAuthException;
import com.example.expensetracker.model.User;
import com.example.expensetracker.repositories.UserRepository;

@Service
@Transactional
public class UserServiceImplementation implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	

	@Override
	public User validateUser(String email, String password) throws EtAuthException {
		System.out.println("login user");
		Pattern pattern = Pattern.compile("^(.+)@(.+)$");
		if (email != null) email = email.toLowerCase();
		if (!pattern.matcher(email).matches())
			throw new EtAuthException("Invalid Email format");
		int count = userRepository.getCountByEmail(email);
		System.out.println("count "+count);
		if (count == 0)
			throw new EtAuthException("User not registered");
		return userRepository.findByEmailAndPassword(email, password);
	}

	@Override
	public User registerUser(User user) throws EtAuthException {
		System.out.println("register User");
		Pattern pattern = Pattern.compile("^(.+)@(.+)$");
		if (user.getEmail() != null) user.setEmail(user.getEmail().toLowerCase());
		if (!pattern.matcher(user.getEmail()).matches())
			throw new EtAuthException("Invalid Email format");
		int count = userRepository.getCountByEmail(user.getEmail());
		if (count > 0)
			throw new EtAuthException("Email already in use");
		userRepository.create(user);
		User result = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
		return result;
	}

}

package com.example.expensetracker.service;

import com.example.expensetracker.exceptions.EtAuthException;
import com.example.expensetracker.model.User;

public interface UserService {
	
	User validateUser(String email, String password) throws EtAuthException;
	
	User registerUser(User user) throws EtAuthException;
	
}
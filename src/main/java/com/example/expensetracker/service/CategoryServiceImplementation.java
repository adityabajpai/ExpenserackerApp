package com.example.expensetracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.expensetracker.exceptions.EtBadRequestException;
import com.example.expensetracker.exceptions.EtResourceNotFoundException;
import com.example.expensetracker.model.Category;
import com.example.expensetracker.repositories.CategoryRepository;

@Service
@Transactional
public class CategoryServiceImplementation implements CategoryService{
	
	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public List<Category> fetchAllCategories(int userId) {
		// TODO Auto-generated method stub
		return categoryRepository.findAll(userId);
	}

	@Override
	public Category fetchCategoryById(int userId, int categoryId) throws EtResourceNotFoundException {
		// TODO Auto-generated method stub
		return categoryRepository.findById(userId, categoryId);
	}

	@Override
	public Category addCategory(int userId, String title, String description) throws EtBadRequestException {
		// TODO Auto-generated method stub
		System.out.println("add Category");
		categoryRepository.create(userId, title, description);
		return new Category(0, userId, title, description, 0);
	}

	@Override
	public void updateCategory(int userId, int categoryId, String title, String description) throws EtBadRequestException {
		// TODO Auto-generated method stub
		categoryRepository.update(userId, categoryId, title, description);
	}

	@Override
	public void removeCategoryWithAllTransactions(int userId, int categoryId) throws EtResourceNotFoundException {
		// TODO Auto-generated method stub
		
	}

}

package com.example.expensetracker.service;

import java.util.List;

import com.example.expensetracker.exceptions.EtBadRequestException;
import com.example.expensetracker.exceptions.EtResourceNotFoundException;
import com.example.expensetracker.model.Category;

public interface CategoryService {
	
	List<Category> fetchAllCategories(int userId);
	
	Category fetchCategoryById(int userId, int categoryId) throws EtResourceNotFoundException;
	
	Category addCategory(int userId, String title, String description) throws EtBadRequestException;
	
	void updateCategory(int userId, int categoryId, String title, String description) throws EtBadRequestException;
	
	void removeCategoryWithAllTransactions(int userId, int categoryId) throws EtResourceNotFoundException;

}

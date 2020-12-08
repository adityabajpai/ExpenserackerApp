package com.example.expensetracker.resources;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.expensetracker.model.Category;
import com.example.expensetracker.service.CategoryService;

@RestController
@RequestMapping("/api/categories/")
public class CategoryResource {
	
	@Autowired
	CategoryService categoryService;

	@GetMapping("")
	public List<Category> getAllCategories(HttpServletRequest request) {
		int userId = (Integer) request.getAttribute("userId");
		List<Category> categories = categoryService.fetchAllCategories(userId);
		return categories;
	}
	
	@GetMapping("/{categoryId}")
	public Category getCategoryById(HttpServletRequest request, @PathVariable("categoryId") Integer categoryId) {
		int userId = (Integer) request.getAttribute("userId");
		Category category = categoryService.fetchCategoryById(userId, categoryId);
		return category;
	}
	
	@PostMapping("")
	public Category addCategory(HttpServletRequest request, @RequestBody Category category) {
		int userId = (Integer) request.getAttribute("userId");
		System.out.println("userId " + userId);
		Category category2 = categoryService.addCategory(userId, category.getTitle(), category.getDescription());
		return category2;
	}
	
	@PutMapping("/{categoryId}")
	public Category updateCategory(HttpServletRequest request, @PathVariable ("categoryId") Integer categoryId, @RequestBody Category category) {
		int userId = (Integer) request.getAttribute("userId");
		System.out.println(userId + " " + categoryId);
		categoryService.updateCategory(userId, categoryId, category.getTitle(), category.getDescription());
		return category;
	}
	
}

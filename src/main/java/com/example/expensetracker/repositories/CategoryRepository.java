package com.example.expensetracker.repositories;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.expensetracker.exceptions.EtBadRequestException;
import com.example.expensetracker.exceptions.EtResourceNotFoundException;
import com.example.expensetracker.model.Category;

@Mapper
public interface CategoryRepository {
	
	static final String SQL_CREATE = "insert into et_categories (user_id, title, description) values (#{userId}, #{title}, #{description})";
	static final String SQL_FIND_BY_ID = "select c.category_id, c.user_id, c.title, c.description, coalesce(SUM(t.amount), 0) total_expense from et_transactions t right outer join et_categories c on c.category_id = t.category_id where c.user_id = #{userId} and c.category_id = #{categoryId} group by c.category_id";
	static final String SQL_FIND_ALL = "select c.category_id, c.user_id, c.title, c.description, coalesce(SUM(t.amount), 0) total_expense from et_transactions t right outer join et_categories c on c.category_id = t.category_id where c.user_id = #{userId} group by c.category_id";
	static final String SQL_UPDATE = "update et_categories set title = #{title}, description = #{description} where user_id = #{userId} and category_id = #{categoryId}";
	
	@Select(SQL_FIND_ALL)
	List<Category> findAll(int userId) throws EtResourceNotFoundException;
	
	@Select(SQL_FIND_BY_ID)
	Category findById(int userId, int categoryId) throws EtResourceNotFoundException;
	
	@Insert(SQL_CREATE)
	int create(int userId, String title, String description) throws EtBadRequestException;
	
	@Update(SQL_UPDATE)
	void update(int userId, int categoryId, String title, String description) throws EtBadRequestException;
	
	void removeById(int userId, int categoryId);

}

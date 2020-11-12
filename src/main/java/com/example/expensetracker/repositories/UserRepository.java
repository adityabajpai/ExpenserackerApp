package com.example.expensetracker.repositories;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.expensetracker.exceptions.EtAuthException;
import com.example.expensetracker.model.User;

@Mapper
public interface UserRepository {
	
	static final String SQL_CREATE = "insert into et_users (first_name, last_name, email, password) values (#{firstName}, #{lastName}, #{email}, #{password})";
	static final String SQL_COUNT_BY_EMAIL = "Select COUNT(*) from et_users where email = #{email}";
	static final String SQL_FIND_BY_ID = "Select * from et_users where user_id = #{userId}";
	static final String SQL_FIND_BY_EMAIL = "select * from et_users where email = #{email}";

	@Insert(SQL_CREATE)
    void create(User user) throws EtAuthException;

	@Select(SQL_FIND_BY_EMAIL)
    User findByEmailAndPassword(String email, String password) throws EtAuthException;

    @Select(SQL_COUNT_BY_EMAIL)
    int getCountByEmail(String email);

    @Select(SQL_FIND_BY_ID)
    User findById(Integer userId);

}

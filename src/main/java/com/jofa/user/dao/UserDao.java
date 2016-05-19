package com.jofa.user.dao;

import com.jofa.user.model.User;

public interface UserDao {
	
	void save(User stock);
	void update(User stock);
	void delete(User stock);
	User findById(Integer id);
	User findByUsername(String username);
	User findByEmail(String email);

}

package com.jofa.user.bo;

import com.jofa.user.model.User;

public interface UserBo {
	
	void save(User user);
	void update(User user);
	void delete(User user);
	User findById(Integer id);
	User findByUsername(String username);
	User findByEmail(String email);
}
package com.jofa.dao;

import java.io.Serializable;
import java.util.List;

public interface LoginAttemptDao <T, Id extends Serializable> {

	public void persist(T entity);
	
	public void save(T entity);
	
	public void update(T entity);
	
	public T findById(Integer id);
	
	public void delete(T entity);
	
	public List<T> findAll();
	
	public void deleteAll();

	public void saveOrUpdate(T entity);
	
	public T findByUsername(String username);
	
	public T findByUsernameAndPassword(T entity);
	
}

package com.jofa.service;

import java.util.List;

public interface GenericService <T, E> {

	public void persist(T entity);
	public void save(T entity);
	public void update(T entity);
	public T findById(Integer id); 
	public void delete(int id);
	public List<T> findAll();
	public void deleteAll();
	public E genericDaoImpl();
	public void SaveOrUpdate(T entity);
	
}

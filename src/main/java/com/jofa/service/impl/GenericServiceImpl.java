package com.jofa.service.impl;

import java.util.List;

import com.jofa.service.GenericService;

public abstract class GenericServiceImpl <T, E> implements GenericService<T, E>{
	

	@Override
	public void persist(T entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(T entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(T entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public E genericDaoImpl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void SaveOrUpdate(T entity) {
		// TODO Auto-generated method stub
		
	}

	
}

package com.jofa.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.jofa.dao.GenericDao;

public abstract class GenericDaoImpl<T, Id extends Serializable> implements GenericDao<T, Serializable> {

	private static SessionFactory sessionFactory;

	protected Session currentSession;
	protected Transaction currentTransaction;

	public GenericDaoImpl() {
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
		this.sessionFactory = new Configuration().buildSessionFactory(serviceRegistry);
	}
	
	@SuppressWarnings({ "unused", "unchecked" })
	private Class<T> getClassType() {
		return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public Session openCurrentSession() {
		currentSession = sessionFactory.openSession();
		return currentSession;
	}

	public Session openCurrentSessionwithTransaction() {
		currentSession = sessionFactory.openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}

	public void closeCurrentSession() {
		currentSession.close();
	}

	public void closeCurrentSessionwithTransaction() {
		currentTransaction.commit();
		currentSession.close();
	}

	public Session getCurrentSession() {
		return currentSession;
	}

	public void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}

	public Transaction getCurrentTransaction() {
		return currentTransaction;
	}

	public void setCurrentTransaction(Transaction currentTransaction) {
		this.currentTransaction = currentTransaction;
	}

	@Override
	public void persist(T entity) {
		currentSession.persist(entity);
	}

	@Override
	public void saveOrUpdate(T entity) {
		currentSession.saveOrUpdate(entity);
	}

	@Override
	public void update(T entity) {
		currentSession.update(entity);

	}

	@Override
	public T findById(Integer id) {
		return (T) currentSession.get(getClassType(), id);
	}

	@Override
	public void delete(T entity) {
		currentSession.delete(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		return currentSession.createCriteria(getClassType()).list();
	}

	@Override
	public void deleteAll() {
		// TODO IMPLEMENT

	}

	@Override
	public void save(T entity) {
		currentSession.save(entity);
	}

}

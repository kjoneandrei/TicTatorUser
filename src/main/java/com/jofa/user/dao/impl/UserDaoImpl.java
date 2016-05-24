package com.jofa.user.dao.impl;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Property;
import org.hibernate.service.ServiceRegistry;
import org.springframework.stereotype.Repository;

import com.jofa.user.dao.UserDao;
import com.jofa.user.exception.UserNotFoundException;
import com.jofa.user.exception.UserNotSavedException;
import com.jofa.user.model.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao<User, String> {

	private Session currentSession;
    private Transaction currentTransaction;
    
    public UserDaoImpl() {
    }

    public Session openCurrentSession() {
		currentSession = getSessionFactory().openSession();
		return currentSession;
	}

	public Session openCurrentSessionwithTransaction() {
		currentSession = getSessionFactory().openSession();
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
	
	private static SessionFactory getSessionFactory() {
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
		    configure().build();
		SessionFactory sessionFactory = new Configuration().buildSessionFactory(serviceRegistry);
		return sessionFactory;
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
	public void persist(User entity) throws UserNotSavedException {
		currentSession.persist(entity);
	}
	
	@Override
	public void saveOrUpdate(User entity){
		currentSession.saveOrUpdate(entity);
	}


	@Override
	public void update(User entity) {
		currentSession.update(entity);
		
	}

	@Override
	public User findById(Integer id) {
		return (User)currentSession.get(User.class, id);
	}

	@Override
	public void delete(User entity) {
		currentSession.delete(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {
		return currentSession.createCriteria(User.class).list();
	}

	@Override
	public void deleteAll() {
		// TODO IMPLEMENT
		
	}

	@Override
	public void save(User entity) {
		currentSession.save(entity);		
	}

	@Override
	public User findByUsername(String username) {
		@SuppressWarnings("unchecked")
		List<User> users = currentSession.createCriteria(User.class)
			    .add(Property.forName("username").eq(username))
			    .list();
		return users.isEmpty() ? null : users.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public User findByUsernameAndPassword(User entity) {
		List<User> users = currentSession.createCriteria(User.class)
			    .add(Property.forName("username").eq(entity.getUsername()))
			    .add(Property.forName("password").eq(entity.getPassword()))
			    .list();
		if(users == null || users.isEmpty()) {
			throw new UserNotFoundException();
		}
		return users.get(0);
	}


}

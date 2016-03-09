package com.dentist.dao;



import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class DbDao {

	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void persist(Object entity) {
		getSession().persist(entity);
	}

	public void delete(Object entity) {
		getSession().delete(entity);
	}
	
	public void update(Object entity) {
		getSession().update(entity);
	}
	
	public void save(Object entity) {
		getSession().save(entity);
	}
	
	public void merge(Object entity) {
		getSession().merge(entity);
	}

}

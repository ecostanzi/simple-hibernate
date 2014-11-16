package com.lesula.db.dao;

import com.lesula.db.conf.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;

import java.io.Serializable;
import java.util.List;

public abstract class Dao<E, ID extends Serializable>
implements DaoInterface<E, ID> {

	private final Class<E> entityClass;
	protected SessionFactory sessionFactory;

	public Dao(Class<E> entityClass) {

		this.entityClass = entityClass;
		this.sessionFactory = HibernateUtils.getInstance().getSessionFactory();
	}

	protected Session getSession(){
		return sessionFactory.getCurrentSession();
	}


	public Class<E> getEntityClass() {
		return entityClass;
	}

	public E findById(ID id) {
		return (E) sessionFactory.getCurrentSession().get(getEntityClass(), id);

	}

	public List<E> findAll() {
		return findByCriteria();
	}

	public List<E> findByExample(E exampleInstance, String... excludeProperty) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(getEntityClass());
		Example example =  Example.create(exampleInstance);
		for (String exclude : excludeProperty) {
			example.excludeProperty(exclude);
		}
		crit.add(example);
		return crit.list();
	}

	public void save(E entity) {
		sessionFactory.getCurrentSession().save(entity);
	}

	public void update(E entity) {
		sessionFactory.getCurrentSession().update(entity);
	}

	public void saveOrUpdate(E entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	public void saveOrUpdateAndFlush(E entity) {
		sessionFactory.getCurrentSession().saveOrUpdate(entity);
		sessionFactory.getCurrentSession().flush();
	}

	public void delete(E entity) {
		sessionFactory.getCurrentSession().delete(entity);
	}

	public void flush() {
		sessionFactory.getCurrentSession().flush();
	}

	public void clear() {
		sessionFactory.getCurrentSession().clear();
	}

	protected List<E> findByCriteria(Criterion... criterion) {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(getEntityClass());
		for (Criterion c : criterion) {
			crit.add(c);
		}
		return crit.list();
	}
}
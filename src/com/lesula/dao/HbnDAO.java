package com.lesula.dao;

import com.lesula.conf.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;

import java.io.Serializable;
import java.util.List;

public abstract class HbnDAO<E, ID extends Serializable>
implements IHbnDAO<E, ID> {

	private final Class<E> entityClass;
	protected Session session;

	public HbnDAO(Class<E> entityClass) {

		this.entityClass = entityClass;
		this.session = HibernateUtils.getSession();
	}


	public Class<E> getEntityClass() {
		return entityClass;
	}

	public E findById(ID id) {
		return (E) session.get(getEntityClass(), id);

	}

	public List<E> findAll() {
		return findByCriteria();
	}

	public List<E> findByExample(E exampleInstance, String... excludeProperty) {
		Criteria crit = session.createCriteria(getEntityClass());
		Example example =  Example.create(exampleInstance);
		for (String exclude : excludeProperty) {
			example.excludeProperty(exclude);
		}
		crit.add(example);
		return crit.list();
	}

	public void save(E entity) {
		session.save(entity);
	}

	public void update(E entity) {
		session.update(entity);
	}

	public void saveOrUpdate(E entity) {
		session.saveOrUpdate(entity);
	}

	public void saveOrUpdateAndFlush(E entity) {
		session.saveOrUpdate(entity);
		session.flush();
	}

	public void delete(E entity) {
		session.delete(entity);
	}

	public void flush() {
		session.flush();
	}

	public void clear() {
		session.clear();
	}

	protected List<E> findByCriteria(Criterion... criterion) {
		Criteria crit = session.createCriteria(getEntityClass());
		for (Criterion c : criterion) {
			crit.add(c);
		}
		return crit.list();
	}
}
package com.lesula.dao;

import java.io.Serializable;
import java.util.List;

public interface IHbnDAO<E, ID extends Serializable> {

	E findById(ID id);

	List<E> findAll();

	List<E> findByExample(E exampleInstance, String... excludeProperty);

	void save(E entity);

	void saveOrUpdate(E entity);

	void update(E entity);

	void delete(E entity);

	/**
	 * Affects every managed instance in the current persistence context!
	 */
	void flush();

	/**
	 * Affects every managed instance in the current persistence context!
	 */
	void clear();

	//	    E initializeAndUnproxy(E entity);

}


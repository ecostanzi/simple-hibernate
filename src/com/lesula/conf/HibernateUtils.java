package com.lesula.conf;

/**
 * RepositorySessionFactory.java
 * 
 * This is the singleton class to get the proper {@link SessionFactory}
 */

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

	private static HibernateUtils hibernateUtils;

	private SessionFactory sessionFactory;

	private HibernateUtils() {}

	/**
	 * Return the singleton instance of {@link com.lesula.conf.HibernateUtils}
	 * @return - Object of {@link com.lesula.conf.HibernateUtils}
	 */
	public static HibernateUtils getInstance() {
		if (hibernateUtils == null) {
			hibernateUtils = new HibernateUtils();
		}

		return hibernateUtils;
	}

	/**
	 * Return {@link SessionFactory} object corresponding to BeenZ API
	 * 
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		if(sessionFactory == null) {
			Configuration configuration = new Configuration().configure();
			StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
			applySettings(configuration.getProperties());
			sessionFactory = configuration.buildSessionFactory(builder.build());
		}
		return sessionFactory;
	}
	
	public static Session openSession(){
		return getInstance().getSessionFactory().openSession();
	}

	public static Session getSession(){
		return getInstance().getSessionFactory().getCurrentSession();
	}

	public static void closeSession(){
		if(getInstance().getSessionFactory().getCurrentSession().isOpen()){
			getInstance().getSessionFactory().getCurrentSession().close();
		}
	}

	public static Transaction beginTransaction(){
		Session session = getSession();
		return session.beginTransaction();
	}

	public static Transaction getTransaction(){
		return getSession().getTransaction();
	}


	public static void rollbackTransaction(){
		if(getInstance().getSessionFactory().getCurrentSession().getTransaction().isActive()){
			getInstance().getSessionFactory().getCurrentSession().getTransaction().rollback();
		}
	}

	public static void commitTransaction(){
		getInstance().getSessionFactory().getCurrentSession().getTransaction().commit();
	}

}

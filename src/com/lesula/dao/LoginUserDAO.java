package com.lesula.dao;

import com.lesula.table.LoginUser;
import org.hibernate.Query;

import java.util.List;

public class LoginUserDAO extends HbnDAO<LoginUser, Integer>{

	public LoginUserDAO() {
		super(LoginUser.class);
	}
	
	public List<Integer> getActiveUsers(){
		String hql = "select id from LoginUser as lu where lu.isActive = :active";
		Query q = session.createQuery(hql)
				.setParameter("active", true);
		return q.list();
	}


}

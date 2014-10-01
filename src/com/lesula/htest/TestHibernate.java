package com.lesula.htest;

import com.lesula.conf.HibernateUtils;
import com.lesula.dao.LoginUserDAO;
import com.lesula.table.LoginUser;

import java.text.MessageFormat;

public class TestHibernate {

	public static void main(String[] args) {
		try{
			HibernateUtils.beginTransaction();

            LoginUser loginUser = new LoginUser();
            String email = "lesula@lesula.com";
            System.out.println(MessageFormat.format("Inserting user {0} into database", email));
            loginUser.setEmail(email);
			new LoginUserDAO().save(loginUser);
            System.out.println("insertion done... committing transaction");
            HibernateUtils.commitTransaction();
		} catch(Exception e){
			e.printStackTrace();
			HibernateUtils.rollbackTransaction();
		} finally{
			HibernateUtils.closeSession();
		}

	}

}

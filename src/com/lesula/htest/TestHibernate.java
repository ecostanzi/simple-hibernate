package com.lesula.htest;

import com.lesula.conf.HibernateUtils;
import com.lesula.dao.LoginUserDAO;
import com.lesula.table.LoginUser;
import com.lesula.table.UserAddress;

import java.text.MessageFormat;

public class TestHibernate {

    public static void main(String[] args) {
        String email = "lesula@lesula.com";
        int userId = 0;

        try{
            HibernateUtils.beginTransaction();

            LoginUserDAO loginUserDAO = new LoginUserDAO();

            LoginUser loginUser = new LoginUser();
            System.out.println(MessageFormat.format("Inserting user {0} into database", email));
            loginUser.setEmail(email);
            loginUserDAO.save(loginUser);
            int id = loginUser.getId();
            System.out.println("User inserted with id" + id);

            //creating address
            UserAddress address = new UserAddress();
            address.setUser(loginUser);
            address.setCity("Padova");
            address.setStreet("Via Padova");

            //setting address
            loginUser.setUserAddress(address);

            loginUserDAO.update(loginUser);

            System.out.println("insertion done... committing transaction");
            HibernateUtils.commitTransaction();
        } catch(Exception e){
            e.printStackTrace();
            HibernateUtils.rollbackTransaction();
        } finally{
            HibernateUtils.closeSession();
        }

        System.out.println("---------\n\n\n");

        try{
            HibernateUtils.beginTransaction();

            LoginUserDAO loginUserDAO = new LoginUserDAO();
            System.out.println("Querying user with id " + userId);
            loginUserDAO.findById(userId);

            HibernateUtils.commitTransaction();
        } catch(Exception e){
            e.printStackTrace();
            HibernateUtils.rollbackTransaction();
        } finally{
            HibernateUtils.closeSession();
        }

    }

}

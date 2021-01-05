package org.datafetch.service.dao;

import com.intuit.oauth2.exception.OAuthException;
import org.datafetch.service.controllers.QBApiController;
import org.datafetch.service.schema.User;
import org.datafetch.service.utils.Connection;
import org.datafetch.service.utils.Constants;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Map;

public class UserDOA {


    public String createNewUser(User user){
        Session session = Constants.sessionFactory.openSession();
        try{

            Transaction tnx = session.beginTransaction();

            int userId = (Integer) session.save(user);
            tnx.commit();

            return "User Created with Id: " + userId;

        } catch (Exception e){
            e.printStackTrace();

        } finally {
            session.close();

        }
        return "Error Message";
    }



    public User readUser(Integer id){
        Session session = Constants.sessionFactory.openSession();


        try {
            session.beginTransaction();
            User user = session.get(User.class, id);
            return user;

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public User UpdateUser(Map<String, Object> data)   {


        Session session = Constants.sessionFactory.openSession();


        User user = session.get(User.class, (Integer) data.get("Id"));

        if (user != null) {
            String authCode = (String) data.get("code");

            user.setCode(authCode);

            try {
                Transaction txn = session.beginTransaction();
                Map<String, String> tokens = new QBApiController().getBearerToken(authCode);

                user.setRealmId((String) data.get("realmId"));
                user.setConnectDate(java.time.LocalDateTime.now());

                user.setToken(tokens.get("token"));
                user.setRefreshToken(tokens.get("refreshToken"));

                // TODO remove state from table user

                session.saveOrUpdate(user);
                txn.commit();

                return user;
            }
            catch (OAuthException e){
                throw new RuntimeException(e);
            }finally {
                session.close();
            }


        }

        return null;
    }

    public User tokenUpdate(Map<String, Object> data)   {

        Session session = Constants.sessionFactory.openSession();



        User user = session.get(User.class, (Integer) data.get("Id"));

        if (user != null) {

            try {
                Transaction txn = session.beginTransaction();
                user.setToken((String) data.get("token"));
                user.setRefreshToken((String) data.get("refreshToken"));


                session.saveOrUpdate(user);
                txn.commit();
                return user;

            } finally {
                session.close();
            }
        }

        return null;
    }

}

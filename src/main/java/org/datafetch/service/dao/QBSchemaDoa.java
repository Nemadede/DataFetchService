package org.datafetch.service.dao;


import org.datafetch.service.utils.Constants;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class QBSchemaDoa {

//    public QBSchemaDoa(){
//        System.out.println("\nDoa ==================================================================================");
//    }

    public void saveObject(Object ob){
        Session session = Constants.sessionFactory.openSession();

        try{
            Transaction txn = session.beginTransaction();
            session.saveOrUpdate(ob);
            txn.commit();

        }finally {
            session.close();
        }
    }

    public void bulkSaveObject(ArrayList<Object> objects){
        Session session = Constants.sessionFactory.openSession();
        int count= 0;

        System.out.println("\n===== In the save method ===== ");

        try{
            Transaction txn = session.beginTransaction();
            for(Object ob : objects){
                session.saveOrUpdate(ob);
                count++;

//                if (count == 20){
//                    session.flush();
//                    session.clear();
//                }
            }

            txn.commit();

        }finally {
            session.close();
        }
    }

}

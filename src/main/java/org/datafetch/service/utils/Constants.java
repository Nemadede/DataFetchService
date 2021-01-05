package org.datafetch.service.utils;

import org.hibernate.SessionFactory;

public class Constants {
    public static final SessionFactory sessionFactory = Connection.getSessionFactory();
}

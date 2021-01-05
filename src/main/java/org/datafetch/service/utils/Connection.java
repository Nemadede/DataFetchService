package org.datafetch.service.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Connection {
    public static SessionFactory sessionFactory = null;

    public static void setUp() throws Exception{
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();

        try {

            sessionFactory = new MetadataSources(
                    registry
            ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e){
            StandardServiceRegistryBuilder.destroy(registry);
            throw new RuntimeException(e);
        }

    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}

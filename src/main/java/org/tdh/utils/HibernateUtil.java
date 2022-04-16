package org.tdh.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * @author Puti
 * @date 2022/4/15 9:49
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    private static SessionFactory getSessionFactory() {
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure().build();

        // 创建会话工厂
        SessionFactory sessionFactory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
        return sessionFactory;
    }

    public static Session getSession() {
        if (sessionFactory == null || sessionFactory.isClosed()) {
            sessionFactory = getSessionFactory();
        }
        // 会话对象
        Session session = sessionFactory.openSession();
        return session;
    }
}

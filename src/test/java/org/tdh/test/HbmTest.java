package org.tdh.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Test;
import org.tdh.domain.Depart;
import org.tdh.domain.User;
import org.tdh.utils.HibernateUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Puti
 * @date 2022/4/14 17:25
 */
public class HbmTest {

    @Test
    public void test1() {
        Session session = HibernateUtil.getSession();

        session.beginTransaction();

        User user = new User();
        user.setYhid("1122211");

        user.setYhxm("yhxm");
        user.setYhkl("yhlk");
        user.setYhxb("1");
        user.setCsrq("1");
        user.setDjrq("1");
        user.setSfjy("1");
        user.setPxh(1);

        session.save(user);

        session.getTransaction().commit();

        session.close();

    }

    @Test
    public void test2() {
        Session session = HibernateUtil.getSession();

        session.beginTransaction();

        User user = new User();
        user.setYhid("1122211");

        session.delete(user);

        session.getTransaction().commit();

        session.close();

    }

    @Test
    public void test3() {
        Session session = HibernateUtil.getSession();

        session.beginTransaction();

        User user = new User();
        user.setYhid("1122211");
        user.setYhxm("22222");
        user.setYhkl("yhlk");
        user.setYhxb("1");
        user.setCsrq("1");
        user.setDjrq("1");
        user.setSfjy("1");
        user.setPxh(1);

        session.update(user);

        session.getTransaction().commit();

        session.close();
    }

    @Test
    public void test4() {
        Session session = HibernateUtil.getSession();

        session.beginTransaction();

        User load = session.load(User.class, "112221");
        System.out.println(load);

        session.getTransaction().commit();

        session.close();
    }

    @Test
    public void test5() {
        Session session = HibernateUtil.getSession();

        session.beginTransaction();

        User user = session.get(User.class, "112221");
        System.out.println(user);

        session.getTransaction().commit();

        session.close();
    }


    @Test
    public void test6() {
        Session session = HibernateUtil.getSession();

        session.beginTransaction();

        User user = new User("4");
        User user1 = new User("5");
        User user2 = new User("6");

        Set<User> users = new HashSet<>();
        users.add(user);
        users.add(user1);
        users.add(user2);

        Depart depart = new Depart("ccc");
//        depart.setUsers(users);

        session.save(user);
        session.save(user1);
        session.save(user2);

        session.save(depart);

        session.getTransaction().commit();

        session.close();
    }

    @Test
    public void test7() {
        Session session = HibernateUtil.getSession();

        session.beginTransaction();

        User user = new User("4");

        Set<User> users = new HashSet<>();
        users.add(user);

        Depart depart = new Depart("ccc");
//        depart.setUsers(users);

        session.save(depart);

        session.getTransaction().commit();

        session.close();
    }

    @Test
    public void test8() {
        Session session = HibernateUtil.getSession();

        session.beginTransaction();

        User user = new User("5fsf");

//        user.setDepart(new Depart("ddd"));

        session.save(user);

        session.getTransaction().commit();

        session.close();
    }
}

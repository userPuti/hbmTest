package org.tdh.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.tdh.dao.UserDao;
import org.tdh.domain.User;
import org.tdh.dto.YhxxDto;

import java.util.Iterator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ImplUserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testSelectAll() {
        User user = userDao.selectUserByYhid("中文");
        System.out.println(user);
    }

    @Test
    @Transactional
    public void testSelectUser() {
        YhxxDto yhxxDto = new YhxxDto();
        yhxxDto.setYhbm("32010002");
        yhxxDto.setYhid("中文");
        Iterator<User> iterator = userDao.selectUser(yhxxDto).iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    @Transactional
    public void test3() {
        User user1 = userDao.selectUserByYhidAndYhkl("aa","aa");
        System.out.println(user1);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testInsert() {
        User user = new User();
        user.setYhid("qqqqqqqqq");
        userDao.insertUser(user);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testdeleteUser() {
        userDao.deleteUser("112221");
    }


    @Test
    @Transactional
    @Rollback(false)
    public void testbulkdel() {
        userDao.bulkDel(new String[]{"11", "187", "188", "189"});
    }

    @Test
    @Transactional
    @Rollback(false)
    public void testupdataUser() {
        User user = new User();
        user.setYhid("1122211");
        user.setCsrq("2");
        userDao.updataUser(user);
    }
}
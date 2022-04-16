package org.tdh.dao.impl;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.tdh.dao.DepartDao;
import org.tdh.domain.Depart;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ImplDepartDaoTest {

    @Autowired
    private DepartDao departDao;

    @Test
    @Transactional
    public void testSelectAllDepart() {
        List<Depart> departs = departDao.selectAllDepart();
        for (Depart depart : departs) {
            System.out.println(depart);
        }
    }
}
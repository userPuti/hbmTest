package org.tdh.dao.impl;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.tdh.dao.BzdmDao;
import org.tdh.domain.Bzdm;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ImplBzdmDaoTest {

    @Autowired
    private BzdmDao bzdmDao;

    @Test
    @Transactional
    public void selectBzdmByKind() {
        List<Bzdm> bzdms = bzdmDao.selectBzdmByKind("00003");
        for (Bzdm bzdm : bzdms) {
            System.out.println(bzdm);
            System.out.println(bzdm.getId().getCode());
        }
    }
}
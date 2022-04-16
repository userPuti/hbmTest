package org.tdh.dao.impl;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.tdh.dao.BzdmDao;
import org.tdh.domain.Bzdm;

import java.util.List;

/**
 * @author Puti
 * @date 2022/4/16 16:44
 */
@Repository
@Transactional
public class ImplBzdmDao implements BzdmDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    /**
     * 根据kind查询bzdm信息
     *
     * @param kind kind信息
     * @return kind对应的bzdm的List对象
     */
    @Override
    public List<Bzdm> selectBzdmByKind(String kind) {
        String hql = "from Bzdm bzdm where bzdm.id.kind = :kindInfo";
        Session currentSession = hibernateTemplate.getSessionFactory().getCurrentSession();
        List<Bzdm> kindList = currentSession.createQuery(hql).setParameter("kindInfo", kind).list();
        return kindList;
    }
}

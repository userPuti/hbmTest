package org.tdh.dao.impl;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.tdh.dao.DepartDao;
import org.tdh.domain.Depart;

import java.util.List;

/**
 * @author Puti
 * @date 2022/4/16 16:44
 */
@Repository
@Transactional
public class ImplDepartDao implements DepartDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    /**
     * 查询所有的部门信息
     *
     * @return 部门的List集合
     */
    @Override
    public List<Depart> selectAllDepart() {
        Session currentSession = hibernateTemplate.getSessionFactory().getCurrentSession();
        String hql = "from Depart";
        List<Depart> departs = currentSession.createQuery(hql).list();
        return departs;
    }
}

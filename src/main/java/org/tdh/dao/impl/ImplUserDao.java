package org.tdh.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.tdh.dao.UserDao;
import org.tdh.domain.User;
import org.tdh.dto.YhxxDto;

import java.io.Serializable;
import java.util.List;

/**
 * @author Puti
 * @date 2022/4/16 9:41
 */
@Repository
public class ImplUserDao implements UserDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;


    /**
     * 根据用户id查询用户
     *
     * @param yhid 用户id
     * @return User对象，查询不到返回null
     */
    @Override
    public User selectUserByYhid(String yhid) {
        return this.hibernateTemplate.get(User.class, yhid);
    }


    /**
     * 根据用户信息入参对象查询对应的用户
     *
     * @param yhxxDto 用户信息入参对象
     * @return 用户信息List集合
     */
    @Override
    public List<User> selectUser(YhxxDto yhxxDto) {
        StringBuilder hql = new StringBuilder("from User");

        int changed = 0;

        String yhid = yhxxDto.getYhid();
        if (yhid != null && !yhid.equals("")) {
            hql.append(" and yhid='").append(yhid).append("'");
            changed = 1;
        }

        String yhbm = yhxxDto.getYhbm();
        if (yhbm != null && !yhbm.equals("")) {
            hql.append(" and yhbm='").append(yhbm).append("'");
            changed = 1;
        }

        if (1 == changed) {
            hql.replace(hql.indexOf("and"), hql.indexOf("and") + 3, "where");
        }

        Session currentSession = hibernateTemplate.getSessionFactory().getCurrentSession();
        List<User> users = currentSession.createQuery(hql.toString(), User.class).list();

        if (users.size() > 0) {
            return users;
        } else {
            return null;
        }
    }


    /**
     * 根据用户id和用户口令查询用户
     * @param yhid 用户id
     * @param yhkl 用户口令
     * @return 查到返回user对象，否则返回null
     */
    @Override
    public User selectUserByYhidAndYhkl(String yhid, String yhkl) {
        String hql = "from User where yhid = :id and yhkl = :kl";
        Session currentSession = hibernateTemplate.getSessionFactory().getCurrentSession();
        Query query = currentSession.createQuery(hql)
                .setParameter("id", yhid)
                .setParameter("kl", yhkl);
        List<User> users = query.list();
        if (1 == users.size()) {
            return users.get(0);
        } else {
            return null;
        }
    }

    /**
     * 插入一个user信息
     *
     * @param user 要插入到数据库的user对象
     * @return 如果为1则插入成功，否则插入失败
     */
    @Override
    public boolean insertUser(User user) {
        if (user != null) {
            Serializable save = hibernateTemplate.save(user);
            return (save != null && !save.equals(""));
        }
        return false;
    }

    /**
     * 根据yhid对象信息删除对象信息
     *
     * @param yhid yhid
     * @return 如果为true则删除成功，否则删除失败
     */
    @Override
    public boolean deleteUser(String yhid) {
        if (yhid != null && !yhid.equals("")) {
            String hql = "delete from User where yhid = :id";
            Session currentSession = hibernateTemplate.getSessionFactory().getCurrentSession();
            int res = currentSession.createQuery(hql).setParameter("id", yhid).executeUpdate();
            return 1 == res;
        }
        return false;
    }


    /**
     * 批量删除
     *
     * @param yhid yhid的字符串数组
     * @return 删除的条数
     */
    @Override
    public int bulkDel(String[] yhid) {
        StringBuilder hql = new StringBuilder("delete from User where");

        for (int i = 0; i < yhid.length; i++) {
            if(0 == i) {
                hql.append(" yhid = '").append(yhid[i]).append("'");
            } else {
                hql.append(" or yhid = '").append(yhid[i]).append("'");
            }
        }

        Session currentSession = hibernateTemplate.getSessionFactory().getCurrentSession();
        int res = currentSession.createQuery(hql.toString()).executeUpdate();
        return res;
    }

    /**
     * 更新用户信息
     *
     * @param user 要更新的对象
     * @return 更新影响的行数
     */
    @Override
    public boolean updataUser(User user) {
        String hql = "update User set yhxm = :yhxmInfo, yhkl = :yhklInfo, yhxb= :yhxbInfo, yhbm = :yhbmInfo," +
                "csrq = :csrqInfo, djrq = :djrqInfo,sfjy = :sfjyInfo, pxh = :pxhInfo where yhid = :yhidInfo";
        Session currentSession = hibernateTemplate.getSessionFactory().getCurrentSession();
        int num = currentSession.createQuery(hql).setParameter("yhxmInfo", user.getYhxm())
                .setParameter("yhklInfo", user.getYhkl())
                .setParameter("yhxbInfo", user.getYhxb())
                .setParameter("yhbmInfo", user.getYhbm())
                .setParameter("csrqInfo", user.getCsrq())
                .setParameter("djrqInfo", user.getDjrq())
                .setParameter("sfjyInfo", user.getSfjy())
                .setParameter("pxhInfo", user.getPxh())
                .setParameter("yhidInfo", user.getYhid()).executeUpdate();
        return  1 == num;
    }
}

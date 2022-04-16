package org.tdh.dao;

import org.tdh.domain.Depart;

import java.util.List;

/**
 * @author Puti
 * @date 2022/4/16 16:43
 */
public interface DepartDao {

    /**
     * 查询所有的部门信息
     *
     * @return 部门的List集合
     */
    List<Depart> selectAllDepart();
}

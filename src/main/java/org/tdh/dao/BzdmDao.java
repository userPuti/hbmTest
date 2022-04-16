package org.tdh.dao;

import org.tdh.domain.Bzdm;

import java.util.List;

/**
 * @author Puti
 * @date 2022/4/16 16:44
 */
public interface BzdmDao {
    /**
     * 根据kind查询bzdm信息
     * @param kind kind信息
     * @return kind对应的bzdm的List对象
     */
    List<Bzdm> selectBzdmByKind(String kind);
}

package org.tdh.dao;

import org.tdh.domain.User;
import org.tdh.dto.YhxxDto;

import java.util.List;

public interface UserDao {

    /**
     * 根据用户id查询用户
     *
     * @param yhid 用户id
     * @return User对象，查询不到返回null
     */
    User selectUserByYhid(String yhid);

    /**
     * 根据用户信息入参对象查询对应的用户
     *
     * @param yhxxDto 用户信息入参对象
     * @return 用户信息List集合
     */
    List<User> selectUser(YhxxDto yhxxDto);

    /**
     * 根据用户id和用户口令查询用户
     * @param yhid 用户id
     * @param yhkl 用户口令
     * @return 查到返回user对象，否则返回null
     */
    User selectUserByYhidAndYhkl(String yhid, String yhkl);

    /**
     * 插入一个user信息
     *
     * @param user 要插入到数据库的user对象
     * @return 如果为true则插入成功，否则插入失败
     */
    boolean insertUser(User user);

    /**
     * 根据yhid对象信息删除对象信息
     *
     * @param yhid yhxxDto对象
     * @return 如果为true则删除成功，否则删除失败
     */
    boolean deleteUser(String yhid);

    /**
     * 批量删除
     * @param yhid yhid的字符串数组
     * @return 删除的条数
     */
    int bulkDel(String[] yhid);

    /**
     * 更新用户信息
     * @param user 要更新的对象
     * @return 更新影响的行数
     */
    boolean updataUser(User user);
}

package org.tdh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.hibernate.annotations.ColumnTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tdh.cache.Caches;
import org.tdh.dao.UserDao;
import org.tdh.domain.Bzdm;
import org.tdh.domain.Depart;
import org.tdh.domain.User;
import org.tdh.dto.YhxxDto;
import org.tdh.service.UserService;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author puti
 * @date 2022/3/18
 */
@Service("userService")
public class ImplUserService implements UserService {

    @Autowired
    private UserDao userDao;

    private String userListXml(List<User> list, int count) {
        StringBuilder allUserxml = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
        if (list != null && count > 0) {
            allUserxml.append("<rows>");
            allUserxml.append("<userdata name='totalnumber'><![CDATA[").append(count).append("]]></userdata>");
            for (User user : list) {
                transferToRealInfo(user);

                allUserxml.append("<row id=\"").append(user.getYhid()).append("\">");
                allUserxml.append("<cell>0</cell>");
                allUserxml.append("<cell><![CDATA[").append(user.getYhid()).append("]]></cell>");
                allUserxml.append("<cell><![CDATA[").append(user.getYhxm()).append("]]></cell>");
                allUserxml.append("<cell><![CDATA[").append(user.getYhxb()).append("]]></cell>");
                allUserxml.append("<cell><![CDATA[").append(user.getYhbm()).append("]]></cell>");

                String csrq = user.getCsrq();
                if ("".equals(csrq)) {
                    allUserxml.append("<cell><![CDATA[").append("-").append("]]></cell>");
                } else {
                    allUserxml.append("<cell><![CDATA[").append(csrq).append("]]></cell>");
                }
                allUserxml.append("<cell><![CDATA[").append(user.getDjrq()).append("]]></cell>");
                allUserxml.append("<cell><![CDATA[").append(user.getSfjy()).append("]]></cell>");

                Integer pxh = user.getPxh();
                if (null == pxh) {
                    allUserxml.append("<cell><![CDATA[").append("-").append("]]></cell>");
                } else {
                    allUserxml.append("<cell><![CDATA[").append(pxh).append("]]></cell>");
                }

                allUserxml.append("<cell><![CDATA[").append("/hbm/static/images/search.png^查看^javascript:view(\"").append(user.getYhid()).append("\")^_self").append("]]></cell>");
                allUserxml.append("<cell><![CDATA[").append("/hbm/static/images/modify.png^修改^javascript:modify(\"").append(user.getYhid()).append("\")^_self").append("]]></cell>");
                allUserxml.append("<cell><![CDATA[").append("/hbm/static/images/delete.png^删除^javascript:delInfo(\"").append(user.getYhid()).append("\")^_self").append("]]></cell>");
                allUserxml.append("</row>");
            }
            allUserxml.append("</rows>");
        } else {
            //没数据
            allUserxml.append("<rows><userdata name='totalnumber'>0</userdata></rows>");
        }
        return allUserxml.toString();
    }

    private User transferToRealInfo(User user) {
        String yhbm = user.getYhbm();
        if (yhbm != null && !yhbm.equals("")) {
            for (Map.Entry<String, Depart> departEntry : Caches.departMap.entrySet()) {
                if (departEntry.getKey().equals(yhbm)) {
                    user.setYhbm(departEntry.getValue().getBmmc());
                }
            }
        } else {
            user.setYhbm("-");
        }

        String yhxb = user.getYhxb();
        List<Bzdm> gender = Caches.bzdm_kind_Map.get("00003");
        if (yhxb != null && !yhxb.equals("")) {
            for (Bzdm gen : gender) {
                if (Objects.equals(yhxb, gen.getId().getCode())) {
                    user.setYhxb(gen.getMc());
                }
            }
        } else {
            user.setYhxb("-");
        }

        String sfjy = user.getSfjy();

        if (Objects.equals(sfjy, "1")) {
            user.setSfjy("是");
        } else if (Objects.equals(sfjy, "0")) {
            user.setSfjy("否");
        }

        String csrq = user.getCsrq();
        if (csrq == null || csrq.equals("")) {
            user.setCsrq("-");
        }

        return user;
    }

    /**
     * 根据用户id查询用户信息
     *
     * @param yhid 用户id
     * @return 如果查询到相应的对象，则返回该用户的对象信息，否则返回null
     */
    @Override
    public User selectUserById(String yhid) {
        if (!Objects.equals(yhid, "")) {
            User user = userDao.selectUserByYhid(yhid);
            return transferToRealInfo(user);
        }
        return null;
    }


    /**
     * 登录
     *
     * @param user 传入User对象
     * @return 返回User对象, 没有则返回null
     */
    @Override
    public boolean login(User user) {
        if (user != null) {
            User loginUser = userDao.selectUserByYhidAndYhkl(user.getYhid(), user.getYhkl());
            return loginUser != null;
        }
        return false;
    }

    /**
     * 展示用户信息
     *
     * @param yhxxDto 用户信息入参对象
     * @return xml格式
     */
    @Override
    public String userInfoDisplay(YhxxDto yhxxDto) {
        if (yhxxDto != null) {
            return userListXml(userDao.selectUser(yhxxDto), userDao.getTotalNum(yhxxDto)-1);
        }
        return "";
    }

    /**
     * 新增用户信息
     *
     * @param user user对象
     * @return xml格式
     */
    @Override
    public boolean insertUser(User user) {
        if (user != null) {
            try {
                String yhid = URLDecoder.decode(URLDecoder.decode(user.getYhid(), "utf-8"), "utf-8");
                String yhxm = URLDecoder.decode(URLDecoder.decode(user.getYhxm(), "utf-8"), "utf-8");
                user.setYhid(yhid);
                user.setYhxm(yhxm);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            String sfjy = user.getSfjy();

            if (Objects.equals(sfjy, "on")) {
                user.setSfjy("1");
            } else {
                user.setSfjy("0");
            }

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String djrq = df.format(new Date());
            user.setDjrq(djrq);

            return userDao.insertUser(user);
        }

        return false;
    }

    /**
     * 删除用户信息
     *
     * @param yhxxDto 用户信息的入参对象
     * @return 是否删除成功
     */
    @Override
    public boolean deleteUserInfo(YhxxDto yhxxDto) {
        if (yhxxDto != null) {
            return userDao.deleteUser(yhxxDto.getYhid());
        }
        return false;
    }

    /**
     * 更新用户信息
     *
     * @param user user对象
     * @return 是否插入成功
     */
    @Override
    public boolean updateUserInfo(User user) {
        if (user != null) {
            String sfjy = user.getSfjy();
            if (Objects.equals(sfjy, "on")) {
                user.setSfjy("1");
            } else {
                user.setSfjy("0");
            }

           return userDao.updataUser(user);
        }
        return false;
    }

    /**
     * 查看用户的详细信息，用于弹窗
     *
     * @param yhxxDto 用户信息的入参对象
     * @return 返回查询到的user对象，否则为null
     */
    @Override
    public User viewUserInfo(YhxxDto yhxxDto) {
        if (yhxxDto != null) {
            User user = userDao.selectUserByYhid(yhxxDto.getYhid());

            if (user != null) {
                if ("".equals(user.getYhxb()) || null == user.getYhxb()) {
                    user.setYhxb("-");
                }
                if ("".equals(user.getCsrq()) || null == user.getCsrq()) {
                    user.setCsrq("-");
                }
                if ("".equals(user.getYhbm()) || null == user.getYhbm()) {
                    user.setYhbm("-");
                }
                if (null == user.getPxh()) {
                    user.setPxh(0);
                }
                return transferToRealInfo(user);
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * 批量删除用户信息
     *
     * @param dels 用户的信息集合
     * @return 是否删除成功
     */
    @Override
    public int bulkDeletion(String dels) {
        if (dels != null && !dels.equals("")) {
            String[] delYhids = dels.trim().split(",");
             return userDao.bulkDel(delYhids);
        }
        return 0;
    }

}

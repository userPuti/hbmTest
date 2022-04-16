package org.tdh.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tdh.domain.Depart;
import org.tdh.service.DepartService;

import java.util.List;

/**
 * @author Puti
 * @date 2022/4/7 8:15
 */
@Service("departService")
public class ImplDepartService implements DepartService {
    /**
     * 查询部门信息
     *
     * @return 部门对象
     */
    @Override
    public List<Depart> queryDepart() {
        return null;
    }


//
//    /**
//     * 查询部门信息
//     *
//     * @return 部门对象
//     */
//    @Override
//    public List<Depart> queryDepart() {
//        DepartExample departExample = new DepartExample();
//        departExample.createCriteria();
//        return departMapper.selectByExample(departExample);
//    }

}

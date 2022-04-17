package org.tdh.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tdh.dao.BzdmDao;
import org.tdh.dao.DepartDao;
import org.tdh.domain.Bzdm;
import org.tdh.domain.Depart;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Puti
 * @date 2022/4/11 10:26
 */
@Component
public class Caches {

    @Autowired
    private DepartDao departDao;

    @Autowired
    private BzdmDao bzdmDao;


    private final String KIND_GENDER = "00003";
    public static final Map<String, Depart> departMap = new HashMap<>();
    public static final Map<String, List<Bzdm>> bzdm_kind_Map = new HashMap<>();


    @PostConstruct
    private void initDepartMap() {
        //查库加载缓存
        List<Depart> departs = departDao.selectAllDepart();
        for (Depart depart : departs) {
            departMap.put(depart.getBmdm(), depart);
        }
    }

    @PostConstruct
    private void initGenderMap() {
        List<Bzdm> bzdms = bzdmDao.selectBzdmByKind(KIND_GENDER);

        for (Bzdm bzdm : bzdms) {
            if (bzdm_kind_Map.containsKey(bzdm.getId().getKind())) {
                bzdm.setKind(bzdm.getId().getKind());
                bzdm.setCode(bzdm.getId().getCode());
                bzdm_kind_Map.get(bzdm.getId().getKind()).add(bzdm);
            } else {
                List<Bzdm> list = new ArrayList<>();
                bzdm.setKind(bzdm.getId().getKind());
                bzdm.setCode(bzdm.getId().getCode());
                list.add(bzdm);
                bzdm_kind_Map.put(bzdm.getId().getKind(), list);
            }
        }
    }
}

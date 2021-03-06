package org.tdh.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.tdh.cache.Caches;
import org.tdh.domain.Bzdm;
import org.tdh.domain.Depart;
import org.tdh.domain.User;
import org.tdh.dto.YhxxDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.tdh.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Puti
 * @date 2022/4/11 9:38
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 登录功能
     *
     * @param resp response对象
     * @param yhid 用户id
     * @param yhkl 用户口令
     * @param jzzh 记住账号，如果勾选则将id存到cookie里面
     * @param jzmm 记住密码, 如果勾选则将密码存到cookie里面
     * @return 成功跳转到homePage，失败则留在login页面
     */
    @RequestMapping("/login")
    public ModelAndView login(HttpServletResponse resp, @RequestParam("zh") String yhid, @RequestParam("kl") String yhkl,
                              @Nullable @RequestParam("jzzh") String jzzh, @Nullable @RequestParam("jzmm") String jzmm) {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        user.setYhid(yhid);
        user.setYhkl(yhkl);

        boolean isLogin = userService.login(user);

        if (isLogin) {
            if (jzzh != null) {
                Cookie zhanghao = new Cookie("zh", yhid);
                zhanghao.setMaxAge(60 * 60 * 24 * 7);
                resp.addCookie(zhanghao);
            }
            if (jzmm != null) {
                Cookie kouling = new Cookie("kl", yhkl);
                kouling.setMaxAge(60 * 60 * 24 * 7);
                resp.addCookie(kouling);
            }
            modelAndView.setViewName("homePage");
            List<Depart> departs = new ArrayList<>();
            if (!Caches.departMap.isEmpty()) {
                for (Map.Entry<String, Depart> departEntry : Caches.departMap.entrySet()) {
                    departs.add(departEntry.getValue());
                }
            }
            try {
                modelAndView.addObject("departs", new ObjectMapper().writeValueAsString(departs).replaceAll("\"", "&quot;"));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return modelAndView;
        } else {
            modelAndView.setViewName("login");
            return modelAndView;
        }
    }

}

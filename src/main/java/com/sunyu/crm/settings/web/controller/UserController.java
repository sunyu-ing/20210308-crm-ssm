package com.sunyu.crm.settings.web.controller;

import com.sunyu.crm.settings.domain.User;
import com.sunyu.crm.settings.service.UserService;
import com.sunyu.crm.utils.MD5Util;
import com.sunyu.crm.utils.PrintJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
@RequestMapping("/settings/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public void login(User u, HttpServletRequest request, HttpServletResponse response){

        System.out.println("进入到登录验证操作");

        String loginPwd = MD5Util.getMD5(u.getLoginPwd());

        String ip = request.getRemoteAddr();

        System.out.println("ip-------:"+ip);

        try {

            User user = userService.login(u.getLoginAct(),loginPwd,ip);

            request.getSession().setAttribute("user",user);

            //request.getRequestDispatcher("WEB-INF/workbench/index.jsp").forward(request,response);
            PrintJson.printJsonObj(response,true);

        } catch (Exception e) {

            e.printStackTrace();

            String msg = e.getMessage();

            HashMap<String, Object> map = new HashMap<>();
            map.put("success",false);
            map.put("msg",msg);
            PrintJson.printJsonObj(response,map);

        }

    }

}

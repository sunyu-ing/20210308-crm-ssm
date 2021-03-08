package com.sunyu.crm.settings.service.Impl;

import com.sunyu.crm.exception.LoginException;
import com.sunyu.crm.settings.dao.UserDao;
import com.sunyu.crm.settings.domain.User;
import com.sunyu.crm.settings.service.UserService;
import com.sunyu.crm.utils.DateTimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;


@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User login(String loginAct, String loginPwd, String ip) throws LoginException {

        HashMap<String,String> map = new HashMap<>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);

        User u = userDao.login(map);

        if (u == null){
            throw new LoginException("账号密码错误");
        }

        String expireTime = u.getExpireTime();
        String currentTime = DateTimeUtil.getSysTime();
        if (expireTime.compareTo(currentTime) < 0){
            throw new LoginException("账号已失效");
        }

        String lockState = u.getLockState();
        if ("0".equals(lockState)){
            throw new LoginException("账号已锁定");
        }

        String allowIps = u.getAllowIps();
        if (!allowIps.contains(ip)){
            throw new LoginException("ip地址受限");
        }

        return u;
    }
}

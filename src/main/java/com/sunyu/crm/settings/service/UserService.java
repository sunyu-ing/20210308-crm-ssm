package com.sunyu.crm.settings.service;

import com.sunyu.crm.exception.LoginException;
import com.sunyu.crm.settings.domain.User;

public interface UserService {
    User login(String loginAct, String loginPwd, String ip) throws LoginException;
}

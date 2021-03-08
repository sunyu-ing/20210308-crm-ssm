package com.sunyu.crm.settings.dao;

import com.sunyu.crm.settings.domain.User;

import java.util.HashMap;

public interface UserDao {
    User login(HashMap<String, String> map);
}

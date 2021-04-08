package com.sunyu.crm.workbench.dao;

import com.sunyu.crm.workbench.domain.Activity;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface ActivityDao {

    int getTotalByCondition(Activity activity);

    List<Activity> getActivityListByCondition(HashMap<String,Object> vo);
}

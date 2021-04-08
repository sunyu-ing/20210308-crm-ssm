package com.sunyu.crm.workbench.service;

import com.sunyu.crm.vo.PaginationVO;
import com.sunyu.crm.workbench.domain.Activity;


public interface ActivityService {

    PaginationVO<Activity> pageList(int skipCount, int pageSize, Activity activity);

}

package com.sunyu.crm.workbench.service.Impl;

import com.sunyu.crm.vo.PaginationVO;
import com.sunyu.crm.workbench.dao.ActivityDao;
import com.sunyu.crm.workbench.domain.Activity;
import com.sunyu.crm.workbench.service.ActivityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;


@Service
public class ActivityServiceImpl implements ActivityService {

    @Resource
    private ActivityDao activityDao;

    @Override
    public PaginationVO<Activity> pageList(int skipCount, int pageSize, Activity activity) {

        //System.out.println("" + skipCount + pageSize );
        //System.out.println( activity );

        int total = activityDao.getTotalByCondition(activity);
        //System.out.println(total);

        HashMap<String,Object> map = new HashMap<>();
        map.put("skipCount",skipCount);
        map.put("pageSize",pageSize);
        map.put("name",activity.getName());
        map.put("owner",activity.getOwner());
        map.put("startDate",activity.getStartDate());
        map.put("endDate",activity.getEndDate());

        List<Activity> dataList = activityDao.getActivityListByCondition(map);

        PaginationVO<Activity> vo = new PaginationVO<>();
        vo.setTotal(total);
        vo.setDataList(dataList);

        return vo;
    }
}

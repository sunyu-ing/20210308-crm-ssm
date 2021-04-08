package com.sunyu.crm.workbench.web.controller;


import com.sunyu.crm.utils.PrintJson;
import com.sunyu.crm.vo.PaginationVO;
import com.sunyu.crm.workbench.domain.Activity;
import com.sunyu.crm.workbench.service.ActivityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Controller
@RequestMapping("/jsp/workbench/activity")
public class ActivityController {

    @Resource
    private ActivityService activityService;

    @RequestMapping(value = "/pageList.do",method = RequestMethod.GET)
    void pageList(Activity activity, HttpServletRequest request, HttpServletResponse response){

        System.out.println("进入到市场活动控制器");

        //从pageNo页开始展示
        String pageNoStr = request.getParameter("pageNo");
        int pageNo = Integer.valueOf(pageNoStr);
        //每页展示pageSize条记录
        String pageSizeStr = request.getParameter("pageSize");
        int pageSize = Integer.valueOf(pageSizeStr);
        //计算要跳过的条数
        int skipCount = (pageNo-1) * pageSize;

        PaginationVO<Activity> vo = activityService.pageList(skipCount, pageSize, activity);

        PrintJson.printJsonObj(response, vo);

    }

}

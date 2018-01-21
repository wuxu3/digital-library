package com.baizhi.back.controller;

import com.baizhi.back.service.UserService;
import com.baizhi.common.entity.DateCount;
import com.baizhi.common.entity.PageShow;
import com.baizhi.common.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("allUser")
    @ResponseBody
    public PageShow allUsers(String page, String rows) {

        if (page == null && rows == null) {

            page = "1";
            rows = "3";
        }

        Integer page1 = new Integer(page);
        Integer rows1 = new Integer(rows);

        Integer count = userService.queryCount();

        List<User> users = userService.queryAll(page1, rows1);

        System.out.println(users);

        PageShow pageShow = new PageShow();

        pageShow.setRows(users);
        pageShow.setTotal(count);

        return pageShow;

    }


    @RequestMapping("updateSta")
    @ResponseBody
    public String updateSta(String id, String pj) {

        userService.modifyStatus(id, pj);

        String msg = "修改成功";

        return msg;

    }


    @RequestMapping("countByDate")
    @ResponseBody
    public List<DateCount> getCountByDate() {

        List<DateCount> list = new ArrayList<DateCount>();

        String condition1 = "week";
        String condition2 = "month";
        String condition3 = "year";

        list.add(new DateCount(userService.queryCountByDate(condition1), "周活跃度"));
        list.add(new DateCount(userService.queryCountByDate(condition2), "月活跃度"));
        list.add(new DateCount(userService.queryCountByDate(condition3), "年活跃度"));

        return list;
    }


}

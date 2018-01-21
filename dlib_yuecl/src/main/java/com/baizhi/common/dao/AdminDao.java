package com.baizhi.common.dao;

import com.baizhi.common.entity.Admin;

public interface AdminDao {

    //添加新的管理员功能
    public void addAdmin(Admin admin);

    //查询单个的管理员
    public Admin selectOne(String username);

}

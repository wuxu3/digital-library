package com.baizhi.back.service;

import com.baizhi.common.entity.Admin;

public interface AdminService {

    public void register(Admin admin);

    public Admin login(Admin admin);

}

package com.baizhi.back.service.impl;

import com.baizhi.back.service.AdminService;
import com.baizhi.common.dao.AdminDao;
import com.baizhi.common.entity.Admin;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;

@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {

    @Resource
    private AdminDao adminDao;

    //信管理员注册的业务方法
    @Override
    public void register(Admin admin) {

        System.out.println("输出" + adminDao);

        Admin adminDB = adminDao.selectOne(admin.getUsername());

        if (adminDB != null) {
            throw new RuntimeException("该用户已经被注册了");
        } else {
            admin.setId(UUID.randomUUID().toString());
            adminDao.addAdmin(admin);
        }

    }

    //查询单个的管理员的登录的方法
    @Override
    public Admin login(Admin admin) {

        Admin adminDB = adminDao.selectOne(admin.getUsername());

        if (adminDB == null) {

            throw new RuntimeException("该用户不存在");
        } else {
            if (adminDB.getPassword().equals(admin.getPassword())) {

                return adminDB;
            } else {

                throw new RuntimeException("用户密码输入错误");
            }
        }
    }
}

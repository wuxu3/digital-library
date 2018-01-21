package com.baizhi.back.service.impl;

import com.baizhi.back.service.UserService;
import com.baizhi.common.annotation.Read;
import com.baizhi.common.annotation.Write;
import com.baizhi.common.dao.UserDao;
import com.baizhi.common.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    @Read
    public List<User> queryAll(Integer page, Integer rows) {

        page = page * rows - rows;
        List<User> users = userDao.selectAllUser(page, rows);
        return users;
    }

    @Override
    @Read
    public Integer queryCount() {

        Integer count = userDao.selectCount();
        return count;
    }

    @Override
    @Write
    public void modifyStatus(String id, String bj) {

        if (bj.equals("0")) {

            String status = "0";
            userDao.updateStatus(id, status);
        } else {
            String status = "1";
            userDao.updateStatus(id, status);

        }

    }

    @Override
    public List<User> queryUsers() {

        List<User> users = userDao.selectAllUsers();

        return users;
    }

    @Override
    public Integer queryCountByDate(String condition) {

        Integer count = userDao.selectCountByDate(condition);

        return count;
    }
}

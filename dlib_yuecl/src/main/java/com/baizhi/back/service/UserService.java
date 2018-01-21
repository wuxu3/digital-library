package com.baizhi.back.service;

import com.baizhi.common.entity.User;

import java.util.List;

public interface UserService {

    public List<User> queryAll(Integer page, Integer rows);

    public Integer queryCount();

    public void modifyStatus(String id, String bj);

    public List<User> queryUsers();

    public Integer queryCountByDate(String condition);

}

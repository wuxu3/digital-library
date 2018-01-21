package com.baizhi.front.service;

import com.baizhi.common.entity.User;

public interface UserFrontService {

    public User userLogin(User user);

    public void userRegister(User user);

    public void modifyPwd(User user);
}

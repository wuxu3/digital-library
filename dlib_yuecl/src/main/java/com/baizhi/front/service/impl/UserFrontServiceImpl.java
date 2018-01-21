package com.baizhi.front.service.impl;

import com.baizhi.common.dao.BookrackDao;
import com.baizhi.common.dao.UserDao;
import com.baizhi.common.entity.User;
import com.baizhi.common.util.RandomStrUtil;
import com.baizhi.front.service.UserFrontService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.UUID;

@Service("userFrontService")
@Transactional
public class UserFrontServiceImpl implements UserFrontService {

    @Resource
    private UserDao userDao;

    @Resource
    private BookrackDao bookrackDao;

    @Override
    public User userLogin(User user) {

        User userDB = userDao.selectOne(user.getName());

        System.out.println(userDB);

        if (userDB == null) {

            throw new RuntimeException("该用户不存在");

        } else {

            String newPwd = DigestUtils.md5DigestAsHex((user.getPassword() + userDB.getSalt()).getBytes());

            if (newPwd.equals(userDB.getPassword())) {

                return userDB;


            } else {

                throw new RuntimeException("用户密码不正确");

            }

        }

    }

    @Override
    public void userRegister(User user) {

        User userDB = userDao.selectOne(user.getName());

        if (userDB == null) {


            //md5加密，生成私盐
            String salt = RandomStrUtil.getString(4);

            String pwd = DigestUtils.md5DigestAsHex((user.getPassword() + salt).getBytes());

            user.setPassword(pwd);

            user.setSalt(salt);

            user.setUserSatus("1");

            user.setId(UUID.randomUUID().toString());

            //给用户默认添加书架
            String rackId = UUID.randomUUID().toString();

            user.setRackId(rackId);
            bookrackDao.addRack(rackId, user.getName());

            userDao.addUser(user);


        } else {

            throw new RuntimeException("该用户已经被注册");
        }


    }

    @Override
    public void modifyPwd(User user) {

        //用户修改密码生成新的私盐
        String salt = RandomStrUtil.getString(4);

        user.setSalt(salt);

        //私盐加密生成新的加密后的密码
        String newPwd = DigestUtils.md5DigestAsHex((user.getPassword() + salt).getBytes());

        user.setPassword(newPwd);

        //调用dao的方法进行修改密码
        userDao.updatePwd(user);
    }
}

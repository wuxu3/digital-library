package com.baizhi.common.dao;

import com.baizhi.common.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

    public List<User> selectAllUser(@Param("page") Integer page, @Param("rows") Integer rows);

    public Integer selectCount();

    public void updateStatus(@Param("id") String id, @Param("status") String status);

    public void addUser(User user);

    public User selectOne(String id);

    public void updatePwd(User user);

    public List<User> selectAllUsers();

    public Integer selectCountByDate(@Param("condition") String condition);

}

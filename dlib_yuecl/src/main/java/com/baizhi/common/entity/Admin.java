package com.baizhi.common.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 创建管理员实体类
 */

public class Admin {

    private String id;
    private String username;
    private String password;
    private String realname;
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date bir;

    public Admin() {
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", realname='" + realname + '\'' +
                ", bir=" + bir +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Date getBir() {
        return bir;
    }

    public void setBir(Date bir) {
        this.bir = bir;
    }

    public Admin(String id, String username, String password, String realname, Date bir) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.realname = realname;
        this.bir = bir;
    }
}

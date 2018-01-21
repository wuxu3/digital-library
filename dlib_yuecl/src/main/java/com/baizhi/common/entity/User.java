package com.baizhi.common.entity;

import java.util.Date;

public class User {

    private String id;
    private String name;
    private String accoutNumber;
    private String password;
    private String photo;
    private Integer inreadcount;
    private Integer outreadcount;
    private String userSatus;
    private Date registerdate;
    private String rackId;
    private Date bir;
    private String salt;

    public User(String id, String name, String accoutNumber, String password, String photo, Integer inreadcount, Integer outreadcount, String userSatus, Date registerdate, String rackId, Date bir, String salt) {
        this.id = id;
        this.name = name;
        this.accoutNumber = accoutNumber;
        this.password = password;
        this.photo = photo;
        this.inreadcount = inreadcount;
        this.outreadcount = outreadcount;
        this.userSatus = userSatus;
        this.registerdate = registerdate;
        this.rackId = rackId;
        this.bir = bir;
        this.salt = salt;
    }

    public User() {

    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", accoutNumber='" + accoutNumber + '\'' +
                ", password='" + password + '\'' +
                ", photo='" + photo + '\'' +
                ", inreadcount=" + inreadcount +
                ", outreadcount=" + outreadcount +
                ", userSatus='" + userSatus + '\'' +
                ", registerdate=" + registerdate +
                ", rackId='" + rackId + '\'' +
                ", bir=" + bir +
                ", salt='" + salt + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccoutNumber() {
        return accoutNumber;
    }

    public void setAccoutNumber(String accoutNumber) {
        this.accoutNumber = accoutNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getInreadcount() {
        return inreadcount;
    }

    public void setInreadcount(Integer inreadcount) {
        this.inreadcount = inreadcount;
    }

    public Integer getOutreadcount() {
        return outreadcount;
    }

    public void setOutreadcount(Integer outreadcount) {
        this.outreadcount = outreadcount;
    }

    public String getUserSatus() {
        return userSatus;
    }

    public void setUserSatus(String userSatus) {
        this.userSatus = userSatus;
    }

    public Date getRegisterdate() {
        return registerdate;
    }

    public void setRegisterdate(Date registerdate) {
        this.registerdate = registerdate;
    }

    public String getRackId() {
        return rackId;
    }

    public void setRackId(String rackId) {
        this.rackId = rackId;
    }

    public Date getBir() {
        return bir;
    }

    public void setBir(Date bir) {
        this.bir = bir;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}

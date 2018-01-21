package com.baizhi.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Banner {

    private String id;
    private String name;
    private String url;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date uploaddate;

    @Override
    public String toString() {
        return "Banner{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", uploaddate=" + uploaddate +
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getUploaddate() {
        return uploaddate;
    }

    public void setUploaddate(Date uploaddate) {
        this.uploaddate = uploaddate;
    }

    public Banner(String id, String name, String url, Date uploaddate) {

        this.id = id;
        this.name = name;
        this.url = url;
        this.uploaddate = uploaddate;
    }

    public Banner() {

    }
}

package com.baizhi.back.service;

import com.baizhi.common.entity.Banner;

import java.util.List;

public interface BannerService {

    public void saveBanner(Banner banner);

    public void removeBanner(String id);

    public void modifyBanner(Banner banner);

    public List<Banner> queryAll(Integer page, Integer rows);

    public Banner queryOne(String id);

    public Integer queryCount();

}

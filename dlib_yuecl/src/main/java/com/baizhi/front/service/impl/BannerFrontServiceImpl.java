package com.baizhi.front.service.impl;

import com.baizhi.common.annotation.Read;
import com.baizhi.common.dao.BannerDao;
import com.baizhi.common.entity.Banner;
import com.baizhi.front.service.BannerFrontService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("bannerFrontService")
@Transactional
public class BannerFrontServiceImpl implements BannerFrontService {

    @Resource
    private BannerDao bannerDao;

    @Override
    @Read
    public List<Banner> getBanners() {

        return bannerDao.getAllBanner();
    }
}

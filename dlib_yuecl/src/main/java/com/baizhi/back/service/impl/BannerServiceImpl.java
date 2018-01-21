package com.baizhi.back.service.impl;

import com.baizhi.back.service.BannerService;
import com.baizhi.common.annotation.Read;
import com.baizhi.common.annotation.Write;
import com.baizhi.common.dao.BannerDao;
import com.baizhi.common.entity.Banner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service("bannerService")
@Transactional
public class BannerServiceImpl implements BannerService {

    @Resource
    private BannerDao bannerDao;


    @Override
    @Write
    public void saveBanner(Banner banner) {

        banner.setId(UUID.randomUUID().toString());
        bannerDao.addBanner(banner);


    }

    @Override
    @Write
    public void removeBanner(String id) {

        bannerDao.deleteBanner(id);
    }

    @Override
    @Write
    public void modifyBanner(Banner banner) {

        bannerDao.updateBanner(banner);
    }

    @Override
    @Read
    public List<Banner> queryAll(Integer page, Integer rows) {

        page = page * rows - rows;
        return bannerDao.selectAllBanner(page, rows);
    }

    @Override
    @Read
    public Banner queryOne(String id) {
        return bannerDao.selectOne(id);
    }

    @Override
    @Read
    public Integer queryCount() {

        Integer count = bannerDao.bannerCount();
        return count;
    }
}

package com.baizhi.common.dao;

import com.baizhi.common.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerDao {

    public void addBanner(Banner banner);

    public void deleteBanner(String id);

    public void updateBanner(Banner banner);

    public List<Banner> selectAllBanner(@Param("page") Integer page, @Param("rows") Integer rows);

    public Banner selectOne(String id);

    public Integer bannerCount();

    public List<Banner> getAllBanner();
}

package com.baizhi.front.controller;


import com.baizhi.common.entity.Banner;
import com.baizhi.front.service.BannerFrontService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("bannerf")
public class BannerFrontController {


    @Resource
    private BannerFrontService bannerFrontService;


    @RequestMapping("getAllBanner")
    @ResponseBody
    public List<Banner> getAllBanner() {

        List<Banner> list = bannerFrontService.getBanners();

        return list;

    }
}

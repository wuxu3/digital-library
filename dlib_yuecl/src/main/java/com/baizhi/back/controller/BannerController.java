package com.baizhi.back.controller;

import com.baizhi.back.service.BannerService;
import com.baizhi.common.dao.LuceneDao;
import com.baizhi.common.entity.Banner;
import com.baizhi.common.entity.PageShow;
import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("banner")
public class BannerController {

    @Resource
    private BannerService bannerService;


    @RequestMapping("allBanner")
    @ResponseBody
    public PageShow allBanner(String page, String rows) {

        if (page == null && rows == null) {

            page = "1";
            rows = "3";

        }

        Integer page1 = new Integer(page);
        Integer rows1 = new Integer(rows);

        PageShow pageShow = new PageShow();

        List<Banner> banners = bannerService.queryAll(page1, rows1);

        Integer count = bannerService.queryCount();

        pageShow.setRows(banners);
        pageShow.setTotal(count);

        return pageShow;
    }

    @RequestMapping("oneBanner")
    @ResponseBody
    public Banner oneBanner(String id) {

        Banner banner = bannerService.queryOne(id);

        return banner;

    }


    @RequestMapping(value = "uploadBanner", method = RequestMethod.POST)
    public void uploadBanner(HttpServletRequest request, @RequestParam("file") MultipartFile file, Banner banner) throws Exception {

        String filePath = request.getRealPath("back/static/file/banners");

        String fileName = UUID.randomUUID().toString() + file.getOriginalFilename();

        String path = "/dlib/back/static/file/banners/" + fileName;

        file.transferTo(new File(filePath, fileName));

        banner.setUrl(path);

        bannerService.saveBanner(banner);
    }


    @RequestMapping("delBanner")
    @ResponseBody
    public String delBanner(String id) {

        bannerService.removeBanner(id);

        return "删除成功";
    }

    @RequestMapping(value = "updateBanner", method = RequestMethod.POST)
    public void updateBanner(HttpServletRequest request, @RequestParam("file") MultipartFile file, Banner banner) throws Exception {

        System.out.println("文件名" + file.getOriginalFilename());

        if (file.getOriginalFilename().equals("")) {

            banner.setUrl(bannerService.queryOne(banner.getId()).getUrl());

            bannerService.modifyBanner(banner);

        } else {

            String filePath = request.getRealPath("back/static/file/banners");

            String fileName = UUID.randomUUID().toString() + file.getOriginalFilename();

            String path = "/dlib/back/static/file/banners/" + fileName;

            file.transferTo(new File(filePath, fileName));

            banner.setUrl(path);

            bannerService.modifyBanner(banner);

        }


    }


    //根据检索索引查询轮播图信息

    @RequestMapping("queryAllByIndex")
    @ResponseBody
    public List<Banner> queryAllByIndex(String value) throws ParseException {

        System.out.println(value);

        LuceneDao luceneDao = new LuceneDao();

        List<Banner> banners = luceneDao.queryAllIndex(value, 100);

        return banners;
    }


}

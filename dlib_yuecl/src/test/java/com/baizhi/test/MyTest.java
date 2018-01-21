package com.baizhi.test;

import com.baizhi.Application;
import com.baizhi.back.service.BannerService;
import com.baizhi.common.dao.BookDao;
import com.baizhi.common.dao.LuceneDao;
import com.baizhi.common.entity.Banner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class MyTest {

    @Resource
    private BannerService bannerService;

    @Resource
    private BookDao bookDao;


    @Test
    public void test1() {

        List<Banner> banners = bannerService.queryAll(1, 3);

        LuceneDao luceneDao = new LuceneDao();

        for (int i = 0; i < banners.size(); i++) {

            System.out.println(banners.get(i));
            luceneDao.addIndex(banners.get(i));

        }

        /*List<Book> books = bookDao.selectAllBooks(1, 3);
        System.out.println(books);
        List<Book> books1 = bookDao.selectAllBooks(1, 3);
        System.out.println(books1);
        List<Book> books2 = bookDao.selectAllBooks(1, 3);
        System.out.println(books2);*/


    }


    public void test2() {

        System.out.println("第一次修改");

        System.out.println("shijianbaa");
        System.out.println("第三次修改");

    }
}

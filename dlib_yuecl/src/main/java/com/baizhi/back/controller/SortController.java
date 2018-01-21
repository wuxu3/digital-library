package com.baizhi.back.controller;

import com.baizhi.back.service.BookService;
import com.baizhi.back.service.SortService;
import com.baizhi.common.entity.PageShow;
import com.baizhi.common.entity.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("sort")
public class SortController {

    @Resource
    private SortService sortService;

    @Resource
    private BookService bookService;

    @RequestMapping("allSorts")
    @ResponseBody
    public PageShow allSorts(String page, String rows) {

        if (page == null && rows == null) {
            page = "1";
            rows = "3";
        }

        Integer page1 = Integer.parseInt(page);
        Integer rows1 = Integer.parseInt(rows);

        List<Sort> sorts = sortService.queryAllSorts(page1, rows1);

        PageShow pageShow = new PageShow();

        pageShow.setTotal(sortService.allSecondSortCount());

        pageShow.setRows(sorts);

        //System.out.println(pageShow);

        return pageShow;
    }

    @RequestMapping("modify")
    @ResponseBody
    public String modify(Sort sort) {

        System.out.println(sort);

        sortService.modifySort(sort);

        return "修改成功";
    }

    @RequestMapping("singleSort")
    @ResponseBody
    public Sort singleSort(String id) {

        Sort sort = sortService.queryOne(id);

        return sort;
    }


    @RequestMapping("fadSort")
    @ResponseBody
    public List<Sort> fadSort(String id) {

        Sort childSort = sortService.queryOne(id);

        Sort parentSort = sortService.queryOne(childSort.getParentId());

        List<Sort> list = new ArrayList<Sort>();

        list.add(childSort);
        list.add(parentSort);
        return list;
    }


    @RequestMapping("allFirstSort")
    @ResponseBody
    public List<Sort> allFirstSort() {

        List<Sort> list = sortService.queryAllFirstSort();

        System.out.println(list);

        return list;
    }


    @RequestMapping("addSort")
    @ResponseBody
    public String addSort(Sort sort) {

        sortService.saveSort(sort);

        return "添加成功";
    }

    @RequestMapping("secondSort")
    @ResponseBody
    public List<Sort> secondSort(String parentId) {

        List<Sort> sorts = sortService.querySecSortByParId(parentId);

        return sorts;

    }


    @RequestMapping("delSort")
    @ResponseBody
    public String delSort(String id) {

        //将分类下面的所有的书籍改变为下架
        bookService.modifyBookStatus(id);

        //删除该分类
        sortService.removeSort(id);

        return "删除成功";
    }
}

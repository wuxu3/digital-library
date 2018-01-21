package com.baizhi.front.controller;

import com.baizhi.common.entity.BookAndRack;
import com.baizhi.common.entity.Result;
import com.baizhi.front.service.BookAndRackFrontService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("bokandRck")
public class BookAndRackFrontController {

    @Resource
    private BookAndRackFrontService bookAndRackFrontService;


    @RequestMapping("addBooksToRack")
    @ResponseBody
    public Result addBoksToRack(BookAndRack bookAndRack) {

        Result result = new Result();

        try {
            bookAndRackFrontService.saveBoksForRack(bookAndRack);

            result.setMsg("添加书籍成功");
            result.setSuccess(false);
        } catch (Exception e) {

            e.printStackTrace();

            result.setMsg(e.getMessage());
            result.setSuccess(false);

        }

        return result;

    }


}

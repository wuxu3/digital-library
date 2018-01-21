package com.baizhi.front.controller;

import com.baizhi.common.entity.Sort;
import com.baizhi.front.service.SortFrontService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("sortf")
public class SortFrontController {

    @Resource
    private SortFrontService sortFrontService;


    @RequestMapping("getSorts")
    @ResponseBody
    public List<Sort> getSorts() {

        return sortFrontService.getFirstSorts();


    }

}

package com.baizhi.front.service.impl;

import com.baizhi.common.annotation.Read;
import com.baizhi.common.dao.SortDao;
import com.baizhi.common.entity.Sort;
import com.baizhi.front.service.SortFrontService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("sortFrontService")
@Transactional
public class SortFrontServiceImpl implements SortFrontService {

    @Resource
    private SortDao sortDao;

    @Override
    @Read
    public List<Sort> getFirstSorts() {


        return sortDao.selectAllFirstSorts();
    }
}

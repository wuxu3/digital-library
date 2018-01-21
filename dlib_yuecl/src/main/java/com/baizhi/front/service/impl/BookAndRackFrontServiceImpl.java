package com.baizhi.front.service.impl;

import com.baizhi.common.annotation.Write;
import com.baizhi.common.dao.BookAndRackDao;
import com.baizhi.common.entity.BookAndRack;
import com.baizhi.front.service.BookAndRackFrontService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;

@Service("bookAndRackFrontService")
@Transactional
public class BookAndRackFrontServiceImpl implements BookAndRackFrontService {

    @Resource
    private BookAndRackDao bookAndRackDao;

    @Override
    @Write
    public void saveBoksForRack(BookAndRack bookAndRack) {

        bookAndRack.setId(UUID.randomUUID().toString());
        bookAndRackDao.addBoksToRack(bookAndRack);

    }
}

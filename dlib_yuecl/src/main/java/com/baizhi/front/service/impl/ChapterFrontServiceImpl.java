package com.baizhi.front.service.impl;

import com.baizhi.common.annotation.Read;
import com.baizhi.common.dao.ChapterDao;
import com.baizhi.common.entity.Chapter;
import com.baizhi.front.service.ChapterFrontService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("chapterFrontService")
@Transactional
public class ChapterFrontServiceImpl implements ChapterFrontService {

    @Resource
    private ChapterDao chapterDao;

    @Override
    @Read
    public List<Chapter> queryChapsByBookId(String bokId) {

        List<Chapter> chaps = chapterDao.selectAllChapterByBookId(bokId);

        return chaps;
    }
}

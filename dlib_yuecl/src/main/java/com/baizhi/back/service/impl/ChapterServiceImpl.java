package com.baizhi.back.service.impl;

import com.baizhi.back.service.ChapterService;
import com.baizhi.common.annotation.Read;
import com.baizhi.common.annotation.Write;
import com.baizhi.common.dao.ChapterDao;
import com.baizhi.common.entity.Chapter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("chapterService")
@Transactional
public class ChapterServiceImpl implements ChapterService {

    @Resource
    private ChapterDao chapterDao;

    @Override
    @Write
    public void saveChapter(Chapter chapter) {
        chapter.setId(UUID.randomUUID().toString());
        chapter.setPublishDate(new Date());
        chapterDao.addChapter(chapter);

    }

    @Override
    @Read
    public List<Chapter> queryAll(Integer page, Integer rows) {

        page = page * rows - rows;
        List<Chapter> chaps = chapterDao.selectAll(page, rows);
        return chaps;
    }

    @Override
    @Read
    public Integer queryCount() {

        Integer count = chapterDao.selectCount();
        return count;

    }

    @Override
    @Write
    public void removeChapter(String id) {

        chapterDao.deleteChapter(id);

    }

    @Override
    @Read
    public Chapter queryOne(String id) {

        return chapterDao.selectSingle(id);
    }

    @Override
    @Write
    public void modifyChapter(Chapter chapter) {

        chapterDao.updateChapter(chapter);

    }

    @Override
    @Write
    public List<Chapter> modifyByBookId(String bookId, Integer page, Integer rows) {

        page = page * rows - rows;

        List<Chapter> chaps = chapterDao.selectByBook(bookId, page, rows);


        return chaps;
    }

    @Override
    @Read
    public Integer queryCountByBookId() {

        return chapterDao.selectByBookIdCount();
    }
}

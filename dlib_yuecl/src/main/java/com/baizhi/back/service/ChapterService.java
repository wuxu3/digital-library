package com.baizhi.back.service;

import com.baizhi.common.entity.Chapter;

import java.util.List;

public interface ChapterService {

    public void saveChapter(Chapter chapter);

    public List<Chapter> queryAll(Integer page, Integer rows);

    public Integer queryCount();

    public void removeChapter(String id);

    public Chapter queryOne(String id);

    public void modifyChapter(Chapter chapter);

    public List<Chapter> modifyByBookId(String bookId, Integer page, Integer rows);

    public Integer queryCountByBookId();

}

package com.baizhi.front.service;

import com.baizhi.common.entity.Chapter;

import java.util.List;

public interface ChapterFrontService {

    public List<Chapter> queryChapsByBookId(String bokId);
}

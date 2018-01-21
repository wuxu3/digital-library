package com.baizhi.common.dao;

import com.baizhi.common.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapterDao {

    public void addChapter(Chapter chapter);

    public void deleteChapter(String id);

    public void updateChapter(Chapter chapter);

    public List<Chapter> selectAll(@Param("page") Integer page, @Param("rows") Integer rows);

    public List<Chapter> selectByBook(@Param("bookId") String bookId, @Param("page") Integer page, @Param("rows") Integer rows);

    public Chapter selectSingle(String id);

    public Integer selectCount();

    public Integer selectByBookIdCount();

    public List<Chapter> selectAllChapterByBookId(String bookId);

}

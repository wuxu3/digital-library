package com.baizhi.common.dao;

import com.baizhi.common.entity.Book;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface BookDao {

    @Cacheable(value = "baseCache", key = "#root.methodName+#p0")
    public List<Book> selectAllBooks(@Param("page") Integer page, @Param("rows") Integer rows);

    public Integer selectBookCount();

    @CacheEvict(value = "baseCache", allEntries = true)
    public void addBooks(Book book);

    @CacheEvict(value = "baseCache", allEntries = true)
    public void deleteBook(String id);

    @CacheEvict(value = "baseCache", allEntries = true)
    public void updateBook(Book book);

    public Book selectOne(String id);

    public void updatePhoto(@Param("photo") String path, @Param("id") String id);

    public void downBooks(@Param("sortId") String sortId, @Param("newSortId") String newSortId);

    public List<Book> allBooks();

    public List<Book> selectBooksBySortId(String sortId);

    public List<Book> selectByRacK(String userId);

    public List<Book> selectNameAndNum();

    public Integer photoTypeCount(@Param("type") String type);

}

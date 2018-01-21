package com.baizhi.front.service.impl;

import com.baizhi.common.annotation.Read;
import com.baizhi.common.dao.BookDao;
import com.baizhi.common.entity.Book;
import com.baizhi.front.service.BookFrontService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("bookFrontService")
@Transactional
public class BookFrontServiceImpl implements BookFrontService {

    @Resource
    private BookDao bookDao;

    @Override
    @Read
    public List<Book> queryboksBySortId(String sortId) {

        List<Book> books = bookDao.selectBooksBySortId(sortId);

        return books;
    }

    @Override
    @Read
    public Book getSingleBook(String bookId) {

        Book book = bookDao.selectOne(bookId);

        return book;
    }

    @Override
    @Read
    public List<Book> getBooksByOwnRack(String userId) {

        List<Book> books = bookDao.selectByRacK(userId);

        return books;
    }
}

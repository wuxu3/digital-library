package com.baizhi.back.service.impl;

import com.baizhi.back.service.BookService;
import com.baizhi.common.annotation.Read;
import com.baizhi.common.annotation.Write;
import com.baizhi.common.dao.BookDao;
import com.baizhi.common.entity.Book;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("bookService")
@ResponseBody
public class BookServiceImpl implements BookService {

    @Resource
    private BookDao bookDao;

    @Override
    @Read
    public List<Book> queryAllBooks(Integer page, Integer rows) {

        page = page * rows - rows;

        List<Book> books = bookDao.selectAllBooks(page, rows);

        return books;
    }

    @Override
    @Read
    public Integer queryBookCount() {

        Integer count = bookDao.selectBookCount();

        return count;
    }

    @Override
    @Write
    public void saveBooks(Book book) {

        book.setId(UUID.randomUUID().toString());
        book.setPublishdate(new Date());   //添加书籍的上架的时间
        book.setReadNumber(0);  //添加书籍的阅读量
        book.setStatus(1); //添加书籍的状态，默认是上架状态

        bookDao.addBooks(book);

    }

    @Override
    @Write
    public void removeBook(String id) {

        bookDao.deleteBook(id);

    }

    @Override
    @Write
    public void modifyBook(Book book) {

        bookDao.updateBook(book);

    }

    @Override
    @Read
    public Book queryOne(String id) {

        return bookDao.selectOne(id);
    }

    @Override
    @Write
    public void modifyPhoto(String photo, String id) {

        bookDao.updatePhoto(photo, id);

    }

    @Override
    @Write
    public void modifyBookStatus(String sortId) {

        bookDao.downBooks(sortId, "0");

    }

    @Override
    @Read
    public List<Book> queryBooks() {


        return bookDao.allBooks();
    }

    @Override
    public List<Book> queryReadByBook() {
        List<Book> list = bookDao.selectNameAndNum();

        return list;
    }

    @Override
    public Integer queryPhotoTYpe(String type) {

        Integer count = bookDao.photoTypeCount(type);

        return count;
    }
}

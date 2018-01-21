package com.baizhi.back.service;

import com.baizhi.common.entity.Book;

import java.util.List;

public interface BookService {

    public List<Book> queryAllBooks(Integer page, Integer rows);

    public Integer queryBookCount();

    public void saveBooks(Book book);

    public void removeBook(String id);

    public void modifyBook(Book book);

    public Book queryOne(String id);

    public void modifyPhoto(String photo, String id);

    public void modifyBookStatus(String sortId);

    public List<Book> queryBooks();

    public List<Book> queryReadByBook();

    public Integer queryPhotoTYpe(String type);
}

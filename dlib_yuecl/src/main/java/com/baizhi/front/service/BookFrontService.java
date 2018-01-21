package com.baizhi.front.service;

import com.baizhi.common.entity.Book;

import java.util.List;

public interface BookFrontService {

    public List<Book> queryboksBySortId(String sortId);

    public Book getSingleBook(String bookId);

    public List<Book> getBooksByOwnRack(String userId);
}

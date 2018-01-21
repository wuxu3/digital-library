package com.baizhi.front.controller;

import com.baizhi.common.entity.Book;
import com.baizhi.front.service.BookFrontService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;


@RequestMapping("bookf")
@Controller
public class BookFrontController {

    @Resource
    private BookFrontService bookFrontService;

    @RequestMapping("boksBySort")
    @ResponseBody
    public List<Book> getBooksBySortId(String sortId) {

        List<Book> books = bookFrontService.queryboksBySortId(sortId);

        return books;

    }


    @RequestMapping("getOneBook")
    @ResponseBody
    public Book getOneBook(String bookId) {

        Book book = bookFrontService.getSingleBook(bookId);

        return book;
    }


    @RequestMapping("getBoksByRack")
    @ResponseBody
    public List<Book> getBoksByRack(String userId) {

        List<Book> books = bookFrontService.getBooksByOwnRack(userId);

        return books;
    }


}

package com.baizhi.back.controller;

import com.baizhi.back.service.BookService;
import com.baizhi.common.entity.Book;
import com.baizhi.common.entity.BookNum;
import com.baizhi.common.entity.DateCount;
import com.baizhi.common.entity.PageShow;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequestMapping("book")
@Controller
public class BookController {

    @Resource
    private BookService bookService;

    @RequestMapping("allBooks")
    @ResponseBody
    public PageShow allBooks(String page, String rows) {

        if (page == null && rows == null) {
            page = "1";
            rows = "3";
        }

        Integer page1 = new Integer(page);
        Integer rows1 = new Integer(rows);
        Integer count = bookService.queryBookCount();
        List<Book> books = bookService.queryAllBooks(page1, rows1);

        PageShow pageShow = new PageShow();
        pageShow.setTotal(count);
        pageShow.setRows(books);

        return pageShow;
    }


    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public void uploadBooks(HttpServletRequest request, HttpServletResponse response, @RequestParam("file") MultipartFile file, Book book) throws Exception {

        System.out.println(book);

        String filePath = request.getRealPath("back/static/file/picture");

        String fileName = UUID.randomUUID().toString() + file.getOriginalFilename();

        String path = "/dlib/back/static/file/picture/" + fileName;

        book.setPhoto(path);

        bookService.saveBooks(book);

        file.transferTo(new File(filePath, fileName));

    }


    @RequestMapping("delBook")
    @ResponseBody
    public String delBook(String id) {

        bookService.removeBook(id);

        return "删除成功";
    }


    @RequestMapping("oneBook")
    @ResponseBody
    public Book oneBook(String id) {

        Book book = bookService.queryOne(id);

        return book;

    }


    @RequestMapping("updatebook")
    @ResponseBody
    public String updatebook(Book book) {

        System.out.println(book);
        bookService.modifyBook(book);
        return "修改成功";
    }


    @RequestMapping(value = "changePhoto", method = RequestMethod.POST)
    public void changePhoto(HttpServletRequest request, @RequestParam("file") MultipartFile file, String id) throws Exception {

        String filePath = request.getRealPath("back/static/file/picture");

        String fileName = UUID.randomUUID().toString() + file.getOriginalFilename();

        String path = "/dlib/back/static/file/picture/" + fileName;

        file.transferTo(new File(filePath, fileName));

        bookService.modifyPhoto(path, id);

    }


    @RequestMapping("allBoks")
    @ResponseBody
    public List<Book> allBoks() {

        return bookService.queryBooks();

    }

    @RequestMapping("getBoksNum")
    @ResponseBody
    public BookNum getBoksNum() {

        List<String> names = new ArrayList<String>();
        List<Integer> nums = new ArrayList<Integer>();

        List<Book> books = bookService.queryReadByBook();

        //System.out.println(books);

        for (Book book : books) {

            names.add(book.getName());
            nums.add(book.getReadNumber());

        }

        BookNum bookNum = new BookNum();
        bookNum.setBookName(names);
        bookNum.setReadCount(nums);

        return bookNum;
    }


    @RequestMapping("countByType")
    @ResponseBody
    public List<DateCount> countByType() {

        List<DateCount> list = new ArrayList<DateCount>();

        String type1 = ".jpg";
        String type2 = ".png";
        String type3 = ".gif";

        list.add(new DateCount(bookService.queryPhotoTYpe(type1), "jpg类型"));
        list.add(new DateCount(bookService.queryPhotoTYpe(type2), "png类型"));
        list.add(new DateCount(bookService.queryPhotoTYpe(type3), "gif类型"));

        return list;
    }

}

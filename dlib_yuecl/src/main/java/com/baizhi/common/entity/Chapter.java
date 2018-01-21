package com.baizhi.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Chapter {

    private String id;
    private String name;
    private String url;
    private String bookId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishDate;
    private Book book;

    public Chapter(String id, String name, String url, String bookId, Date publishDate, Book book) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.bookId = bookId;
        this.publishDate = publishDate;
        this.book = book;
    }

    public Chapter() {

    }

    @Override
    public String toString() {
        return "Chapter{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", bookId='" + bookId + '\'' +
                ", publishDate=" + publishDate +
                ", book=" + book +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}

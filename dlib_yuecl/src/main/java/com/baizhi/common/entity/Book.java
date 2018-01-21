package com.baizhi.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Book {

    private String id;
    private String name;
    //书籍简介
    private String sketch;
    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    private Date publishdate;
    //书籍的分类id
    private String sortId;
    private Integer readNumber;
    //书籍封面
    private String photo;
    private String author;
    private String isbn;
    private String publish;
    //书籍状态
    private Integer status;

    private Sort sort;

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sketch='" + sketch + '\'' +
                ", publishdate=" + publishdate +
                ", sortId='" + sortId + '\'' +
                ", readNumber=" + readNumber +
                ", photo='" + photo + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publish='" + publish + '\'' +
                ", status=" + status +
                ", sort=" + sort +
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

    public String getSketch() {
        return sketch;
    }

    public void setSketch(String sketch) {
        this.sketch = sketch;
    }

    public Date getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(Date publishdate) {
        this.publishdate = publishdate;
    }

    public String getSortId() {
        return sortId;
    }

    public void setSortId(String sortId) {
        this.sortId = sortId;
    }

    public Integer getReadNumber() {
        return readNumber;
    }

    public void setReadNumber(Integer readNumber) {
        this.readNumber = readNumber;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public Book(String id, String name, String sketch, Date publishdate, String sortId, Integer readNumber, String photo, String author, String isbn, String publish, Integer status, Sort sort) {

        this.id = id;
        this.name = name;
        this.sketch = sketch;
        this.publishdate = publishdate;
        this.sortId = sortId;
        this.readNumber = readNumber;
        this.photo = photo;
        this.author = author;
        this.isbn = isbn;
        this.publish = publish;
        this.status = status;
        this.sort = sort;
    }

    public Book() {

    }
}

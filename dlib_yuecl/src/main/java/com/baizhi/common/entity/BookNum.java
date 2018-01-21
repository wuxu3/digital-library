package com.baizhi.common.entity;

import java.util.List;

public class BookNum {

    private List<String> bookName;

    private List<Integer> readCount;

    @Override
    public String toString() {
        return "BookNum{" +
                "bookName=" + bookName +
                ", readCount=" + readCount +
                '}';
    }

    public List<String> getBookName() {
        return bookName;
    }

    public void setBookName(List<String> bookName) {
        this.bookName = bookName;
    }

    public List<Integer> getReadCount() {
        return readCount;
    }

    public void setReadCount(List<Integer> readCount) {
        this.readCount = readCount;
    }

    public BookNum(List<String> bookName, List<Integer> readCount) {

        this.bookName = bookName;
        this.readCount = readCount;
    }

    public BookNum() {

    }
}

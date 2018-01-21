package com.baizhi.common.entity;

/**
 * 书架和书籍的关联实体
 */
public class BookAndRack {

    private String id;
    private String rackId;
    private String bookId;

    @Override
    public String toString() {
        return "BookAndRack{" +
                "id='" + id + '\'' +
                ", rackId='" + rackId + '\'' +
                ", bookId='" + bookId + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRackId() {
        return rackId;
    }

    public void setRackId(String rackId) {
        this.rackId = rackId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public BookAndRack(String id, String rackId, String bookId) {

        this.id = id;
        this.rackId = rackId;
        this.bookId = bookId;
    }

    public BookAndRack() {

    }
}

package com.baizhi.common.entity;

import java.util.List;

public class PageShow<T> {
    private Integer total;
    private List rows;

    @Override
    public String toString() {
        return "PageShow{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public PageShow(Integer total, List rows) {

        this.total = total;
        this.rows = rows;
    }

    public PageShow() {

    }
}

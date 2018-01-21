package com.baizhi.common.entity;

public class DateCount {

    private Integer value;
    private String name;

    @Override
    public String toString() {
        return "DateCount{" +
                "value=" + value +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DateCount(Integer value, String name) {

        this.value = value;
        this.name = name;
    }

    public DateCount() {

    }
}

package com.baizhi.common.entity;

public class Bookrack {
    private String id;
    private String name;

    @Override
    public String toString() {
        return "Bookrack{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
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

    public Bookrack(String id, String name) {

        this.id = id;
        this.name = name;
    }

    public Bookrack() {

    }
}

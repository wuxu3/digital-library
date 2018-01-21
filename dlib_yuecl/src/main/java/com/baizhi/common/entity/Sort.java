package com.baizhi.common.entity;

//二级分类实体类
public class Sort {

    private String id;
    private String name;
    private String parentId;
    private Sort firstSort;

    @Override
    public String toString() {
        return "Sort{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", parentId='" + parentId + '\'' +
                ", firstSort=" + firstSort +
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Sort getFirstSort() {
        return firstSort;
    }

    public void setFirstSort(Sort firstSort) {
        this.firstSort = firstSort;
    }

    public Sort(String id, String name, String parentId, Sort firstSort) {

        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.firstSort = firstSort;
    }

    public Sort() {

    }
}

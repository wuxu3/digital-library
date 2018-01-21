package com.baizhi.back.service;

import com.baizhi.common.entity.Sort;

import java.util.List;

public interface SortService {

    public List<Sort> queryAllSorts(Integer page, Integer rows);

    public Integer allSecondSortCount();

    public void removeSort(String id);

    public void modifySort(Sort sort);

    public Sort queryOne(String id);

    public List<Sort> queryAllFirstSort();

    public void saveSort(Sort sort);

    public List<Sort> querySecSortByParId(String parentId);

}

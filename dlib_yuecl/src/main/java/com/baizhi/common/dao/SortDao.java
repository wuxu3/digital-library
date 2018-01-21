package com.baizhi.common.dao;

import com.baizhi.common.entity.Sort;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SortDao {

    public List<Sort> selectSecondSorts(@Param("page") Integer page, @Param("rows") Integer rows);

    public Integer secondSortCount();

    public void deleteSort(String id);

    public void updateSort(Sort sort);

    public Sort selectOne(String id);


    public List<Sort> selectAllFirstSort();

    public void addSort(Sort sort);

    public List<Sort> allSecondSort(String parentId);

    public List<Sort> selectAllFirstSorts();
}

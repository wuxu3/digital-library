package com.baizhi.common.dao;

import org.apache.ibatis.annotations.Param;

public interface BookrackDao {

    public void addRack(@Param("id") String id, @Param("name") String name);

}

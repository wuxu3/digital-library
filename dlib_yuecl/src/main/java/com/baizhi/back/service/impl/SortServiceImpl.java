package com.baizhi.back.service.impl;

import com.baizhi.back.service.SortService;
import com.baizhi.common.annotation.Read;
import com.baizhi.common.annotation.Write;
import com.baizhi.common.dao.SortDao;
import com.baizhi.common.entity.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service("sortService")
@Transactional
public class SortServiceImpl implements SortService {

    @Resource
    private SortDao sortDao;

    @Override
    @Read
    public List<Sort> queryAllSorts(Integer page, Integer rows) {

        page = page * rows - rows;

        List<Sort> sorts = sortDao.selectSecondSorts(page, rows);

        return sorts;

    }

    @Override
    @Read
    public Integer allSecondSortCount() {
        Integer count = sortDao.secondSortCount();
        return count;
    }

    @Override
    @Write
    public void removeSort(String id) {

        sortDao.deleteSort(id);

    }

    @Override
    @Write
    public void modifySort(Sort sort) {

        sortDao.updateSort(sort);
    }

    @Override
    @Read
    public Sort queryOne(String id) {

        Sort sort = sortDao.selectOne(id);

        return sort;
    }

    @Override
    @Read
    public List<Sort> queryAllFirstSort() {

        return sortDao.selectAllFirstSort();
    }

    @Override
    @Write
    public void saveSort(Sort sort) {

        sort.setId(UUID.randomUUID().toString());

        sortDao.addSort(sort);

    }

    @Override
    @Read
    public List<Sort> querySecSortByParId(String parentId) {

        List<Sort> list = sortDao.allSecondSort(parentId);

        return list;
    }
}

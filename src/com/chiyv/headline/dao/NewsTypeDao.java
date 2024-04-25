package com.chiyv.headline.dao;

import com.chiyv.headline.pojo.NewsType;

import java.util.List;

public interface NewsTypeDao {
    /**
     * 查询NewsType表格中所有数据
     * @return 以List<NewsType>返回
     */
    List<NewsType> findAll();
}

package com.chiyv.headline.service;

import com.chiyv.headline.pojo.NewsType;

import java.util.List;

public interface NewsTypeService {
    /**
     * 查询所有头条类型的方法
     * @return 多个头条类型以List<NewsType>返回
     */
    List<NewsType> findAll();
}

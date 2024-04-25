package com.chiyv.headline.service;

import com.chiyv.headline.dao.NewsTypeDao;
import com.chiyv.headline.dao.NewsTypeDaoImpl;
import com.chiyv.headline.pojo.NewsType;

import java.util.List;

public class NewsTypeServiceImpl implements NewsTypeService{

    private NewsTypeDao newsTypeDao =new NewsTypeDaoImpl();
    @Override
    public List<NewsType> findAll() {
        return newsTypeDao.findAll();
    }
}

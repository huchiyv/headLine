package com.chiyv.headline.service;

import com.chiyv.headline.dao.NewsHeadLineDao;
import com.chiyv.headline.dao.NewsHeadlineDaoImpl;
import com.chiyv.headline.pojo.NewsHeadline;
import com.chiyv.headline.pojo.vo.HeadlineDetailVo;
import com.chiyv.headline.pojo.vo.HeadlinePageVo;
import com.chiyv.headline.pojo.vo.HeadlineQueryVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsHeadlineServiceImpl implements NewsHeadlineService{
    private NewsHeadLineDao newsHeadLineDao=new NewsHeadlineDaoImpl();
    @Override
    public Map findNewsPage(HeadlineQueryVo headlineQueryVo) {
        int pageNum=headlineQueryVo.getPageNum();
        int pageSize=headlineQueryVo.getPageSize();
        List<HeadlinePageVo> pageData=newsHeadLineDao.findPageList(headlineQueryVo);
        int totalSize=newsHeadLineDao.findPageCount(headlineQueryVo);//pageData.size();
        int totalPage=totalSize%pageSize==0?totalSize/pageSize:totalSize/pageSize+1;
        HashMap pageInfo = new HashMap();
        pageInfo.put("pageNum",pageNum);
        pageInfo.put("pageSize",pageSize);
        pageInfo.put("totalSize",totalSize);
        pageInfo.put("totalPage",totalPage);
        pageInfo.put("pageData",pageData);
        return pageInfo;
    }

    @Override
    public HeadlineDetailVo showHeadlineDetail(int hid) {
        //修改头条浏览量
        newsHeadLineDao.incrPageView(hid);
        //查询详情
        HeadlineDetailVo headlineDetailVo= newsHeadLineDao.findHeadlineDetail(hid);
        return headlineDetailVo;
    }

    @Override
    public int addNewsHeadLine(NewsHeadline newsHeadline) {
        return newsHeadLineDao.addNewsHeadLine(newsHeadline);
    }

    @Override
    public NewsHeadline findHeadlineByHid(Integer hid) {
        return newsHeadLineDao.findHeadlineByHid(hid);
    }

    @Override
    public int updateNewsHeadline(NewsHeadline newsHeadline) {
        return newsHeadLineDao.updateNewsHeadline(newsHeadline);
    }

    @Override
    public int removeByHid(Integer hid) {
        return newsHeadLineDao.removeByHid(hid);
    }
}

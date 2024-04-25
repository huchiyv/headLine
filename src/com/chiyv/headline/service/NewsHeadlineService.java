package com.chiyv.headline.service;

import com.chiyv.headline.pojo.NewsHeadline;
import com.chiyv.headline.pojo.vo.HeadlineDetailVo;
import com.chiyv.headline.pojo.vo.HeadlineQueryVo;

import java.util.Map;

public interface NewsHeadlineService {
    /**
     * 根据条件搜索分页信息,返回含页码数,页大小,总页数,总记录数,当前页数据等信息,并根据时间降序,浏览量降序排序
     * @param headlineQueryVo
     * @return
     */
    Map findNewsPage(HeadlineQueryVo headlineQueryVo);

    /**
     * 根据新闻id查询完整新闻文章信息，同时让新闻的浏览量+1
     * @param hid
     * @return
     */
    HeadlineDetailVo showHeadlineDetail(int hid);

    /**
     * 将新闻信息存入数据库
     * @param newsHeadline
     * @return
     */
    int addNewsHeadLine(NewsHeadline newsHeadline);

    /**
     * 根据新闻id查询新闻的完整信息并响应给前端
     * @param hid
     * @return
     */
    NewsHeadline findHeadlineByHid(Integer hid);

    /**
     * + 客户端将新闻信息修改后,提交前先请求登录校验接口校验登录状态
     * + 登录校验通过则提交修改后的新闻信息,后端接收并更新进入数据库
     *
     * @param newsHeadline
     * @return
     */
    int updateNewsHeadline(NewsHeadline newsHeadline);

    /**
     * + 将要删除的新闻id发送给服务端
     * + 服务端校验登录是否过期,未过期则直接删除,过期则响应登录过期信息
     * @param hid
     * @return
     */
    int removeByHid(Integer hid);
}

package com.chiyv.headline.dao;

import com.chiyv.headline.pojo.NewsHeadline;
import com.chiyv.headline.pojo.vo.HeadlineDetailVo;
import com.chiyv.headline.pojo.vo.HeadlinePageVo;
import com.chiyv.headline.pojo.vo.HeadlineQueryVo;

import java.util.List;

public interface NewsHeadLineDao {
    /**
     * 按条件分页查询
     * @param headlineQueryVo
     * @return
     */
    List<HeadlinePageVo> findPageList(HeadlineQueryVo headlineQueryVo);

    /**
     * 按条件查询满足条件的所有数据总数
     * @return
     */
    int findPageCount(HeadlineQueryVo headlineQueryVo);

    /**
     * 根据新闻id查询完整新闻文章信息
     * @param hid
     * @return
     */
    HeadlineDetailVo findHeadlineDetail(int hid);

    /**
     * 让新闻的浏览量+1
     * @param hid
     */
    int incrPageView(int hid);

    /**
     * 将新闻信息存入数据库
     * @param newsHeadline
     * @return
     */
    int addNewsHeadLine(NewsHeadline newsHeadline);

    /**
     * 根据新闻id查询新闻的完整信息
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

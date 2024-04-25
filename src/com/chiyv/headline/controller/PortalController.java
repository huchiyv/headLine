package com.chiyv.headline.controller;

import com.chiyv.headline.common.Result;
import com.chiyv.headline.pojo.NewsType;
import com.chiyv.headline.pojo.vo.HeadlineDetailVo;
import com.chiyv.headline.pojo.vo.HeadlinePageVo;
import com.chiyv.headline.pojo.vo.HeadlineQueryVo;
import com.chiyv.headline.service.NewsHeadlineService;
import com.chiyv.headline.service.NewsHeadlineServiceImpl;
import com.chiyv.headline.service.NewsTypeService;
import com.chiyv.headline.service.NewsTypeServiceImpl;
import com.chiyv.headline.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* 门户 控制器
* 那些不需要登录的，不需要做增删改查的门户请求
*
*
* */
@WebServlet("/portal/*")
public class PortalController extends BaseController{


    private NewsTypeService typeService= new NewsTypeServiceImpl();

    private  NewsHeadlineService newsHeadlineService=new NewsHeadlineServiceImpl();

    /**
     * 查询所有头条类型的业务接口实现
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findAllTypes(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //查询所有的新闻类型，装如Result响应给客户端
        List<NewsType> newsTypeList= typeService.findAll();

        WebUtil.writeJson(resp,Result.ok(newsTypeList));
    }

    /**
     * 根据条件搜索分页信息,返回含页码数,页大小,总页数,总记录数,当前页数据等信息,并根据时间降序,浏览量降序排序
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findNewsPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收请求的参数
        HeadlineQueryVo headlineQueryVo = WebUtil.readJson(req, HeadlineQueryVo.class);
        //根据参数分页查询
        Map pageInfo = newsHeadlineService.findNewsPage(headlineQueryVo);
        Map data = new HashMap();
        data.put("pageInfo",pageInfo);
        //返回查询结果
        WebUtil.writeJson(resp,Result.ok(data));

    }


    protected void showHeadlineDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int hid = Integer.parseInt(req.getParameter("hid"));
        HeadlineDetailVo headlineDetailVo=newsHeadlineService.showHeadlineDetail(hid);
        Map data = new HashMap();
        data.put("headline",headlineDetailVo);
        WebUtil.writeJson(resp,Result.ok(data));

    }
}
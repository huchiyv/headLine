package com.chiyv.headline.controller;

import com.chiyv.headline.common.Result;
import com.chiyv.headline.common.ResultCodeEnum;
import com.chiyv.headline.pojo.NewsUser;
import com.chiyv.headline.service.NewsUserService;
import com.chiyv.headline.service.NewsUserServiceImpl;
import com.chiyv.headline.util.JwtHelper;
import com.chiyv.headline.util.MD5Util;
import com.chiyv.headline.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;

@WebServlet("/user/*")
public class NewsUserController extends BaseController{

    private NewsUserService newsUserService=new NewsUserServiceImpl();

    /**
     * 处理登录表单提交的业务接口的实现
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收用户名和密码
        NewsUser paramUser = WebUtil.readJson(req, NewsUser.class);

        //调用服务层方法实现登录
       NewsUser loginUser= newsUserService.findByUsername(paramUser.getUsername());
        Result result = Result.ok(null);
        //向客户端响应登录验证信息

        if(null!=loginUser){

            if(MD5Util.encrypt(paramUser.getUserPwd()).equals(loginUser.getUserPwd())){
                HashMap data = new HashMap();
                data.put("token",JwtHelper.createToken(loginUser.getUid().longValue()));
                result=Result.ok(data);
            }else {
                result=Result.build(null, ResultCodeEnum.PASSWORD_ERROR );

            }
        }else {
            result=Result.build(null, ResultCodeEnum.USERNAME_ERROR);

        }

        WebUtil.writeJson(resp,result);

    }


    /**
     * 根据token请求头获取登录用户的详细信息并响应给客户端
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getUserInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求中的token
        String token = req.getHeader("token");
        Result result=Result.build(null,ResultCodeEnum.NOTLOGIN);
        if(null!=token&&!"".equals(token)){
            if(!JwtHelper.isExpiration(token)){
                Integer userId = JwtHelper.getUserId(token).intValue();
                NewsUser newsUser =newsUserService.findByUid(userId);
                if(null!=newsUser){
                    HashMap data = new HashMap();
                    newsUser.setUserPwd("");
                    data.put("loginUser",newsUser);
                     result = Result.ok(data);
                }
            }
        }
        WebUtil.writeJson(resp,result);

    }

    /**
     * 根据用户名查询用户名是否可用并做出响应
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void checkUserName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //接收参数
        String username = req.getParameter("username");
        NewsUser registerUser = newsUserService.findByUsername(username);
        Result result = Result.ok(null);
        if(null!=registerUser){
            result=Result.build(null,ResultCodeEnum.USERNAME_USED.getCode(),"用户名占用");
        }
        WebUtil.writeJson(resp,result);
    }


    /**
     * 将新用户存入数据库,存入之前做用户名是否被占用校验,校验通过响应成功提示,否则响应失败提示
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewsUser registerUser = WebUtil.readJson(req, NewsUser.class);
        Result result = Result.ok(null);
        int rows=0;
        if(null!=newsUserService.findByUsername(registerUser.getUsername())){
            result=Result.build(null,ResultCodeEnum.USERNAME_USED.getCode(),"用户名占用");
        }else{
            rows =newsUserService.regist(registerUser);
            if(rows>0){
                result = Result.ok(null);
            }
        }

        WebUtil.writeJson(resp,result);

    }


    protected void checkLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getHeader("token");
        Result  result =Result.build(null,ResultCodeEnum.NOTLOGIN);
        if(null!= token){
            if (!JwtHelper.isExpiration(token)) {
                result=Result.ok(null);
            }
        }
        WebUtil.writeJson(resp,result);
    }
}
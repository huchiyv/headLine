package com.chiyv.headline.service;

import com.chiyv.headline.pojo.NewsUser;

public interface NewsUserService {
    /**
     * 根据用户登录的账号查询用户信息的方法
     * @param username  用户输入的账户
     * @return 找到了返回NewsUser对象 找不到返回null
     */
    NewsUser findByUsername(String username);

    /**
     * 根据用户id查询用户信息
     * @param userId
     * @return
     */
    NewsUser findByUid(Integer userId);

    /**
     * 根据传入参数添加新用户
     * @param registerUser 新用户对象
     * @return
     */
    int regist(NewsUser registerUser);
}

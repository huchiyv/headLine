package com.chiyv.headline.dao;

import com.chiyv.headline.pojo.NewsUser;

public interface NewsUserDao {
    /**
     * 根据用户名查询用户
     * @param username
     * @return 成功返回NewsUser对象，失败返回null
     */
    NewsUser findByUsername(String username);

    /**
     * 根据用户id查询用户信息
     * @param userId
     * @return
     */
    NewsUser findByUid(Integer userId);

    /**
     * 根据传入参数向数据库中添加新用户
     * @param registerUser
     * @return
     */
    int regist(NewsUser registerUser);
}

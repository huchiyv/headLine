package com.chiyv.headline.service;

import com.chiyv.headline.dao.NewsUserDao;
import com.chiyv.headline.dao.NewsUserDaoImpl;
import com.chiyv.headline.pojo.NewsUser;
import com.chiyv.headline.util.MD5Util;

public class NewsUserServiceImpl implements NewsUserService{
    private NewsUserDao newsUserDao=new NewsUserDaoImpl();
    @Override
    public NewsUser findByUsername(String username) {
        return newsUserDao.findByUsername( username);
    }

    @Override
    public NewsUser findByUid(Integer userId) {
        return newsUserDao.findByUid(userId);
    }

    @Override
    public int regist(NewsUser registerUser) {
        registerUser.setUserPwd(MD5Util.encrypt(registerUser.getUserPwd()));
        return newsUserDao.regist(registerUser);
    }
}

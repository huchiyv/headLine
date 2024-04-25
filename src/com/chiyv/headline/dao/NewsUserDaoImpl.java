package com.chiyv.headline.dao;

import com.chiyv.headline.pojo.NewsUser;

import java.util.List;

public class NewsUserDaoImpl extends BaseDao implements NewsUserDao{
    @Override
    public NewsUser findByUsername(String username) {
        String sql = """
                    select 
                        uid,
                        username,
                        user_pwd userPwd,
                        nick_name nickName
                    from
                        news_user
                    where
                    username=?
                    
                """;
        List<NewsUser> newsUserList = baseQuery(NewsUser.class, sql, username);
        return newsUserList!=null&&newsUserList.size()>0?newsUserList.get(0):null;
    }

    @Override
    public NewsUser findByUid(Integer userId) {
        String sql = """
                    select 
                        uid,
                        username,
                        user_pwd userPwd,
                        nick_name nickName
                    from
                        news_user
                    where
                    uid=?
                    
                """;
        List<NewsUser> newsUserList = baseQuery(NewsUser.class, sql, userId);
        return newsUserList!=null&&newsUserList.size()>0?newsUserList.get(0):null;
    }

    @Override
    public int regist(NewsUser registerUser) {
        String sql ="insert into news_user values(DEFAULT,?,?,?)";
        return baseUpdate(sql,registerUser.getUsername(),registerUser.getUserPwd(),registerUser.getNickName());
    }
}

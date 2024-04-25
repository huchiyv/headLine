package com.chiyv.headline.filters;

import com.chiyv.headline.common.Result;
import com.chiyv.headline.common.ResultCodeEnum;
import com.chiyv.headline.util.JwtHelper;
import com.chiyv.headline.util.WebUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebFilter("/headline/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request =(HttpServletRequest)  servletRequest;
        String token = request.getHeader("token");
        boolean flag =false;
        // token不为空并且没过期
        if (null  != token ){
            boolean expiration = JwtHelper.isExpiration(token);
            if (!expiration ){
                flag=true;
            }
        }
        if (flag){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            WebUtil.writeJson((HttpServletResponse) servletResponse, Result.build(null, ResultCodeEnum.NOTLOGIN));
        }
    }
}

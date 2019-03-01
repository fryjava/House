package com.xmcc.House.web.filter;

import com.google.common.collect.Lists;
import com.xmcc.House.common.Constant;
import com.xmcc.House.pojo.User;
import com.xmcc.House.service.UserService;
import com.xmcc.House.utils.CookieUtils;
import com.xmcc.House.utils.ThreadLocalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class LoginFilter implements Filter {
  @Autowired
  private UserService  userService  ;
  @Autowired
  private ThreadLocalUtils threadLocalUtils ;
  private   ArrayList<String> urls = Lists.newArrayList();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        urls.add("/aa");
        urls.add("/user/login");
        urls.add("/user/register");
        urls.add("/accounts/register");
        urls.add("/accounts/signin");
        urls.add("/active");
        urls.add("/accounts/signin/check");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Boolean flag= false ;
        String reqUri = request.getRequestURI();
        if(urls.contains(reqUri)||reqUri.startsWith("/static")||reqUri.startsWith("/error")){
                   flag=true ;
        }

        User user=null ;
        if(!flag) {
            //首先去session中取出用户
            HttpSession session = request.getSession(true);
             user = (User) session.getAttribute(Constant.SESSION_LOGIN);
             //若没有去cookie 中取
            if (user == null) {
                Cookie cookie = CookieUtils.getCookie(request, Constant.COOKIES_LOGIN);
                if (cookie != null) {
                    String[] split = cookie.getValue().split("&");
                    if (split.length == 2 && split != null) {
                        user = userService.login(split[0], split[1]);

                    }
                }
            }

            if (user != null ) {
                threadLocalUtils.pushThreadLocal(user);
            }

        }

        if(user!=null||flag){
            filterChain.doFilter(servletRequest,servletResponse);
        }

    }
}

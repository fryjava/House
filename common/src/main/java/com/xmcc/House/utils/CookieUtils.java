package com.xmcc.House.utils;


import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
    public static Cookie getCookie(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if(cookies==null){
            return  null ;
        }
        for (Cookie cookie : cookies) {
            if (StringUtils.equals(cookie.getName(), key)) {
                return cookie; } }
        return null;
    }

    public static void setCookie(String name, String value, HttpServletResponse response) {
        Cookie cookie = new Cookie(name,value);
        cookie.setMaxAge(7*24*60*60);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        response.addCookie(cookie);


    }
    public  static  void removeCookie(String name,HttpServletResponse response){
        Cookie cookie = new Cookie(name, null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}

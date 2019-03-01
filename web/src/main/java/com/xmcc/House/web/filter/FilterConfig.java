package com.xmcc.House.web.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Autowired
    private  LoginFilter loginFilter ;
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(loginFilter);
       filterRegistrationBean.addUrlPatterns("*");
  //      filterRegistrationBean.addInitParameter("exclusions","/static/**,/accounts/signin,/user/login,/aa,/user/register");
      return  filterRegistrationBean ;
    }
}

package com.xmcc.House.web.controlller;

import com.xmcc.House.common.Constant;
import com.xmcc.House.common.ResultResponse;
import com.xmcc.House.pojo.User;
import com.xmcc.House.service.UserService;
import com.xmcc.House.utils.CookieUtils;
import com.xmcc.House.utils.ThreadLocalUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@Slf4j
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    private ThreadLocalUtils threadLocalUtils ;

    //注册用户
    @PostMapping("/user/register")
    public String doRegister(User user, String confirmPasswd, @RequestParam(value = "avatarFile" ) MultipartFile multipartFile, ModelMap modelMap) {



        ResultResponse resultResponse = userService.doRegister(user, confirmPasswd, multipartFile);
        if (resultResponse.isSuccess()) {
            modelMap.addAttribute("email", user.getEmail());
            return "user/accounts/registerSubmit";
        }
        log.error(resultResponse.paramsAsUrl());
        return "redirect:accounts/register" + resultResponse.paramsAsUrl();
    }

    @GetMapping("/accounts/register")
    public String toRegister() {
        return "user/accounts/register";
    }

    @ResponseBody
    @RequestMapping("/queryAll")
    public List<User> queryAll() {
        return userService.querryAll();
    }

    @RequestMapping("/accounts/signin")
    public String toSigin() {
        return "user/accounts/signin";
    }

    @RequestMapping("/accounts/profile")
    public String toProfile(HttpServletRequest request, ModelMap modelMap) {
        User user = threadLocalUtils.popThreadLocal();
        modelMap.put("loginUser", user);
        return "user/accounts/profile";
    }

    @RequestMapping("/accounts/profile/changeUser")
    public String changeUser(User user, ModelMap modelMap,HttpServletRequest request) {
        User password = threadLocalUtils.popThreadLocal();
        user.setPasswd(password.getPasswd());
        User update = userService.changeUser(user);
        if(update==null){
            modelMap.put("errorMsg","更新失败");
            return "user/accounts/profile";
        }
        modelMap.put("successMsg","更新成功");
        modelMap.put("loginUser", update);
        return "user/accounts/profile";
    }

    @RequestMapping("/active")
    public String active(@RequestParam("key") String key) {
        Boolean flag = userService.enable(key);
        if (!flag) {
            return "redirect:/accounts/register?" + ResultResponse.failResponse("激活失败").paramsAsUrl();
        }
        return "/homepage/index";

    }
    @RequestMapping("accounts/logout")
    public String  logout(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(true);

        session.removeAttribute(Constant.SESSION_LOGIN);
        CookieUtils.removeCookie(Constant.COOKIES_LOGIN,response);
        return "homepage/index";

    }

    @PostMapping("/accounts/signin/check")
    public String Login(HttpServletRequest request, String username, String password, @RequestParam(value = "autoLogin",required =false) String autoLogin, HttpServletResponse response, @RequestParam("target") String target, ModelMap modelMap) {
        log.info("********:{}", autoLogin);
        User user = userService.login(username, password);
        if (user != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute(Constant.SESSION_LOGIN, user);
            if (autoLogin!=null) {
                CookieUtils.setCookie(Constant.COOKIES_LOGIN, new StringBuffer(user.getEmail()).append("&").append(user.getPasswd()).toString(), response);
            }

            log.info("原路径为{}{}", target);
            if (StringUtils.isNotBlank(target)) {
                return target;
            }
            modelMap.put("loginUser", user);
            return "/homepage/index";
        }

        return "redirect:/accounts/signin?errorMsg=用户名或者密码错误&target" + target;
    }
}


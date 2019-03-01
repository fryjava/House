package com.xmcc.House.web.controlller;

import com.xmcc.House.pojo.User;
import com.xmcc.House.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class FreemarkerController {
    @Autowired
    private UserService userService ;
    @RequestMapping("test")
    public  String test(ModelMap modelMap){
        modelMap.put("msg","hello freemarker no where json");
        return "test";
    }
    @RequestMapping("index")
    public  String index(ModelMap modelMap){
        return "homepage/index" ;}
        @RequestMapping("user")
    public  String  getUser(ModelMap modelMap){
            List<User> users = userService.querryAll();
            modelMap.addAttribute("user",users.get(0));
            return  "user" ;
        }
        @RequestMapping("exception")
    public  String  exception(){
        if(1==1){
            throw  new RuntimeException("异常处理");
        }
        return "test" ;
    }

}

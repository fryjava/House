package com.xmcc.House.service.Impl;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.xmcc.House.common.ResultResponse;
import com.xmcc.House.dao.UserMapper;
import com.xmcc.House.pojo.User;
import com.xmcc.House.service.UserService;
import com.xmcc.House.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    FileUtils fileUtils;
    @Autowired
    MailUtils mailUtils;

    @Override
    public List<User> querryAll() {
        return userMapper.queryAll();
    }

    @Value("${project.domain}")
    private String projectDomain;
    private final Cache<String, String> registerCach = CacheBuilder.newBuilder().maximumSize(100)
            .expireAfterAccess(10, TimeUnit.MINUTES)
            .removalListener(new RemovalListener<String, String>() {

                @Override
                public void onRemoval(RemovalNotification<String, String> notification) {
                    User user=new User();
                    String email  = notification.getValue();
                    user.setEmail(email);
                 List<User> users=userMapper.queryUser(user);
                 if(users!=null&&Objects.equals(users.get(0).getEnable(),0)){

                    userMapper.deleteUserByEmail(notification.getValue());}
                }
            }).build();

    private final Cache<String, String> resetCache =  CacheBuilder.newBuilder().maximumSize(100).expireAfterAccess(15, TimeUnit.MINUTES).build();

    @Override
    public ResultResponse doRegister(User user, String confirmPasswd, MultipartFile multipartFile) {
        ResultResponse check = BeanValidator.check(user);
        if (!check.isSuccess()) {
            return ResultResponse.failResponse("用户信息不可用");
        }
        if (!StringUtils.equals(user.getPasswd(), confirmPasswd)) {
            return ResultResponse.failResponse("两次输入的密码不一致");
        }
        ResultResponse upload = fileUtils.upload(multipartFile);
        //发送邮箱
        sendEmail(user.getEmail());

        ResultResponse checkuser = doCheckUser(user);
        user.setPasswd(MD5Utils.md5(user.getPasswd()));
        user.setAvatar(upload.getSuccessMsg());
        user.setCreateTime(new Date());

        user.setAgencyId(0);
        user.setEnable(0);

        if (checkuser.isSuccess()) {
            int insert = userMapper.insert(user);
            if (insert > 0) {
                if (!upload.isSuccess()) {
                    return ResultResponse.successReponse("注册用户成功但是上传头像失败");
                }
                return ResultResponse.successReponse("注册用户成功");
            }


        }

        return ResultResponse.failResponse("注册失败");
    }



    @Override
    public User login(String username, String password) {
        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) { password=MD5Utils.md5(password);
            User user = userMapper.selectByUserNameAndPassword(username, password);


            return user;
        }
        return null;
    }

    @Override
    public Boolean enable(String key) {
        String email = registerCach.getIfPresent(key);
        if(StringUtils.isBlank(email)){
            return  false ;
        }
        User user = new User();
        user.setEnable(1);
        user.setEmail(email);
        int i = userMapper.updateByEmail(email);
        registerCach.invalidate(key);
    if(i>0){
        return  true ;
    }
        return false;
    }

    @Override
    public User changeUser(User user) {
        BeanValidator.check(user);
       int i= userMapper.updateByPrimaryKeySelective(user);
       if(i==0){
           return null;
       }
       //查询更新之后的user；
        User Auser = userMapper.selectByUserNameAndPassword(user.getName(), user.getPasswd());
        return Auser ;
    }

    @Async
    public void sendEmail(String email) {
        String uuid = UUIDUtils.uuid();
        registerCach.put(uuid, email);
        mailUtils.sendMail(email, new StringBuffer("http://").append(projectDomain).append("/active").append("?key=").append(uuid).toString(), "请放访问一下地址并确认");


    }

    private ResultResponse doCheckUser(User user) {
        int i = userMapper.checkUser(user);
        if (i > 0) {
            return ResultResponse.failResponse("该用户已经注册过");
        } else
            return ResultResponse.successReponse();
    }
}

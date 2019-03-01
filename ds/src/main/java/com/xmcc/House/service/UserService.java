package com.xmcc.House.service;

import com.xmcc.House.common.ResultResponse;
import com.xmcc.House.pojo.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    List<User> querryAll();

    ResultResponse doRegister(User user, String confirmPasswd, MultipartFile multipartFile);

    User login(String username, String password);

    Boolean enable(String key);

    User changeUser(User user);
}

package com.xmcc.House.utils;

import com.xmcc.House.pojo.User;
import org.springframework.stereotype.Component;

@Component
public class ThreadLocalUtils {
    private  ThreadLocal<User> threadLocal = new ThreadLocal<>();

    public  void pushThreadLocal(User user){
        threadLocal.set(user);
    }
    public  User popThreadLocal(){
        return  threadLocal.get();
    }
    public  void removeThreadLocal(){
        threadLocal.remove();
    }

}

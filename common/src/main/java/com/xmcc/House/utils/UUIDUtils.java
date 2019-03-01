package com.xmcc.House.utils;

import java.util.UUID;

public class UUIDUtils {
    public  static  String uuid(){
        return UUID.randomUUID().toString().replace("-","").substring(0,10);
    }
}

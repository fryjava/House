package com.xmcc.House.utils;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.nio.charset.Charset;

public class MD5Utils {
    private  static  final HashFunction FUNCTION = Hashing.md5();
    private  static  final  String salt="fry";
    public  static  String md5(String str){
        HashCode hashCode = FUNCTION.hashString(str + salt, Charset.forName("UTF-8"));
        return  hashCode.toString();
    }
}

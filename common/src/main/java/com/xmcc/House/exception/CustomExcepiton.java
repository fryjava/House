package com.xmcc.House.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomExcepiton extends RuntimeException {

    private  String message ;

    public  CustomExcepiton(String message){
        super(message);
        this.message=message ;
    }

    public  static  void  cast(String message){
        throw new CustomExcepiton(message);
    }
}

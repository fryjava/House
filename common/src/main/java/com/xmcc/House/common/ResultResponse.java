package com.xmcc.House.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultResponse {

    public  static  final String successKey="successMsg" ;
    public  static  final  String errorKey="errorMsg";
   Boolean flag ;
  private String successMsg ;
  private  String errorMsg ;

  public  ResultResponse(Boolean flag){
      this.flag =flag ;
  }
  public  static  ResultResponse successReponse(){
      return  new ResultResponse(true);
  }
  public  static  ResultResponse successReponse(String  successMeg){
      return new ResultResponse(true,successMeg,"");
  }
  public  static  ResultResponse failResponse(String errorMeg){
      return  new ResultResponse(false,"",errorMeg);
  }

  public  boolean isSuccess(){
      return  flag ;
  }

  public  String paramsAsUrl(){
      StringBuffer url=new StringBuffer("?");
      if(StringUtils.isBlank(errorMsg)){
          return  url.append(successKey).append("=").append(successMsg).toString();
      }
      return  url.append(errorKey).append("=").append(errorMsg).toString();
  }

}

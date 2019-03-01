package com.xmcc.House.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HouseSearchVo  {
    String sort ;
    String name ;
    Integer type ;
    String price_asc ;
    String price_desc ;
    String time_desc ;
    Integer pageSize;
}

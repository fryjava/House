package com.xmcc.House.vo;


import com.google.common.collect.Lists;
import com.xmcc.House.common.Pagination;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseListVo implements Serializable {

     List<HouseVo> list = Lists.newArrayList() ;
     private Pagination pagination ;
}

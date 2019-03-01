package com.xmcc.House.service;

import com.github.pagehelper.PageInfo;
import com.xmcc.House.pojo.House;
import com.xmcc.House.vo.HouseSearchVo;
import com.xmcc.House.vo.HouseVo;

import java.util.List;


public interface HouseService {
    PageInfo<House> queryList(HouseSearchVo houseSearchVo, Integer pageNum, Integer pageSize);

    List<HouseVo> getRecomHouse();
}

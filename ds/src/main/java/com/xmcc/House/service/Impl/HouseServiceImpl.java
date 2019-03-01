package com.xmcc.House.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xmcc.House.dao.HouseMapper;
import com.xmcc.House.pojo.House;
import com.xmcc.House.service.HouseService;
import com.xmcc.House.vo.HouseListVo;
import com.xmcc.House.vo.HouseSearchVo;

import com.xmcc.House.vo.HouseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    HouseMapper houseMapper;


    @Override
    public PageInfo<House> queryList(HouseSearchVo houseSearchVo, Integer pageNum, Integer pageSize) {

        pageNum = pageNum == null ? 0 : pageNum;
        pageSize = pageSize == null ? 2 : pageSize;
        PageHelper.startPage(pageNum, pageSize);
        List<House> houseList = houseMapper.queryList(houseSearchVo);
        PageInfo<House> housePageInfo = new PageInfo<>(houseList);

        return   housePageInfo ;
    }

    @Override
    public List<HouseVo> getRecomHouse() {
    List<House>  houses =  houseMapper.getHotHouse();
        HouseListVo houseVoList = HouseVo.getHouseVoList(houses);
        return houseVoList.getList() ;
    }
}

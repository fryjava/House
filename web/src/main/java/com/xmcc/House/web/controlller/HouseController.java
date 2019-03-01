package com.xmcc.House.web.controlller;


import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.xmcc.House.common.Pagination;
import com.xmcc.House.pojo.House;
import com.xmcc.House.service.HouseService;
import com.xmcc.House.vo.HouseListVo;
import com.xmcc.House.vo.HouseSearchVo;
import com.xmcc.House.vo.HouseVo;
import org.apache.catalina.startup.HomesUserDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("house")
public class HouseController {

    @Autowired
    HouseService houseService;

    @RequestMapping("ownList")
    public String toownlist(ModelMap modelMap) {
        modelMap.put("pageType", "book");
        return "/house/ownlist";
    }

    @RequestMapping("toAdd")
    public String toadd() {
        return "/house/add";
    }

    @RequestMapping("detail")
    public String todetail() {
        return "/house/detail";
    }

    @RequestMapping("list")
    public String houseSearch(ModelMap modelMap, HouseSearchVo houseSearchVo, Integer pageSize, Integer pageNum) {
        if (houseSearchVo.getSort() == null) {
            houseSearchVo.setSort("price_asc");
        }
        PageInfo<House> housePageInfo = houseService.queryList(houseSearchVo, pageNum, pageSize);
        HouseListVo houseVoLists = HouseVo.getHouseVoList(housePageInfo.getList());
        houseVoLists.setPagination(Pagination.getPageHelper(housePageInfo));
        List<HouseVo> houseVos= houseService.getRecomHouse();
        houseSearchVo.setPageSize(housePageInfo.getPageSize());
        modelMap.put("ps", houseVoLists);
        modelMap.put("vo", houseSearchVo);
        modelMap.put("recomHouses",houseVos);
        return "/house/listing";
    }

    @RequestMapping("profile")
    public String toprofile() {
        return "/house/profile";
    }
}


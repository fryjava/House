package com.xmcc.House.vo;

import com.xmcc.House.pojo.House;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class HouseVo extends House {
    String typeStr;
    String firstImg;

    public static HouseVo getHouseVo(House house) {
        HouseVo houseVo = new HouseVo();
        houseVo.setId(house.getId());
        houseVo.setName(house.getAddress());
        if (house.getType()) {
            houseVo.setTypeStr("销售");
        } else {
            houseVo.setTypeStr("出租");
        }
        houseVo.setPrice(house.getPrice());
        houseVo.setArea(house.getArea());
        houseVo.setBeds(house.getBeds());
        houseVo.setBaths(house.getBaths());
        houseVo.setRemarks(house.getRemarks());
        houseVo.setProperties(house.getProperties());
        houseVo.setFloorPlan(house.getFloorPlan());
        houseVo.setTags(house.getTags());
        houseVo.setCreateTime(house.getCreateTime());
        houseVo.setCityId(house.getCityId());
        houseVo.setCommunityId(house.getCommunityId());
        houseVo.setAddress(house.getAddress());
        houseVo.setState(house.getState());
        String images = house.getImages();
        String[] split = images.split(",");
        houseVo.setFirstImg("/static/imgs"+split[0]);
        return houseVo;
    }

    public static HouseListVo getHouseVoList(List<House> houses) {
        HouseListVo houseListVo = new HouseListVo();
        for (House house : houses) {
            HouseVo houseVo = getHouseVo(house);
            houseListVo.getList().add(houseVo);
        }
        return houseListVo;
    }


}

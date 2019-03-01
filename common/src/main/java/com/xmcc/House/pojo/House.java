package com.xmcc.House.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class House implements Serializable {
    private Long id;

    private String name;

    private Boolean type;

    private Integer price;

    private String images;

    private Integer area;

    private Integer beds;

    private Integer baths;

    private Double rating;

    private String remarks;

    private String properties;

    private String floorPlan;

    private String tags;

    private Date createTime;

    private Integer cityId;

    private Integer communityId;

    private String address;

    private Boolean state;


}
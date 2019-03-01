package com.xmcc.House.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User  implements Serializable {
    private Long id;

    private String name;

    private String phone;

    private String email;

    private String aboutme;

    private String passwd;

    private String avatar;

    private Integer type;

    private Date createTime;

    private Integer enable;

    private Integer agencyId;


}
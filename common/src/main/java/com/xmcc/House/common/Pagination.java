package com.xmcc.House.common;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder

public class Pagination {
    Integer pageNum ;
    Integer pageSize;
    List<Integer> pages ;

    public static Pagination getPageHelper(PageInfo pageInfo){
        int pages = pageInfo.getPages();
        List<Integer> pageList = Lists.newArrayList();
        for (int i = 1 ; i <= pages; i++) {
            pageList.add(i);
        }
        return     Pagination.builder().pages(pageList)
                .pageSize(pageInfo.getPageSize())
                .pageNum(pageInfo.getPageNum()).build();

    }
}

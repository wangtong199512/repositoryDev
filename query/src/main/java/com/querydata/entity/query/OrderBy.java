package com.querydata.entity.query;


import java.util.List;
import java.util.Map;

/**
 * 查询排序实体类
 * @date 2021/05/30
 * @author tong.wang
 */
public class OrderBy {
    /**
     * 排序(多排序) 每个map含有
     * orderByFieldName-排序字段
     * orderByType-排序方式(asc-升序 desc-降序)
     */
    List<Map<String, String>> orderByList;

    public List<Map<String, String>> getOrderByList() {
        return orderByList;
    }

    public void setOrderByList(List<Map<String, String>> orderByList) {
        this.orderByList = orderByList;
    }
}

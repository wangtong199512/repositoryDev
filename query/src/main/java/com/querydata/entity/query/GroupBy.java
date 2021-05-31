package com.querydata.entity.query;

import java.util.List;


/**
 * 查询分组实体类
 * @date 2021/05/30
 * @author tong.wang
 */
public class GroupBy {
    // 分组字段
    List<String> groupFieldNames;

    public List<String> getGroupFieldNames() {
        return groupFieldNames;
    }

    public void setGroupFieldNames(List<String> groupFieldNames) {
        this.groupFieldNames = groupFieldNames;
    }
}

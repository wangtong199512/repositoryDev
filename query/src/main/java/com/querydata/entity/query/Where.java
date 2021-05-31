package com.querydata.entity.query;


import java.util.List;
import java.util.Map;

/**
 * 查询条件实体类
 * @date 2021/05/30
 * @author tong.wang
 */
public class Where {
    /**
     * 过滤(多过滤) 每个map含有
     * filterFieldName-过滤字段
     * filterFieldValue-过滤字段值
     * filterType-or或者、and并且
     * matchType-accurate精确匹配、vague-左右模糊匹配、leftVague左模糊匹配、rightVague模糊匹配
     */
    List<Map<String, String>> fileterList;

    public List<Map<String, String>> getFileterList() {
        return fileterList;
    }

    public void setFileterList(List<Map<String, String>> fileterList) {
        this.fileterList = fileterList;
    }
}

package com.querydata.entity.query;


/**
 * 查询分页实体类
 * @date 2021/05/30
 * @author tong.wang
 */
public class Limit {
    // 开始行
    private Integer startRow;
    // 结束行
    private Integer endRow;

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public Integer getEndRow() {
        return endRow;
    }

    public void setEndRow(Integer endRow) {
        this.endRow = endRow;
    }
}

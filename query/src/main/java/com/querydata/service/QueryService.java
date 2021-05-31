package com.querydata.service;

import com.querydata.entity.UserEntity;
import com.querydata.entity.query.GroupBy;
import com.querydata.entity.query.Limit;
import com.querydata.entity.query.OrderBy;
import com.querydata.entity.query.Where;

import java.util.List;

/**
 * 查询接口
 * @date 2021/05/30
 * @author tong.wang
 */
public interface QueryService {

    /**
     * 通过条件获取数据
     * @param userList 需要过滤的用户(这里以UserEntity为例)
     * @param where 查询条件
     * @param orderBy 排序条件信息
     * @param groupBy 分组信息
     * @param limit 分页信息
     * @return List 查询结果
     */
    public List<UserEntity> query(List<UserEntity>userList, Where where, OrderBy orderBy, GroupBy groupBy, Limit limit);
}

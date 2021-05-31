package com.querydata.entity;

import java.util.Date;

/**
 * 基础实体类
 * @date 2021/05/30
 * @author tong.wang
 */
public class BaseEntity {
    // 数据id
    private String id;
    // 创建时间
    private Date createTime;
    // 创建人id
    private String createUserId;
    // 创建人名称
    private String createUserName;
    // 更新时间
    private Date updateTime;
    // 更新人id
    private String updateUserId;
    // 更新人名称
    private String updateUserName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }
}

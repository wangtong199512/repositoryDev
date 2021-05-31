package com.querydata.entity;

import java.util.Date;

/**
 * 用户信息实体类
 * @date 2021/05/30
 * @author tong.wang
 */
public class UserEntity extends BaseEntity{
    // 人员名称
    private String name;
    // 年龄
    private Integer age;
    // 性别 (1-男 2-女)
    private String sex;
    // 家庭住址
    private String address;
    // 人员所在部门id
    private String deptId;
    // 人员所在部门名称
    private String departmentName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}

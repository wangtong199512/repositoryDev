package com.querydata.service;


import com.alibaba.fastjson.JSON;
import com.querydata.entity.UserEntity;
import com.querydata.entity.query.GroupBy;
import com.querydata.entity.query.Limit;
import com.querydata.entity.query.OrderBy;
import com.querydata.entity.query.Where;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.querydata.constants.CommonConstant.OrderConstant.*;
import static com.querydata.constants.CommonConstant.WhereConstant.*;

@SpringBootTest
@Slf4j
public class QueryServiceTest {

    @Autowired
    QueryService queryService;

    private Where where = new Where();
    private GroupBy groupBy = new GroupBy();
    private OrderBy orderBy = new OrderBy();
    private Limit limit = new Limit();

    public static List<UserEntity> userList = new ArrayList<>();
    // 初始化用户信息，用作过滤
    static {
        UserEntity u = new UserEntity();
        u.setId("1");
        u.setName("张三");
        u.setAge(24);
        u.setAddress("四川成都");
        u.setSex("1");
        userList.add(u);
        UserEntity u1 = new UserEntity();
        u1.setId("2");
        u1.setName("李四");
        u1.setAge(23);
        u1.setAddress("四川绵阳");
        u1.setSex("1");
        userList.add(u1);
        UserEntity u2 = new UserEntity();
        u2.setId("3");
        u2.setName("王五");
        u2.setAge(22);
        u2.setAddress("四川绵阳");
        u2.setSex("2");
        userList.add(u2);
        UserEntity u3 = new UserEntity();
        u3.setId("4");
        u3.setName("张三1");
        u3.setAge(21);
        u3.setAddress("四川南充");
        u3.setSex("1");
        userList.add(u3);
        UserEntity u4 = new UserEntity();
        u4.setId("1");
        u4.setName("麻子");
        u4.setAge(21);
        u4.setAddress("四川内江");
        u4.setSex("2");
        userList.add(u4);
    }

    /**
     * filter分支覆盖
     */
    @Test
    void TestFilter() {
        List<Map<String, String>> filterList = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put(FILTER_FIELD_NAME, "name");
        map.put(FILTER_FIELD_VALUE, "张三");
        map.put(FILTER_TYPE, FILTER_FIELD_OR);
        map.put(MATCH_TYPE, MATCH_TYPE_ACCURATE);
        filterList.add(map);
        where.setFileterList(filterList);
        List<UserEntity> resList = queryService.query(userList, where, orderBy, groupBy, limit);
        log.info(">>>>>>>>>>>>>获取名称为张三的数据结果为" + JSON.toJSONString(resList));
    }

    /**
     * group分支覆盖
     */
    @Test
    void TestGroup() {
        List<String> groupFieldNames = new ArrayList<>();
        groupFieldNames.add("age");
        groupBy.setGroupFieldNames(groupFieldNames);
        List<UserEntity> resList = queryService.query(userList, where, orderBy, groupBy, limit);
        log.info(">>>>>>>>>>>>>获取按照年龄分组的数据结果为:");
        printExecResult(resList);


        groupFieldNames.add("sex");
        groupBy.setGroupFieldNames(groupFieldNames);
        log.info(">>>>>>>>>>>>>获取按照年龄、性别分组的数据结果为:");
        printExecResult(resList);
    }

    /**
     * 排序分支覆盖
     */
    @Test
    void TestOrderBy() {
        List<Map<String, String>> orderByList = new ArrayList<>();
        // 第一排序以id
        Map<String, String> odMap1 = new HashMap<>();
        odMap1.put(ORDER_BY_FIELD_NAME, "id");
        odMap1.put(ORDER_BY_TYPE, ORDER_BY_ASC);
        orderByList.add(odMap1);
        // 第二排序以年龄
        Map<String, String> odMap2 = new HashMap<>();
        odMap2.put(ORDER_BY_FIELD_NAME, "age");
        odMap2.put(ORDER_BY_TYPE, ORDER_BY_DESC);
        orderByList.add(odMap2);
        orderBy.setOrderByList(orderByList);
        List<UserEntity> resList = queryService.query(userList, where, orderBy, groupBy, limit);
        log.info(">>>>>>>>>>>>>获取按照id升序，年龄降序的数据结果为:");
        printExecResult(resList);
    }

    /**
     * 分页分支覆盖
     */
    @Test
    void TestLimit() {
        limit.setStartRow(1);
        limit.setEndRow(3);
        List<UserEntity> resList = queryService.query(userList, where, orderBy, groupBy, limit);
        log.info(">>>>>>>>>>>>>获取分页后数据结果为:");
        printExecResult(resList);
    }

    /**
     * 打印执行结果
     * @param resList
     */
    private void printExecResult(List<UserEntity> resList) {
        if (CollectionUtils.isEmpty(resList)) {
            return;
        }
        for (UserEntity u : resList) {
            log.info(JSON.toJSONString(u));
        }
    }

}

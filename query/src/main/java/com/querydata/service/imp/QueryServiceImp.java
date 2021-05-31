package com.querydata.service.imp;

import com.querydata.entity.UserEntity;
import com.querydata.entity.query.GroupBy;
import com.querydata.entity.query.Limit;
import com.querydata.entity.query.OrderBy;
import com.querydata.entity.query.Where;
import com.querydata.service.QueryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.querydata.constants.CommonConstant.OrderConstant.*;
import static com.querydata.constants.CommonConstant.WhereConstant.*;

/**
 * 查询接口实现
 */
@Service
@Slf4j
public class QueryServiceImp implements QueryService {

    @Override
    public List<UserEntity> query(List<UserEntity> userList, Where where, OrderBy orderBy, GroupBy groupBy, Limit limit) {
        // 无数据直接结束
        if (CollectionUtils.isEmpty(userList)) {
            return userList;
        }
        // 过滤数据
        userList = filterData(userList, where);
        // 分组数据
        userList = groupData(userList, groupBy);
        // 排序数据
        userList = orderData(userList, orderBy);
        // 分页数据
        userList = limitData(userList, limit);
        return userList;
    }

    /**
     * 获取分页后的数据
     * @param userList 需要分页的数据
     * @param limit 分页对象
     * @return List 分页之后的结果
     */
    private List<UserEntity> limitData(List<UserEntity> userList, Limit limit) {
        Integer startRow = limit.getStartRow();
        Integer endRow = limit.getEndRow();
        // 无数据或者无分页信息
        if (CollectionUtils.isEmpty(userList) || startRow == null || endRow == null) {
            return userList;
        }
        return userList.subList(startRow, endRow);
    }

    /**
     * 排序数据
     * @param userList 需要排序的数据
     * @param orderBy 排序信息
     * @return List 排序之后的结果
     */
    private List<UserEntity> orderData(List<UserEntity> userList, OrderBy orderBy) {
        List<Map<String, String>> orderByList = orderBy.getOrderByList();
        // 无数据或者无分组条件过滤
        if (CollectionUtils.isEmpty(orderByList) || CollectionUtils.isEmpty(userList)) {
            return userList;
        }

        Stream<UserEntity> stream = userList.stream();
        Comparator<UserEntity> comparObj = null;
        for (Map<String, String> orderMap : orderByList) {
            String orderField = orderMap.get(ORDER_BY_FIELD_NAME);
            String orderType = orderMap.get(ORDER_BY_TYPE);
            // 排序数据
            comparObj = getComparObj(comparObj, orderField, orderType);
        }
        // 返回排序结果
        return stream.sorted(comparObj).collect(Collectors.toList());
    }

    /**
     * 获取排序对象
     * @param c 排序对象
     * @param orderField 排序字段
     * @param orderType 排序方式
     * @return Comparator 排序对象
     */
    private Comparator getComparObj(Comparator<UserEntity> c, String orderField, String orderType) {
        switch (orderField) {
                case "id":
                    if (c == null) {
                        c = Comparator.comparing(UserEntity::getId);
                    } else {
                        c = c.thenComparing(UserEntity::getId);
                    }
                    break;
                case "createTime":
                    if (c == null) {
                        c = Comparator.comparing(UserEntity::getCreateTime);
                    } else {
                        c = c.thenComparing(UserEntity::getCreateTime);
                    }
                    break;
                case "createUserId":
                    if (c == null) {
                        c = Comparator.comparing(UserEntity::getCreateUserId);
                    } else {
                        c = c.thenComparing(UserEntity::getCreateUserId);
                    }
                    break;
                case "createUserName":
                    if (c == null) {
                        c = Comparator.comparing(UserEntity::getCreateUserName);
                    } else {
                        c = c.thenComparing(UserEntity::getCreateUserName);
                    }
                    break;
                case "updateTime":
                    if (c == null) {
                        c = Comparator.comparing(UserEntity::getUpdateTime);
                    } else {
                        c = c.thenComparing(UserEntity::getUpdateTime);
                    }
                    break;
                case "updateUserId":
                    if (c == null) {
                        c = Comparator.comparing(UserEntity::getUpdateUserId);
                    } else {
                        c = c.thenComparing(UserEntity::getUpdateUserId);
                    }
                    break;
                case "updateUserName":
                    if (c == null) {
                        c = Comparator.comparing(UserEntity::getUpdateUserName);
                    } else {
                        c = c.thenComparing(UserEntity::getUpdateUserName);
                    }
                    break;
                case "name":
                    if (c == null) {
                        c = Comparator.comparing(UserEntity::getName);
                    } else {
                        c = c.thenComparing(UserEntity::getName);
                    }
                    break;
                case "age":
                    if (c == null) {
                        c = Comparator.comparing(UserEntity::getId);
                    } else {
                        c = c.thenComparing(UserEntity::getId);
                    }
                    break;
                case "sex":
                    if (c == null) {
                        c = Comparator.comparing(UserEntity::getSex);
                    } else {
                        c = c.thenComparing(UserEntity::getSex);
                    }
                    break;
                case "address":
                    if (c == null) {
                        c = Comparator.comparing(UserEntity::getAddress);
                    } else {
                        c = c.thenComparing(UserEntity::getAddress);
                    }
                    break;
                case "deptId":
                    if (c == null) {
                        c = Comparator.comparing(UserEntity::getDeptId);
                    } else {
                        c = c.thenComparing(UserEntity::getDeptId);
                    }
                    break;
                case "departmentName":
                    if (c == null) {
                        c = Comparator.comparing(UserEntity::getDepartmentName);
                    } else {
                        c = c.thenComparing(UserEntity::getDepartmentName);
                    }
                    break;

                default:
        }
        // 降序，默认升序
        if (orderType.equals(ORDER_BY_DESC)) {
            c = c.reversed();
        }
        return c;
    }


    /**
     * 根据分组条件分组
     * @param userList 需要分组的数据
     * @param groupBy 分组信息
     * @return List 分组后的数据结果
     */
    private List<UserEntity> groupData(List<UserEntity> userList, GroupBy groupBy) {
        List<String> grpNames = groupBy.getGroupFieldNames();
        // 无数据或者无分组条件过滤
        if (CollectionUtils.isEmpty(grpNames) || CollectionUtils.isEmpty(userList)) {
            return userList;
        }
        Map<String, UserEntity> grpMap = new HashMap<>();
        for (UserEntity u : userList) {
            // 拼接分组key
            StringBuffer grpKey = new StringBuffer();
            for (String field : grpNames) {
                grpKey.append("_").append(getFieldValue(u, field));
            }
            // 验证分组中是否已经包含了该数据
            if (!grpMap.containsKey(grpKey.toString())) {
                grpMap.put(grpKey.toString(), u);
            }
        }
        // 将分组结果转为list返回
        return new ArrayList<>(grpMap.values());
    }

    /**
     * 通过条件过滤数据
     * @param userList 需要过滤的数据
     * @param where 条件
     * @return
     */
    private List<UserEntity> filterData(List<UserEntity> userList, Where where) {
        List<Map<String, String>> filterList = where.getFileterList();
        // 无过滤条件
        if (CollectionUtils.isEmpty(filterList)) {
            return userList;
        }
        // 或条件处理结果集合
        List<UserEntity> orUserList = new ArrayList<>();
        // 并且条件处理结果集合
        List<UserEntity> andUserList = new ArrayList<>(userList);
        // 是否执行and分支
        boolean execAndFlg = false;
        // 循环过滤数据
        for (Map<String, String> filter : filterList) {
            String filterType = filter.get(FILTER_TYPE);
            // 或
            if (filterType.equals(FILTER_FIELD_OR)) {
                orUserList.addAll(filterList(filter, userList, where));
            }  else if (filterType.equals(FILTER_FIELD_AND)) {
                // 并且
                andUserList = filterList(filter, andUserList, where);
                execAndFlg = true;
            } else {
                continue;
            }
        }
        // 合并or和and的结果
        if (execAndFlg) {
            orUserList.addAll(andUserList);
        }
        return orUserList;
    }

    /**
     * 过滤集合
     */
    private List<UserEntity> filterList(Map<String, String> filter, List<UserEntity> userList, Where where) {
        // 获取过滤参数
        String fieldName = filter.get(FILTER_FIELD_NAME);
        String queryValue = filter.get(FILTER_FIELD_VALUE);
        String matchType = filter.get(MATCH_TYPE);
        // 根据条件过滤结果
        List<UserEntity> resList = userList.stream().filter(u -> {
            String value = getFieldValue(u, fieldName);
            return matchValue(value, queryValue, matchType);
        }).collect(Collectors.toList());
        return resList;
    }

    /**
     * 匹配值
     * @param value 需要匹配的值
     * @param queryValue 匹配条件
     * @param matchType 匹配方式
     * @return
     */
    private boolean matchValue(String value, String queryValue, String matchType) {
        // 精确匹配
        if (matchType.equals(MATCH_TYPE_ACCURATE) && value.equals(queryValue)) {
            return true;
        }
        // 左右模糊匹配
        if (matchType.equals(MATCH_TYPE_VAGUE) && value.contains(queryValue)) {
            return true;
        }
        // 左模糊匹配
        if (matchType.equals(MATCH_TYPE_LEFTVAGUE) && value.endsWith(queryValue)) {
            return true;
        }
        // 右模糊匹配
        if (matchType.equals(MATCH_TYPE_RIGHTVAGUE) && value.startsWith(queryValue)) {
            return true;
        }
        return false;
    }

    /**
     * 通过字段名称获取字段值
     * @param u
     * @param fieldName
     * @return
     */
    private String getFieldValue(UserEntity u, String fieldName) {
        // 根据驼峰构造get方法
        String firstLetter = fieldName.substring(0, 1).toUpperCase();
        String getter = "get" + firstLetter + fieldName.substring(1);
        Object value = null;
        try {
            // 调用get方法获取value
            Method method = u.getClass().getMethod(getter, new Class[] {});
            value = method.invoke(u, new Object[] {});
        } catch (Exception e) {
            log.error("匹配查询出错", e);
        }
        return value == null ? "" : value.toString();
    }
}

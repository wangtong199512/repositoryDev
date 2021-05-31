package com.querydata.constants;


/**
 * 公共常量
 * @date 2021/05/30
 * @author tong.wang
 */
public class CommonConstant {
    /**
     * 过滤条件常量
     */
    public static final class WhereConstant {
        // 过滤字段名称
        public static final String FILTER_FIELD_NAME = "filterFieldName";
        // 过滤字段值
        public static final String FILTER_FIELD_VALUE = "filterFieldValue";

        // 过滤类型
        public static final String FILTER_TYPE = "filterType";
        // 过滤类型-OR
        public static final String FILTER_FIELD_OR = "or";
        // 过滤类型-AND
        public static final String FILTER_FIELD_AND = "and";

        // 匹配方式
        public static final String MATCH_TYPE = "matchType";
        // 精确匹配
        public static final String MATCH_TYPE_ACCURATE = "accurate";
        // 左右模糊匹配
        public static final String MATCH_TYPE_VAGUE = "vague";
        // 左模糊匹配
        public static final String MATCH_TYPE_LEFTVAGUE = "leftVague";
        // 右模糊匹配
        public static final String MATCH_TYPE_RIGHTVAGUE = "rightVague";
    }

    /**
     * 排序条件常量
     */
    public static final class OrderConstant {
        // 排序字段名称
        public static final String ORDER_BY_FIELD_NAME = "orderByFieldName";
        // 排序类型
        public static final String ORDER_BY_TYPE = "orderByType";

        // 排序类型-升序
        public static final String ORDER_BY_ASC = "asc";
        // 排序类型-降序
        public static final String ORDER_BY_DESC = "desc";
    }
}

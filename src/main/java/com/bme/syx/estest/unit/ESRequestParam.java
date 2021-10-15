package com.bme.syx.estest.unit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ESRequestParam {


    private String index;   //索引
    private String order;   //排序规则；desc：倒序，asc：顺序
    private Long customerId;    //客户id
    private Integer from;   //分页页码，从1开始
    private Integer size;   //分页显示条数
    private String filed;   //需要排序字段名称
    private List<Match> match;   //match过滤
    private List<Terms> terms;

    @Data
    @Builder
    public static class Match {
        private String filedName;   //字段名称
        private String value;   //值
        private String relational;  //逻辑查询and/or
        private boolean isNumber;   //是否是数字类型
        private boolean isMultiMatch;   //是否为多字段匹配

        public boolean isShould() {
            return "should".equals(this.relational);
        }

        public boolean isMust() {
            return "must".equals(this.relational);
        }

        public void setShould() {
            this.relational = "should";
        }

        public void setMust() {
            this.relational = "must";
        }

        public boolean getNumber() {
            return isNumber;
        }

        public void setNumber(boolean number) {
            isNumber = number;
        }
    }

    @Data
    public static class Terms{
        private String fieldName;   //字段名称
        private String value;
    }
}

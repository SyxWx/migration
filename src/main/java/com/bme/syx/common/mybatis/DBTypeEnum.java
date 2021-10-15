package com.bme.syx.common.mybatis;
public enum DBTypeEnum {
    db1("mysql"), db2("sqlServer"),db3("tiDb");
    private String value;

    DBTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

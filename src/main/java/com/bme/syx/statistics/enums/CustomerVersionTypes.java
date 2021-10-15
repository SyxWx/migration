package com.bme.syx.statistics.enums;

import lombok.Getter;
import lombok.Setter;


@Getter
public enum CustomerVersionTypes {
    cd_version(1,"超低版大屏"),
    bz_version(2,"标准版大屏"),
    new_version(3,"315版本大屏"),
    private_bz_version(4,"私有化标准版大屏"),
    private_new_version(5,"私有化315大屏");

    public int code;
    public String version;

    CustomerVersionTypes(int code,String version){
        this.code = code;
        this.version = version;
    }

    public static CustomerVersionTypes getValue(int code){
        for (CustomerVersionTypes types : values()){
            if(types.getCode() == code){
                return types;
            }
        }
        return null;
    }
}

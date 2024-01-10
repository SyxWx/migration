package com.bme.syx.statistics.entity;

import lombok.Data;

@Data
public class MenuEnitty {

    public String name;
    public Long customerId;
    public Long parentid;
    public String url;
    public int type;
    public int status;

}

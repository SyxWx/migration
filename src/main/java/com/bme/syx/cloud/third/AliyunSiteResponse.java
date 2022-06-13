package com.bme.syx.cloud.third;


import lombok.Data;

import java.util.List;

/**
 * @author joker
 */
@Data
public class AliyunSiteResponse {

    private boolean success;

    private List<GovernmentMonitor> data;
}
